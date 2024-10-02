package com.udea.energym.util.validaciones;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    private String[] allowedDomains;

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
        // Aquí inicializamos los dominios permitidos de la anotación
        this.allowedDomains = constraintAnnotation.allowedDomains();
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) {
            return false;
        }

        // Construimos un patrón dinámico en función de los dominios permitidos
        String domainPattern = String.join("|", allowedDomains);
        String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.(" + domainPattern + ")$";
        return Pattern.compile(emailPattern).matcher(email).matches();
    }
}
