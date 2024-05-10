package com.udea.energym.persistence.entity;

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
@Table(name = "inscripciones")
public class InscripcionesEntity {

	@Id
	@Column(name = "id_inscripcion")
	private Long idInscripcion;
	
	@Column(name = "fecha_inscripcion")
	private LocalDate fechaInscripcion;
	
	
	@JoinColumn(name = "cedula_usuarios")
	@ManyToOne(fetch = FetchType.EAGER)
	private UsuarioEntity usuarioEnt;
	
	/**
	 * FetchType.EAGER= Cuando consultemos una inscripcion, la inscripcion nos devolvera la clase relacionada.
	 */
	@JoinColumn(name = "id_clases")
	@ManyToOne(fetch = FetchType.EAGER)
	private ClasesEntity clasesEnt;
	
	
	
}
