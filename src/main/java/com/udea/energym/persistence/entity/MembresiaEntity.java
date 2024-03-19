package com.udea.energym.persistence.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "membresias")
public class MembresiaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_membresia")
	private Long idMembresia;
	
	@Column(name = "fecha_inicio")
	private LocalDate fechaInicio;
	
	@Column(name = "fecha_vencimiento")
	private LocalDate fechaVencimiento;
	
	@Column(name = "estado")
	private String estado; //activo, vencido, cancelado
	
	@JoinColumn(name = "cedula_usuarios")
	@ManyToOne(fetch = FetchType.EAGER)
	private UsuarioEntity usuarioEnt;
}
