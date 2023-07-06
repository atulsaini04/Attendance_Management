package com.example.myfirstproject.firstproject.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
// @Table(name="User Data")
public class Login implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long userId;

    private String password;
    @ManyToOne
    @JoinColumn(name = "org_id", referencedColumnName = "orgid", insertable = false, updatable = false)
    private Organization organization;
    @Column(name = "org_id")
    private Long org_id;
    // getters and setters

    @JoinColumn(name = "emp_code", referencedColumnName = "empcode", insertable = false, updatable = false)
    @ManyToOne
    private Employee employee;
    @Column(name = "emp_code")
    private Long emp_code;

    public Login() {
    }

    public Login(Long userId, LocalDateTime loginTime, String password, Organization organization, Long org_id,
            Employee employee, Long emp_code) {
        this.userId = userId;
        this.password = password;
        this.organization = organization;
        this.org_id = org_id;
        this.employee = employee;
        this.emp_code = emp_code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Long getEmp_code() {
        return emp_code;
    }

    public void setEmp_code(Long emp_code) {
        this.emp_code = emp_code;
    }

    public Long getOrg_id() {
        return org_id;
    }

    public void setOrg_id(Long org_id) {
        this.org_id = org_id;
    }

}