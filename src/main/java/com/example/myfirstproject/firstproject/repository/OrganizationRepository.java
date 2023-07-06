package com.example.myfirstproject.firstproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myfirstproject.firstproject.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    // Add custom queries if needed
}