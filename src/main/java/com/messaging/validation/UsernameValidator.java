package com.messaging.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameValidator.UsernameValidatorImpl.class)
@Documented
public @interface UsernameValidator {
    String message() default "Username must be 5-20 letters or digits";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    class UsernameValidatorImpl implements ConstraintValidator<UsernameValidator, String> {
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (value == null) return false;
            return value.matches("^[A-Za-z0-9]{5,20}$");
        }
    }
}