package com.udea.energym.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Especialidad {
	private Long dniEspecialidad;
	private String nombre;
	private Persona persona;
}
