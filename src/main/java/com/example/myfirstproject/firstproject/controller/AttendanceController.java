package com.example.myfirstproject.firstproject.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.myfirstproject.firstproject.entity.Attendance;
import com.example.myfirstproject.firstproject.entity.AuthToken;
import com.example.myfirstproject.firstproject.repository.AuthTokenRepository;
import com.example.myfirstproject.firstproject.repository.EmployeeRepository;
import com.example.myfirstproject.firstproject.repository.LoginRepository;
import com.example.myfirstproject.firstproject.service.AttendanceService;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    private final AttendanceService attendanceService;
    @Autowired
    private final AuthTokenRepository authTokenRepository;
    public AttendanceController(AttendanceService attendanceService, LoginRepository loginRepository,
            EmployeeRepository employeeRepository, AuthTokenRepository authTokenRepository) {
        this.attendanceService = attendanceService;
        this.authTokenRepository = authTokenRepository;
    }

    @PostMapping("/punch-in/{employeeId}")
    public ResponseEntity<String> punchIn(@PathVariable Long employeeId,
            @RequestHeader("authToken") String authToken) {
        // Check if the user is authorized and logged in
        if (!isLoggedIn(authToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authorized or not logged in");
        }

        Attendance attendance = attendanceService.punchIn(employeeId);
        if (attendance != null) {
            return ResponseEntity.ok("Punch In successful");
        } else {
            return ResponseEntity.badRequest().body("Unable to punch in. Employee not found or already punched In.");
        }
    }

    public boolean isLoggedIn(String authToken) {
        AuthToken token = authTokenRepository.findByToken(authToken);
        if (token != null && token.getExpirationTime().isAfter(LocalDateTime.now())) {
            // Token is valid and not expired
            return true;
        }
        return false;
    }

    @PostMapping("/punch-out/{employeeId}")
    public ResponseEntity<String> punchOut(@PathVariable Long employeeId,
            @RequestHeader("authToken") String authToken) {
        if (!isLoggedIn(authToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authorized or not logged in");
        }

        Attendance attendance = attendanceService.punchOut(employeeId);
        if (attendance != null) {
            return ResponseEntity.ok("Punch Out successful");
        } else {
            return ResponseEntity.badRequest().body("Unable to punch out. Employee not found or already punched out.");
        }
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<Attendance>> getAttendanceByEmployeeId(@PathVariable Long employeeId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestHeader("authToken") String authToken) {
        if (!isLoggedIn(authToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Attendance> attendanceList = attendanceService.getAttendanceByEmployeeIdAndDateRange(employeeId, startDate,
                endDate);
        if (!attendanceList.isEmpty()) {
            return ResponseEntity.ok(attendanceList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
