package com.udea.energym.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Persona {
    private String cedula;
    private String nombre;
    private String apellido;
    private char genero;
    private int edad;
    private String celular;
    private String email;
    private float peso;
    private float altura;
    private String profesion;

    public Persona(){
        this.setPeso(0);
        this.setAltura(0);
        this.setCedula(null);
        this.setApellido(null);
        this.setCelular(null);
        this.setEmail(null);
        this.setEdad(0);
        this.setGenero('G');
        this.setNombre(null);
        this.setPeso(0);
        this.setAltura(0);
        this.setProfesion(null);
    }

    public Persona(String cedula, String nombre, String apellido, String celular, String email, int edad, char genero, float peso, float altura, String profesion){
        this.setCedula(cedula);
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setCelular(celular);
        this.setEmail(email);
        this.setEdad(edad);
        this.setGenero(genero);
        this.setPeso(peso);
        this.setAltura(altura);
        this.setProfesion(profesion);
    }
}
