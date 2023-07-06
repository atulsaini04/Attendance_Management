package com.example.myfirstproject.firstproject.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.myfirstproject.firstproject.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    @Query(value = "SELECT * FROM attendance WHERE emp_id = :employeeId AND punch_out IS NULL", nativeQuery = true)
    Attendance findByEmployeeIdAndPunchOutIsNull(@Param("employeeId") Long employeeId);

    @Query(value = "SELECT * FROM attendance WHERE emp_id = :employeeId ORDER BY date DESC, punch_in LIMIT 1", nativeQuery = true)
    Attendance findLatestAttendanceByEmployeeId(Long employeeId);

    @Query(value = "SELECT * FROM attendance WHERE emp_id = :employeeId AND date = :date AND punch_in is not null", nativeQuery = true)
    Attendance findByEmployeeIdAndDate(Long employeeId, LocalDate date);

    @Query(value = "SELECT * FROM attendance WHERE emp_id = :employeeId AND date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<Attendance> findByEmployeeIdAndDateBetween(@Param("employeeId") Long employeeId,
            @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
