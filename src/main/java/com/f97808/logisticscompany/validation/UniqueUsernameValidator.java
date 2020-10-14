package com.f97808.logisticscompany.validation;

import com.f97808.logisticscompany.jpa.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserRepository repository;

    UniqueUsernameValidator(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return username != null && repository.findByUsername(username) == null;
    }

}
