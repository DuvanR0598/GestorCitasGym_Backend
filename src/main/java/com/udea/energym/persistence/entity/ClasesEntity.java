package com.udea.energym.persistence.entity;

import java.sql.Time;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
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
	 * @OneToMany = Una categoria puede tener muchas citas. 
	 * CascadeType.ALL = Cuando eliminemos una gategoria, podremos eliminar tambien las citas asociadas
	 * a esa categoria.
	 */
	@OneToMany(mappedBy = "clasesEnt", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<InscripcionesEntity> inscripciones = new LinkedHashSet<>();

	public Long getIdClases() {
		return idClases;
	}

	public void setIdClases(Long idClases) {
		this.idClases = idClases;
	}

	public String getNombreClase() {
		return nombreClase;
	}

	public void setNombreClase(String nombreClase) {
		this.nombreClase = nombreClase;
	}

	public String getTipoClase() {
		return tipoClase;
	}

	public void setTipoClase(String tipoClase) {
		this.tipoClase = tipoClase;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public int getCapacidadMax() {
		return capacidadMax;
	}

	public void setCapacidadMax(int capacidadMax) {
		this.capacidadMax = capacidadMax;
	}

	public Set<InscripcionesEntity> getInscripciones() {
		return inscripciones;
	}

	public void setInscripciones(Set<InscripcionesEntity> inscripciones) {
		this.inscripciones = inscripciones;
	}

	public Set<InscripcionesEntity> getCitas() {
		return inscripciones;
	}

	public void setCitas(Set<InscripcionesEntity> citas) {
		this.inscripciones = citas;
	}
	
	
	
}
