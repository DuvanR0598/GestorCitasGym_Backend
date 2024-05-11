package com.udea.energym.persistence.entity;

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
@Table(name = "usuario_clases")
public class UsuarioClasesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usuario_clases_id")
	private Long usuarioClasesId;
	
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
