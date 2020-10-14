package com.f97808.logisticscompany.validation;

import com.f97808.logisticscompany.jpa.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserRepository repository;

    UniqueEmailValidator(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email != null && repository.findByEmail(email) == null;
    }

}
