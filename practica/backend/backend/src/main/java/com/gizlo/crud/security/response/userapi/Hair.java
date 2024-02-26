package com.gizlo.crud.security.response.userapi;

public class Hair {
    private String color;
    private String type;

    public Hair(String color, String type) {
        this.color = color;
        this.type = type;
    }

    public String getColor() {
        return this.color;
    }

    public String getType() {
        return this.type;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setType(String type) {
        this.type = type;
    }
}
