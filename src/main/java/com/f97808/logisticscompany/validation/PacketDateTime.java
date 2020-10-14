package com.f97808.logisticscompany.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PacketDateTimeValidator.class)
public @interface PacketDateTime {
    String message() default "Packet has invalid date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
