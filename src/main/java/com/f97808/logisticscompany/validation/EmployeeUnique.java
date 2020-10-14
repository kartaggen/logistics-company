package com.f97808.logisticscompany.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmployeeUniqueValidator.class)
public @interface EmployeeUnique {
    String message() default "Employee with this name or email already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
