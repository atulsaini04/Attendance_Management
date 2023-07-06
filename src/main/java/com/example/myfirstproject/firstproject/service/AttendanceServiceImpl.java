package com.example.myfirstproject.firstproject.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.myfirstproject.firstproject.entity.Attendance;
import com.example.myfirstproject.firstproject.entity.Employee;
import com.example.myfirstproject.firstproject.repository.AttendanceRepository;
import com.example.myfirstproject.firstproject.repository.EmployeeRepository;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, EmployeeRepository employeeRepository) {
        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
    }

    LocalDate current = LocalDate.now();

    @Override
    public boolean isEmployeePunchedIn(Long employeeId, LocalDate current) {

        Attendance todayattendance = attendanceRepository.findByEmployeeIdAndDate(employeeId, current);
        return todayattendance != null;
    }

    @Override
    public Attendance punchIn(Long employeeId) {

        // LocalDate current=LocalDate.now();
        if (isEmployeePunchedIn(employeeId, current)) {

            return null;
        }

        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);

        if (employeeOptional.isPresent()) {
            Attendance attendance = new Attendance();
            attendance.setEmpid(employeeOptional.get().getEmpid());
            attendance.setPunchIn(LocalDateTime.now());

            if (attendance.getDate() == current) {
                return null;
            }
            attendance.setDate(LocalDate.now());

            return attendanceRepository.save(attendance);
        }
        return null; // Employee not found
    }

    @Override
    public Attendance punchOut(Long employeeId) {

        Attendance attendance = attendanceRepository.findLatestAttendanceByEmployeeId(employeeId);
        if (attendance != null && attendance.getPunchOut() == null) {
            attendance.setPunchOut(LocalDateTime.now());
            return attendanceRepository.save(attendance);
        }
        return null; // No attendance record found or already punched out
    }

    @Override
    public List<Attendance> getAttendanceByEmployeeIdAndDateRange(Long employeeId, LocalDate startDate,
            LocalDate endDate) {
        return attendanceRepository.findByEmployeeIdAndDateBetween(employeeId, startDate, endDate);
    }

}
