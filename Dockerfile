FROM jboss/wildfly:15.0.1.Final

# Appserver
ENV WILDFLY_USER admin
ENV WILDFLY_PASS admin

# Database
ENV DB_NAME testdb
ENV DB_USER mysql
ENV DB_PASS mysql
ENV DB_URI db:3306

ENV MYSQL_VERSION 8.0.15
ENV JBOSS_CLI /opt/jboss/wildfly/bin/jboss-cli.sh
ENV DEPLOYMENT_DIR /opt/jboss/wildfly/standalone/deployments/
#ENV JAVA_OPTS

# Setting up WildFly Admin Console
RUN echo "=> Adding WildFly administrator"
RUN $JBOSS_HOME/bin/add-user.sh -u $WILDFLY_USER -p $WILDFLY_PASS --silent

# Configure Wildfly server
RUN echo "=> Starting WildFly server" && \
      bash -c '$JBOSS_HOME/bin/standalone.sh &' && \
    echo "=> Waiting for the server to boot" && \
      bash -c 'until `$JBOSS_CLI -c ":read-attribute(name=server-state)" 2> /dev/null | grep -q running`; do echo `$JBOSS_CLI -c ":read-attribute(name=server-state)" 2> /dev/null`; sleep 1; done' && \
    echo "=> Downloading MySQL driver" && \
      curl --location --output /tmp/mysql-connector-java-${MYSQL_VERSION}.jar --url http://search.maven.org/remotecontent?filepath=mysql/mysql-connector-java/${MYSQL_VERSION}/mysql-connector-java-${MYSQL_VERSION}.jar && \
    echo "=> Adding MySQL module" && \
      $JBOSS_CLI --connect --command="module add --name=com.mysql --resources=/tmp/mysql-connector-java-${MYSQL_VERSION}.jar --dependencies=javax.api,javax.transaction.api" && \
    echo "=> Adding MySQL driver" && \
      $JBOSS_CLI --connect --command="/subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql,driver-class-name=com.mysql.cj.jdbc.Driver)" && \
    echo "=> Creating a new datasource" && \
      $JBOSS_CLI --connect --command="data-source add \
        --name=${DB_NAME}DS \
        --jndi-name=java:/jdbc/datasources/${DB_NAME}DS \
        --user-name=${DB_USER} \
        --password=${DB_PASS} \
        --driver-name=mysql \
        --connection-url=jdbc:mysql://${DB_URI}/${DB_NAME} \
        --use-ccm=false \
        --max-pool-size=25 \
        --blocking-timeout-wait-millis=5000 \
        --enabled=true" && \
    echo "=> Creating security domain" && \
      $JBOSS_CLI --connect --command="./subsystem=security/security-domain=application-security/:add" && \
    echo "=> Creating security domain modules" && \
      $JBOSS_CLI --connect --command="./subsystem=security/security-domain=application-security/authentication=classic:add(login-modules=[{code=Database, flag=Required, module-options={ \
      dsJndiName=\"java:/jdbc/datasources/${DB_NAME}DS\", \
      principalsQuery=\"SELECT password FROM AuthenticationUser WHERE username = ?\", \
      rolesQuery=\"SELECT userrole, 'Roles' FROM AuthenticationUser WHERE username = ?\", }}])" && \
    echo "=> Creating Keystore" && \
      bash -c 'keytool -genkey -alias alias -keyalg RSA -keysize 2048 -keystore ./wildfly/standalone/configuration/jwt.keystore -storepass secret -keypass secret -dname "CN=localhost, OU=Wildfly, O=Jboss, L=Tilburg, S=Noord-Brabant, C=NL"' && \
    echo "=> Add a keystore to elytron for loading signature public key" && \
      $JBOSS_CLI --connect --command="./subsystem=elytron/key-store=jwt-key-store:add(type=\"JKS\",relative-to=jboss.server.config.dir,path=\"jwt.keystore\",credential-reference={clear-text=\"secret\"})" && \
    echo "=> Add a new token security realm to elytron for authentication using JWTs" && \
      $JBOSS_CLI --connect --command="./subsystem=elytron/token-realm=jwt-realm:add(jwt={issuer=[\"quickstart-jwt-issuer\"],audience=[\"jwt-audience\"],key-store=jwt-key-store,certificate=\"alias\"},principal-claim=\"sub\")" && \
    echo "=> Add a new security domain, which uses the jwt security realm" && \
      $JBOSS_CLI --connect --command="./subsystem=elytron/security-domain=jwt-domain:add(realms=[{realm=jwt-realm,role-decoder=groups-to-roles}],permission-mapper=default-permission-mapper,default-realm=jwt-realm)" && \
    echo "=> Create http authentication factory that uses BEARER_TOKEN authentication" && \
      $JBOSS_CLI --connect --command="./subsystem=elytron/http-authentication-factory=jwt-http-authentication:add(security-domain=jwt-domain,http-server-mechanism-factory=global,mechanism-configurations=[{mechanism-name=\"BEARER_TOKEN\",mechanism-realm-configurations=[{realm-name=\"jwt-realm\"}]}])" && \
    echo "=> Configure Undertow to use our http authentication factory for authentication" && \
      $JBOSS_CLI --connect --command="./subsystem=undertow/application-security-domain=other:add(http-authentication-factory=jwt-http-authentication)" && \
    echo "=> Shutting down WildFly and Cleaning up" && \
      $JBOSS_CLI --connect --command=":shutdown" && \
      rm -rf $JBOSS_HOME/standalone/configuration/standalone_xml_history/ $JBOSS_HOME/standalone/log/* && \
      rm -f /tmp/*.jar

# Expose http and admin ports
EXPOSE 8080 9990

#echo "=> Restarting WildFly"
# Set the default command to run on boot
# This will boot WildFly in the standalone mode and bind to all interfaces
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]