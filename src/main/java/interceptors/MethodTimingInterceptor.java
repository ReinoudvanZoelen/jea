package interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.ArrayList;

@Interceptor
public class MethodTimingInterceptor {

    private static ArrayList<String> logging;

    @AroundInvoke
    public Object aroundInvoke(InvocationContext invocationContext) throws Exception {
        var methodName = invocationContext.getMethod().getName();
        var properties = invocationContext.getParameters();

        long startTime = System.nanoTime();

        try {
            return invocationContext.proceed();
        } finally {
            long stopTime = System.nanoTime();

            var message = "The method [" + methodName + "] with properties [";

            for (var property : properties) {
                message += property + ", ";
            }
            message += "] and it took [" + (stopTime - startTime) + "] milliseconds to complete.";

            logging.add(message);
        }
    }
}
