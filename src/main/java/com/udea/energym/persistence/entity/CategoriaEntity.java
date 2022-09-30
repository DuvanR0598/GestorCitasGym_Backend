package com.udea.energym.persistence.entity;

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
@Table(name = "categorias")
public class CategoriaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_categoria")
	private Long idCategoria;
	
	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "num_personas")
	private int numPersonas;
	
	/**
	 * @OneToMany = Una categoria puede tener muchas citas. 
	 * CascadeType.ALL = Cuando eliminemos una gategoria, podremos eliminar tambien las citas asociadas
	 * a esa categoria.
	 */
	@OneToMany(mappedBy = "categoriaEnt", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<CitaEntity> citas = new LinkedHashSet<>();

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getNumPersonas() {
		return numPersonas;
	}

	public void setNumPersonas(int numPersonas) {
		this.numPersonas = numPersonas;
	}

	public Set<CitaEntity> getCitas() {
		return citas;
	}

	public void setCitas(Set<CitaEntity> citas) {
		this.citas = citas;
	}
	
	
	
}
