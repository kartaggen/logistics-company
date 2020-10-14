package com.f97808.logisticscompany.model;

import com.f97808.logisticscompany.validation.PasswordConfirmed;
import com.f97808.logisticscompany.validation.PasswordPolicy;
import com.f97808.logisticscompany.validation.UniqueEmail;
import com.f97808.logisticscompany.validation.UniqueUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@PasswordConfirmed
public class UserDto {

    @NotEmpty(message = "Please enter your first name")
    private String firstname;
    @NotEmpty(message = "Please enter your last name")
    private String lastname;
    @NotEmpty(message = "Please enter a username")
    @UniqueUsername
    private String username;
    @NotEmpty(message = "Please enter an email")
    @Email(message = "Email is not valid")
    @UniqueEmail
    private String email;
    @NotEmpty(message = "Please enter in a password")
    @PasswordPolicy
    private String password;
    @NotEmpty(message = "Please confirm your password")
    private String confirmPassword;

    public UserDto() {
    }

    public UserDto(@NotEmpty(message = "Please enter your first name") String firstname,
                   @NotEmpty(message = "Please enter your last name") String lastname,
                   @NotEmpty(message = "Please enter a username") String username,
                   @NotEmpty(message = "Please enter an email") @Email(message = "Email is not valid") String email,
                   @NotEmpty(message = "Please enter in a password") String password,
                   @NotEmpty(message = "Please confirm your password") String confirmPassword) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}