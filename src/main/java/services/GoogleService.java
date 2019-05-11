package services;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import models.GoogleAuthentication;

public class GoogleService {

    private final String BASE_URL_VERIFICATION = "https://www.googleapis.com/oauth2/v1/tokeninfo?access_token=";

    public boolean verifyGoogleAuthentication(GoogleAuthentication googleAuthentication) {
        try {
            URL url = new URL(this.BASE_URL_VERIFICATION + googleAuthentication.getAccessToken());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            int code = connection.getResponseCode();

            connection.disconnect();

            return code == 200;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}