package com.udea.energym.service;

import java.util.Set;

import com.udea.energym.dto.Membresia;
import com.udea.energym.persistence.entity.MembresiaEntity;

public interface IMembresiaService {
	
	Set<MembresiaEntity> obtenerMembresias();

	Membresia guardarMembresia (Membresia membresia);
	
	Membresia obtenerMembresiaId(Long idMembresia);
	
	String actualizarMembresia(Membresia membresia);
	
	String eliminarMembresia(Long idMembresia);
}
