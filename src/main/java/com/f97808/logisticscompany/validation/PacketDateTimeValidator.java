package com.f97808.logisticscompany.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PacketDateTimeValidator implements ConstraintValidator<PacketDateTime, String> {

    @Override
    public boolean isValid(String date, ConstraintValidatorContext context) {
        if (date == null) return false;
        try {
            LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
