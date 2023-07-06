package com.example.myfirstproject.firstproject.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AuthToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token")
    private String token;
    @ManyToOne
    @JoinColumn(name = "emp_code", referencedColumnName = "emp_code", insertable = false, updatable = false)
    private Login login;
    @Column(name = "emp_code")
    private Long emp_code;
    @Column(name = "expiration_time")
    private LocalDateTime expirationTime;

   

    public AuthToken() {}


    
    public AuthToken(Long id, String token, Login login, Long emp_code, LocalDateTime expirationTime) {
        this.id = id;
        this.token = token;
        this.login = login;
        this.emp_code = emp_code;
        this.expirationTime = expirationTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Long getLogin_time() {
        return emp_code;
    }

    public void setLogin_time(Long emp_code) {
        this.emp_code = emp_code;
    }

    public Long getEmp_code() {
        return emp_code;
    }

    public void setEmp_code(Long emp_code) {
        this.emp_code = emp_code;
    }


}
