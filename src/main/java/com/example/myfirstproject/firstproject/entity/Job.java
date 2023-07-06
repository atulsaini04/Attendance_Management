package com.example.myfirstproject.firstproject.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobid;
    @Column(unique = true, nullable = false)
    private String jobprofile;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "fixed_duration")
    private String fixedDuration; // Assuming duration is stored in minutes

    public Job() {
    }

    public Job(Long jobid, String jobprofile, LocalDateTime startTime, LocalDateTime endTime, String fixedDuration) {
        this.jobid = jobid;
        this.jobprofile = jobprofile;
        this.startTime = startTime;
        this.endTime = endTime;
        this.fixedDuration = fixedDuration;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getFixedDuration() {
        return fixedDuration;
    }

    public void setFixedDuration(String fixedDuration) {
        this.fixedDuration = fixedDuration;
    }

    public Long getJobid() {
        return jobid;
    }

    public void setJobid(Long jobid) {
        this.jobid = jobid;
    }

    public String getJobprofile() {
        return jobprofile;
    }

    public void setJobprofile(String jobprofile) {
        this.jobprofile = jobprofile;
    }

}
