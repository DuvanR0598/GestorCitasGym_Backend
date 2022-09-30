package com.udea.energym.persistence.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class RolEntity {

	@Id
	@Column(name = "dni_rol")
    private Long dniRol;
	
	@Column(name = "nombre")
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "rol")
    private Set<UsuarioRolEntity> usuarioRoles = new HashSet<>();

	public Long getDniRol() {
		return dniRol;
	}

	public void setDniRol(Long dniRol) {
		this.dniRol = dniRol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<UsuarioRolEntity> getUsuarioRoles() {
		return usuarioRoles;
	}

	public void setUsuarioRoles(Set<UsuarioRolEntity> usuarioRoles) {
		this.usuarioRoles = usuarioRoles;
	}
}
