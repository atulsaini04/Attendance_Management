package com.example.myfirstproject.firstproject.service;

import com.example.myfirstproject.firstproject.entity.AuthToken;
import com.example.myfirstproject.firstproject.entity.Login;

public interface LoginService {
        boolean login(Long empCode, String password, Long orgId);
        Login findByEmpCode(Long empCode);
        void saveAuthToken(AuthToken authToken);
        boolean invalidateAuthToken(String authToken);
        boolean isAlreadyLoggedIn(Long empCode);

    }


//login krte hue save
//token ko auth token vali me save karana h with server time
// expiration time hoga login ke 2min baad ka
// expiration ke time pr token invalid hojae or logout hojae automatically
//logout krte hue bhi token delete krde 