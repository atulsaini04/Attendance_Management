package com.example.myfirstproject.firstproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.myfirstproject.firstproject.entity.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {

    @Query("SELECT COUNT(l) > 0 FROM Login l WHERE l.emp_code = :empCode AND l.password = :password AND l.org_id = :orgId")
    boolean existsByEmpCodeAndPasswordAndOrgId(Long empCode, String password, Long orgId);

    @Query(value = "SELECT * FROM login WHERE emp_code = :empCode AND org_id = :orgId", nativeQuery = true)
    Login findByEmpCodeAndOrgId(@Param("empCode") Long empCode, @Param("orgId") Long orgId);

    @Query(value = "SELECT * FROM login WHERE emp_code=:empcode", nativeQuery = true)
    Login findByEmpCode(Long empcode);
}