package com.cgi.dentistapp.customValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = VisitTimeValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface VisitTimeConstraint {
 
    String message() default "Visit time is taken.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String field(); 
    String fieldMatch();
    
}