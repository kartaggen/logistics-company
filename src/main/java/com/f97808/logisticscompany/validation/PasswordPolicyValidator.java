package com.f97808.logisticscompany.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordPolicyValidator implements ConstraintValidator<PasswordPolicy, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        return password.length() >= 10 && password.length() <= 128 &&
                !password.equals(password.toLowerCase()) &&
                !password.equals(password.toUpperCase()) &&
                !password.matches("[A-Za-z0-9 ]*");
    }

}
