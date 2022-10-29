package com.udea.energym.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario {

	private Long cedula;
	private String nombre;
	private String apellido;
	private char genero;
	private LocalDate fechaNacimiento;
	private String celular;
	private String email;
	private float peso;
	private float altura;
	private String username;
	private String password;
}
