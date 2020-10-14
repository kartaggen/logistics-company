package com.f97808.logisticscompany.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidPacketValidator.class)
public @interface ValidPacket {
    String message() default "Packet has invalid sender and/or receiver";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
