package edu.eci.models;

import java.io.Serializable;

public class Car implements Serializable{
    private String licencePlate;
    private String brand;
    public Car(String licencePlate, String brand) {
        this.licencePlate = licencePlate;
        this.brand = brand;
    }

    public Car() {
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    
}
