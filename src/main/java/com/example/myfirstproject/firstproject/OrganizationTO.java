package com.example.myfirstproject.firstproject;

public class OrganizationTO {
    private Long orgId;
    private String name;

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrganizationTO(Long orgId, String name) {
        this.orgId = orgId;
        this.name = name;
    }

    public OrganizationTO() {
    }

}
