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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "citas")
public class CitaEntity {

	@Id
	@Column(name = "id_cita")
	private Long idCita;
	
	@Column(name = "titulo_cita")
	private String tituloCita;
	
	@Column(name = "descripcion_cita")
	private String descripcionC;
	
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
	
}
