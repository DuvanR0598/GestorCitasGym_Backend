package com.udea.energym.persistence.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	
	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "fecha_inicio")
	private LocalDate fechaInicio;
	
	@Column(name = "fecha_vencimiento")
	private LocalDate fechaVencimiento;
	
	@Column(name = "activa")
	private boolean activa;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "membresia")
    private Set<UsuarioMembresiaEntity> usuarioMembresias = new HashSet<>();
	
//	@JoinColumn(name = "cedula_usuarios")
//	@ManyToOne(fetch = FetchType.EAGER)
//	private UsuarioEntity usuarioEnt;
}
