package com.udea.energym.dto;

public class Cliente extends Persona {

    public Cliente(){
        super();
    }

    public Cliente(String cedula, String nombre, String apellido, String celular, String email, int edad, char genero, float peso, float altura){
        super(cedula, nombre, apellido, celular, email, edad, genero, peso, altura, null);
    }
}