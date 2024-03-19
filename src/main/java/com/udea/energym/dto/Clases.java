package com.udea.energym.dto;

import java.sql.Time;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Clases {

	private Long idClases;
	private String nombreClase;
	private String tipoClase;   //grupal, individual, personalizada
	private String instructor;
	private String ubicacion;
	private LocalDate fechaClase;
	private Time hora;
	private int capacidadMax;
}
