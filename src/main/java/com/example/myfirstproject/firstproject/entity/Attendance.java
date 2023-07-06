package com.example.myfirstproject.firstproject.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendid;

    @JoinColumn(name = "emp_id", referencedColumnName = "empid", insertable = false, updatable = false)
    @ManyToOne
    private Employee employee;

    @Column(name = "emp_id")
    private Long empid;

    @Column(name = "punch_in")
    private LocalDateTime punchIn;

    @Column(name = "punch_out")
    private LocalDateTime punchOut;

    @Column(name = "date")
    private LocalDate date;

    public Attendance() {
    }

    public Attendance(Long attendid, Employee employee, LocalDateTime punchIn, LocalDateTime punchOut, LocalDate date) {
        this.attendid = attendid;
        this.employee = employee;
        this.punchIn = punchIn;
        this.punchOut = punchOut;
        this.date = date;
    }

    public Long getAttendid() {
        return attendid;
    }

    public Long getEmpid() {
        return empid;
    }

    public void setEmpid(Long empid) {
        this.empid = empid;
    }

    public void setAttendid(Long attendid) {
        this.attendid = attendid;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDateTime getPunchIn() {
        return punchIn;
    }

    public void setPunchIn(LocalDateTime punchIn) {
        this.punchIn = punchIn;
    }

    public LocalDateTime getPunchOut() {
        return punchOut;
    }

    public void setPunchOut(LocalDateTime punchOut) {
        this.punchOut = punchOut;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
