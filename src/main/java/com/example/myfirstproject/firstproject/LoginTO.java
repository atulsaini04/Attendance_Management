package com.example.myfirstproject.firstproject;

import java.io.Serializable;
import java.time.LocalDateTime;

public class LoginTO implements Serializable {

    private Long emp_code;
    private String password;
    private Long org_id;
    private LocalDateTime loginTime;;

    public LocalDateTime getLogin_time() {
        return loginTime;
    }

    public void setLogin_time(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public Long getEmp_code() {
        return emp_code;
    }

    public void setEmp_code(Long emp_code) {
        this.emp_code = emp_code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getOrg_id() {
        return org_id;
    }

    public void setOrg_id(Long org_id) {
        this.org_id = org_id;
    }

}
