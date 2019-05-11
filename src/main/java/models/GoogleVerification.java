package models;

public class GoogleVerification {
    private String audience;

    private String access_type;

    private String issued_to;

    private String user_id;

    private String scope;

    private String verified_email;

    private String expires_in;

    private String email;

    public GoogleVerification() {
    }

    public GoogleVerification(String audience, String access_type, String issued_to, String user_id, String scope,
            String verified_email, String expires_in, String email) {
        this.audience = audience;
        this.access_type = access_type;
        this.issued_to = issued_to;
        this.user_id = user_id;
        this.scope = scope;
        this.verified_email = verified_email;
        this.expires_in = expires_in;
        this.email = email;
    }

    public String getAudience() {
        return this.audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getAccess_type() {
        return this.access_type;
    }

    public void setAccess_type(String access_type) {
        this.access_type = access_type;
    }

    public String getIssued_to() {
        return this.issued_to;
    }

    public void setIssued_to(String issued_to) {
        this.issued_to = issued_to;
    }

    public String getUser_id() {
        return this.user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getScope() {
        return this.scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getVerified_email() {
        return this.verified_email;
    }

    public void setVerified_email(String verified_email) {
        this.verified_email = verified_email;
    }

    public String getExpires_in() {
        return this.expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "{" + " audience='" + getAudience() + "'" + ", access_type='" + getAccess_type() + "'" + ", issued_to='"
                + getIssued_to() + "'" + ", user_id='" + getUser_id() + "'" + ", scope='" + getScope() + "'"
                + ", verified_email='" + getVerified_email() + "'" + ", expires_in='" + getExpires_in() + "'"
                + ", email='" + getEmail() + "'" + "}";
    }

}
