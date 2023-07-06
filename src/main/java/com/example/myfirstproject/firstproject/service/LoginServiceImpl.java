package com.example.myfirstproject.firstproject.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myfirstproject.firstproject.entity.AuthToken;
import com.example.myfirstproject.firstproject.entity.Login;
import com.example.myfirstproject.firstproject.repository.AuthTokenRepository;
import com.example.myfirstproject.firstproject.repository.LoginRepository;

//try catch finally exception handling

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private final LoginRepository loginRepository;
    @Autowired
    private AuthTokenRepository authTokenRepository;

    public LoginServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public boolean login(Long empCode, String password, Long orgId) {
        Login login = loginRepository.findByEmpCodeAndOrgId(empCode, orgId);
        // System.out.println("zuffg");
        return login != null && login.getPassword().equals(password);
    }

    @Override
    public Login findByEmpCode(Long empCode) {
        // Retrieve and return the Login entity by empCode
        return loginRepository.findByEmpCode(empCode);

    }
    

    @Override
    public boolean isAlreadyLoggedIn(Long empCode) {
        List<AuthToken> activeTokens = authTokenRepository.findAll();

        for (AuthToken token : activeTokens) {
            Login login = token.getLogin();
            if (login != null && login.getEmp_code().equals(empCode)) {
                // User is already logged in
                return true;
            }
        }

        // User is not logged in
        return false;
    }

    @Override
    public void saveAuthToken(AuthToken authToken) {
        // Save the AuthToken entity in the database
        authTokenRepository.save(authToken);
    }

  
@Override
public boolean invalidateAuthToken(String authToken) {
    AuthToken auth = authTokenRepository.findByToken(authToken);
    if (auth != null) {
        LocalDateTime expirationTime = auth.getExpirationTime();
        LocalDateTime currentTime = LocalDateTime.now();
        if (currentTime.isBefore(expirationTime)) {
            authTokenRepository.delete(auth);
            return true;
        }
    }
    return false;
}
}