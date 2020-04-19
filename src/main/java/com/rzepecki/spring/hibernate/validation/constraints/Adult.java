package com.rzepecki.spring.hibernate.validation.constraints;

import com.rzepecki.spring.hibernate.validation.validators.AdultValidatorForInteger;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AdultValidatorForInteger.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE})
public @interface Adult {
    String message() default "{com.rzepecki.spring.hibernate.validation.constraints.Adult.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
