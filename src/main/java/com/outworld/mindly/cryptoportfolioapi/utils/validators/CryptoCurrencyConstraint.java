package com.outworld.mindly.cryptoportfolioapi.utils.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CryptoCurrencyValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CryptoCurrencyConstraint {
    String message() default "No such cryptocurrency";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}