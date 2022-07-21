package com.udea.energym.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Profesional extends Persona{

    public Profesional(){
        super();
    }

    public Profesional(String cedula, String nombre, String apellido, String celular, String email, int edad, char genero, String profesion){
        super(cedula, nombre, apellido, celular, email, edad, genero, 0, 0, profesion);
    }
}