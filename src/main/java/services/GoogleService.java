package services;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

public class GoogleService {

    private OAuth20Service service;
    private final String API_KEY = "1007737378302-29hfrihbgg06q0c16i3er12f2pnf5cvt.apps.googleusercontent.com";
    private final String API_SECRET = "XmoG14YsrwrKbrXy1yoBap9s";
    private final String SCOPE = "https://www.googleapis.com/auth/userinfo.email";
    private final String CALLBACK = "http://localhost:8080/helloworld-rs/rest/auth/google/authenticate";

    public GoogleService() {
        this.service = new ServiceBuilder(API_KEY).apiSecret(API_SECRET).withScope(SCOPE).callback(CALLBACK)
                .build(GoogleApi20.instance());
    }

    public OAuth20Service getService() {
        return service;
    }
}