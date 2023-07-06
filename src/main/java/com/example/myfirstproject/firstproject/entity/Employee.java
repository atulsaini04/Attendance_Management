package com.example.myfirstproject.firstproject.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empid;
    @Column(unique = true, nullable = false)
    private Long empcode;

    private String name;
    // private String password;

    private Long mobile;
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    // @ManyToOne
    // @JoinColumn(name = "dutyduration_id")
    // private DutyDuration dutyduration;

    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "jobid", insertable = false, updatable = false)
    private Job job;

    public Employee() {
    }

    public Employee(Long empid, Long empcode, String name, Long mobile, Organization organization, Job job) {
        this.empid = empid;
        this.empcode = empcode;
        this.name = name;
        this.mobile = mobile;
        this.organization = organization;
        this.job = job;
    }

    public Long getEmpid() {
        return empid;
    }

    public void setEmpid(Long empid) {
        this.empid = empid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Long getEmpcode() {
        return empcode;
    }

    public void setEmpcode(Long empcode) {
        this.empcode = empcode;
    }

}
