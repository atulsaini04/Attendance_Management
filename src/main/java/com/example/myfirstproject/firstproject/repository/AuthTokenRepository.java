package com.example.myfirstproject.firstproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.myfirstproject.firstproject.entity.AuthToken;

public interface AuthTokenRepository extends JpaRepository<AuthToken,Long>{
    // AuthToken findByToken(String token);
    @Query(value = "SELECT * FROM auth_token WHERE token = :authToken", nativeQuery = true)
    AuthToken findByToken(@Param("authToken") String authToken);
    // @Query("SELECT a FROM AuthToken a WHERE a.token = :token AND a.valid = :valid")
    // AuthToken findByTokenAndValid(String token, boolean valid);

}
