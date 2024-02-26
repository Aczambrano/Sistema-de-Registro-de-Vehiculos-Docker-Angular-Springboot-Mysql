package com.gizlo.crud.security.response.userapi;

public class Company {
    private Address2 address;
    private String department;
    private String name;
    private String title;

    public Company(Address2 address, String department, String name, String title) {
        this.address = address;
        this.department = department;
        this.name = name;
        this.title = title;
    }

    public Address2 getAddress() {
        return this.address;
    }

    public String getDepartment() {
        return this.department;
    }

    public String getName() {
        return this.name;
    }

    public String getTitle() {
        return this.title;
    }

    public void setAddress(Address2 address) {
        this.address = address;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
