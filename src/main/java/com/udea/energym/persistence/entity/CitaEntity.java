package com.udea.energym.persistence.entity;

import java.sql.Time;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "citas")
public class CitaEntity {

	@Id
	@Column(name = "id_cita")
	private Long idCita;
	
	@Column(name = "fecha")
	private LocalDate fecha;
	
	@Column(name = "hora")
	private Time hora;
	
	@Column(name = "duracion")
	private String duracion;
	
	/**
	 * FetchType.EAGER= Cuando consultemos una cita, la cita nos devolvera la categoria relacionada.
	 */
	@JoinColumn(name = "id_categoria")
	@ManyToOne(fetch = FetchType.EAGER)
	private CategoriaEntity categoriaEnt;
	
	@JoinColumn(name = "cedula_usuarios")
	@ManyToOne(fetch = FetchType.EAGER)
	private UsuarioEntity usuarioEnt;

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

	public CategoriaEntity getCategoriaEnt() {
		return categoriaEnt;
	}

	public void setCategoriaEnt(CategoriaEntity categoriaEnt) {
		this.categoriaEnt = categoriaEnt;
	}

	public UsuarioEntity getUsuarioEnt() {
		return usuarioEnt;
	}

	public void setUsuarioEnt(UsuarioEntity usuarioEnt) {
		this.usuarioEnt = usuarioEnt;
	}
	
	
	
}
