package com.example.myfirstproject.firstproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.myfirstproject.firstproject.entity.Organization;
import com.example.myfirstproject.firstproject.repository.OrganizationRepository;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }
}
