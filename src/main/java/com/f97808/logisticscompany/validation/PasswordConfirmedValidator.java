package com.f97808.logisticscompany.validation;

import com.f97808.logisticscompany.model.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConfirmedValidator implements ConstraintValidator<PasswordConfirmed, Object> {

    @Override
    public boolean isValid(Object user, ConstraintValidatorContext context) {
        String password = ((UserDto) user).getPassword();
        String confirmedPassword = ((UserDto) user).getConfirmPassword();
        return password.equals(confirmedPassword);
    }

}
