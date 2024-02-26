package com.gizlo.crud.security.response.userapi;

public class Address2 {
    private String address;
    private String city;
    private Coordinates2 coordinates;
    private String postalCode;
    private String state;

    public Address2(String address, String city, Coordinates2 coordinates, String postalCode, String state) {
        this.address = address;
        this.city = city;
        this.coordinates = coordinates;
        this.postalCode = postalCode;
        this.state = state;
    }

    public String getAddress() {
        return this.address;
    }

    public String getCity() {
        return this.city;
    }

    public Coordinates2 getCoordinates() {
        return this.coordinates;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public String getState() {
        return this.state;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCoordinates(Coordinates2 coordinates) {
        this.coordinates = coordinates;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setState(String state) {
        this.state = state;
    }
}
