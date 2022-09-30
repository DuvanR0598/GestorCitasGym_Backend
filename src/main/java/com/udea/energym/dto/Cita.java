package com.udea.energym.dto;

import java.sql.Time;
import java.time.LocalDate;


public class Cita {
	
	private Long idCita;
	private LocalDate fecha;
	private Time hora;
	private String duracion;
//	private Categoria categoria;
//	private Usuario usuario;
	public Long getIdCita() {
		return idCita;
	}
	public void setIdCita(Long idCita) {
		this.idCita = idCita;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
		this.hora = hora;
	}
	public String getDuracion() {
		return duracion;
	}
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	
	
}
