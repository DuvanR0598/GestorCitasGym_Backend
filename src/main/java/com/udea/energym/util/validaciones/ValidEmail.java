package com.udea.energym.util.validaciones;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEmail {

    String message() default "El correo electrónico no es válido. Debe contener un '@' y un dominio '.com' o '.co'.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
 // Parámtero opcional que permite configurar los dominios válidos
    String[] allowedDomains() default {"com", "co"};
}
