package com.gizlo.crud.security.response.userapi;

public class Coordinates2 {
    private Double lat;
    private Double lng;

    public Coordinates2(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Double getLat() {
        return this.lat;
    }

    public Double getLng() {
        return this.lng;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
