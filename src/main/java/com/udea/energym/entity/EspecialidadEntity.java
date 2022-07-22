package com.udea.energym.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "especialidad")
public class EspecialidadEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dni_especialidad")
	private Long dniEspecialidad;
	
	@Column(name = "nombre")
	private String nombre;
	
	@ManyToOne
    @JoinColumn(name = "cedula_persona", nullable = false, updatable = false)
	private PersonaEntity persona;
}
