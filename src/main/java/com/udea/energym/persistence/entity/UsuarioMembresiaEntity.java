package com.udea.energym.persistence.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario_membresia")
public class UsuarioMembresiaEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioMembresiaId;

    @ManyToOne(fetch = FetchType.LAZY)
    private UsuarioEntity usuario;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private MembresiaEntity membresia;
    
    @Column(name = "fecha_asociacion")
    private LocalDate fechaAsociacion;
    
    @Column(name = "activo")
    private boolean activo;

}
