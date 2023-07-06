package com.example.myfirstproject.firstproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myfirstproject.firstproject.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}