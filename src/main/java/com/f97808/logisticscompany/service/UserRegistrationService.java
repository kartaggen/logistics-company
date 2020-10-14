package com.f97808.logisticscompany.service;


import com.f97808.logisticscompany.model.UserDto;

public interface UserRegistrationService {
    void createUser(UserDto user);

    void addAdminIfNone();
}
