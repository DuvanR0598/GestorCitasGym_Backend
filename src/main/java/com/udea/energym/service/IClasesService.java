package com.udea.energym.service;

import java.util.Set;

import com.udea.energym.dto.Clases;
import com.udea.energym.persistence.entity.ClasesEntity;

public interface IClasesService {

	Set<ClasesEntity> obtenerClases();
	
	Clases guardarClase(Clases clases);
	
	Clases obtenerClaseId(Long idClase);
	
	String actualizarClase(Clases clases);
	
	String eliminarClase(Long idClase);
}
