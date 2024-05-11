package com.udea.energym.persistence.entity;

import java.sql.Time;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "clases")
public class ClasesEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_clases")
	private Long idClases;
	
	@Column(name = "nombre_clase")
	private String nombreClase;
	
	@Column(name = "tipo_clase")
	private String tipoClase;   //grupal, individual, personalizada
	
	@Column(name = "instructor")
	private String instructor;
	
	@Column(name = "ubicacion")
	private String ubicacion;
	
	@Column(name = "fecha_clase") 
	private LocalDate fechaClase;
	
	public LocalDate getFechaClase() {
		return fechaClase;
	}

	public void setFechaClase(LocalDate fechaClase) {
		this.fechaClase = fechaClase;
	}

	@Column(name = "hora")
	private Time hora;
	
	@Column(name = "capacidad_max")
	private int capacidadMax;
	
	/**
	 * @OneToMany = Una categoria puede tener muchas clases. 
	 * CascadeType.ALL = Cuando eliminemos una gategoria, podremos eliminar tambien las clases asociadas
	 * a esa categoria.
	 */
	@OneToMany(mappedBy = "clasesEnt", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<UsuarioClasesEntity> usuarioClases = new LinkedHashSet<>();
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria_id")
	private CategoriaEntity categoria;
}
