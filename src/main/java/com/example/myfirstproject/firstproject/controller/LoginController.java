package com.example.myfirstproject.firstproject.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myfirstproject.firstproject.LoginStatusTO;
import com.example.myfirstproject.firstproject.LoginTO;
import com.example.myfirstproject.firstproject.entity.AuthToken;
import com.example.myfirstproject.firstproject.service.LoginService;
// testcases for login controller with code

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    private static final String secretKey = "my-key"; // Replace with your actual secret key

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<LoginStatusTO> login(@RequestBody LoginTO login) {
        Long empCode = login.getEmp_code();
        String password = login.getPassword();
        Long orgId = login.getOrg_id();

        boolean isAlreadyLoggedIn = loginService.isAlreadyLoggedIn(empCode);
        if (isAlreadyLoggedIn) {
            // User is already logged in, return an error response
            LoginStatusTO loginStatusTO = new LoginStatusTO();
            loginStatusTO.setStatus("alreadyloggedin");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginStatusTO);
        }

        boolean isValidLogin = loginService.login(empCode, password, orgId);
        LoginStatusTO loginStatusTO = new LoginStatusTO();
        if (isValidLogin) {
            String emppassword = empCode + ":" + password;
            String authToken = generateAuthToken(emppassword, secretKey);

            AuthToken auth = new AuthToken();
            auth.setToken(authToken);
            auth.setEmp_code(empCode);
            auth.setExpirationTime(LocalDateTime.now().plusMinutes(2));

            loginService.saveAuthToken(auth); // Save the auth token in the database

            loginStatusTO.setAuthToken(authToken);
            loginStatusTO.setStatus("loggedin");
            return ResponseEntity.ok(loginStatusTO);
        } else {
            loginStatusTO.setStatus("unauthorized");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginStatusTO);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("authToken") String authToken) {
        boolean success = loginService.invalidateAuthToken(authToken);
        if (success) {
            // Remove the authentication token from the server-side storage
            return ResponseEntity.ok("Logged out successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }

    private String generateAuthToken(String emppassword, String secretKey) {
        UUID uuid = UUID.randomUUID();
        String randomUUID = uuid.toString();

        String dataToHash = emppassword + secretKey + randomUUID;
        String hashedData = hashSHA256(dataToHash);
        return hashedData;
    }
// q: what is solid

    private String hashSHA256(String data) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = messageDigest.digest(data.getBytes());
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            // Handle hashing error
            e.printStackTrace();
            return null;
        }
    }
}
