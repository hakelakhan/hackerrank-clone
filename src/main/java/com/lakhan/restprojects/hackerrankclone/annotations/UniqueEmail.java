package com.lakhan.restprojects.hackerrankclone.annotations;

import com.lakhan.restprojects.hackerrankclone.validations.UniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Retention(RUNTIME)
@Target(ElementType.FIELD)
public @interface UniqueEmail {
    String message() default "Email must be unique";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
