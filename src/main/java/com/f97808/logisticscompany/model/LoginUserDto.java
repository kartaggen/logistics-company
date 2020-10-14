package com.f97808.logisticscompany.model;

import javax.validation.constraints.NotEmpty;

public class LoginUserDto {

    @NotEmpty(message = "Please enter a username")
    private String username;
    @NotEmpty(message = "Please enter in a password")
    private String password;

    public LoginUserDto() {
    }

    public LoginUserDto(
            @NotEmpty(message = "Please enter a username") String username,
            @NotEmpty(message = "Please enter in a password") String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}