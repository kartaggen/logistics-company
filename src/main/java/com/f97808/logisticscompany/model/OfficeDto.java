package com.f97808.logisticscompany.model;

import com.f97808.logisticscompany.validation.OfficeUnique;

import javax.validation.constraints.NotEmpty;

@OfficeUnique
public class OfficeDto {

    private int id;
    @NotEmpty(message = "Please enter an office name")
    private String name;
    @NotEmpty(message = "Please enter an office address")
    private String address;

    public OfficeDto() {
    }

    public OfficeDto(@NotEmpty int id, @NotEmpty String name, @NotEmpty String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}