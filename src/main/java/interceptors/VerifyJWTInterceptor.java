package interceptors;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import services.JwtService;

@Interceptor
public class VerifyJWTInterceptor {

    @Inject
    JwtService jwtService;

    @AroundInvoke
    public Object VerifyJWT(InvocationContext invocationContext) throws Exception {
        String token = invocationContext.getParameters()[0].toString();
        if(token.length() > 5){
            return invocationContext.proceed();
        } else {
            throw new Exception();
        }
    }
}