package models;

public class GoogleAuthentication {
    private String email;
    private String accessToken;

    public GoogleAuthentication() {
    }

    public GoogleAuthentication(String email, String accessToken) {
        this.email = email;
        this.accessToken = accessToken;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "{" + " email='" + getEmail() + "'" + ", accessToken='" + getAccessToken() + "'" + "}";
    }

}