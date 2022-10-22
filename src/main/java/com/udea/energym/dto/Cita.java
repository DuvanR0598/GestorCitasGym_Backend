package com.udea.energym.dto;

import java.sql.Time;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cita {
	
	private Long idCita;
	private String tituloCita;
	private String descripcionC;
	private LocalDate fecha;
	private Time hora;
	private String duracion;
	
}
