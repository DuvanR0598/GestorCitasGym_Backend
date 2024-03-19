package com.udea.energym.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Membresia {
	
	private Long idMembresia;
	private LocalDate fechaInicio;
	private LocalDate fechaVencimiento;
	private String estado; //activo, vencido, cancelado

}
