package com.example.myfirstproject.firstproject;

public class LoginStatusTO {
    private String authToken;
    private String status;

    public LoginStatusTO() {
    }

    public LoginStatusTO(String authToken, String status) {
        this.authToken = authToken;
        this.status = status;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
