package com.f97808.logisticscompany.model;

import com.f97808.logisticscompany.validation.ClientUnique;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@ClientUnique
public class ClientDto {
    private int id;
    @NotEmpty(message = "Please enter your firstname")
    private String firstname;
    @NotEmpty(message = "Please enter your lastname")
    private String lastname;
    @NotEmpty(message = "Please enter a username")
    private String username;
    @NotEmpty(message = "Please enter an email")
    @Email(message = "Email is not valid")
    private String email;

    public ClientDto() {
    }

    public ClientDto(@NotEmpty int id,
                     @NotEmpty(message = "Please enter your firstname") String firstname,
                     @NotEmpty(message = "Please enter your lastname") String lastname,
                     @NotEmpty(message = "Please enter a username") String username,
                     @NotEmpty(message = "Please enter an email") @Email(message = "Email is not valid") String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
