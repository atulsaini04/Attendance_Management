package com.example.myfirstproject.firstproject.service;

import java.time.LocalDate;
import java.util.List;

import com.example.myfirstproject.firstproject.entity.Attendance;

public interface AttendanceService {
    Attendance punchIn(Long employeeId);

    Attendance punchOut(Long employeeId);

    List<Attendance> getAttendanceByEmployeeIdAndDateRange(Long employeeId, LocalDate startDate, LocalDate endDate);

    boolean isEmployeePunchedIn(Long employeeId, LocalDate current);

}
