package com.example.demo;

public class Department {
    private int department_id;
    private String department_name;
    private int location_id;
    private String country_id;

    //Constructor with every attributes
    public Department(int department_id, String department_name, int location_id, String country_id, String city, String street_address, String postal_code) {
        this.department_id = department_id;
        this.department_name = department_name;
        this.location_id = location_id;
        this.country_id = country_id;
        this.city = city;
        this.street_address = street_address;
        this.postal_code = postal_code;
    }

    //getters and setters
    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    private String city;
    private String street_address;
    private String postal_code;

    //constructor with only department id and name
    public Department(int department_id, String department_name) {
        this.department_id = department_id;
        this.department_name = department_name;
    }

    //getter and setters
    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }


}