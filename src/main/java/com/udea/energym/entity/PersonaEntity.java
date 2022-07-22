package com.udea.energym.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "persona")
public class PersonaEntity {
	
	@Id
	@Column(name = "cedula")
	private Long cedula;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "apellido")
	private String apellido;
	
	@Column(name = "genero")
	private char genero;
	
	@Column(name = "edad")
	private LocalDate edad;
	
	@Column(name = "celular")
	private String celular;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "peso")
	private float peso;
	
	@Column(name = "altura")
	private float altura;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
    private List<RolEntity> listaRoles;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
	private List<EspecialidadEntity> listaEspecialidades;
}
