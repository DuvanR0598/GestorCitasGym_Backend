package com.udea.energym.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuario_rol")
public class UsuarioRolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioRolId;

    @ManyToOne(fetch = FetchType.EAGER)
    private UsuarioEntity usuario;

    @ManyToOne
    private RolEntity rol;

	public Long getUsuarioRolId() {
		return usuarioRolId;
	}

	public void setUsuarioRolId(Long usuarioRolId) {
		this.usuarioRolId = usuarioRolId;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public RolEntity getRol() {
		return rol;
	}

	public void setRol(RolEntity rol) {
		this.rol = rol;
	}
    
    
}

