package com.f97808.logisticscompany.validation;

import com.f97808.logisticscompany.model.PasswordDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConfirmedUpdateValidator implements ConstraintValidator<PasswordConfirmedUpdate, Object> {

    @Override
    public boolean isValid(Object pass, ConstraintValidatorContext context) {
        String password = ((PasswordDto) pass).getNewPass();
        String confirmedPassword = ((PasswordDto) pass).getNewPassConfirm();
        return password.equals(confirmedPassword);
    }

}
