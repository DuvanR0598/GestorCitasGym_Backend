package com.udea.energym.service;

import java.util.List;
import java.util.Set;

import com.udea.energym.dto.Categoria;
import com.udea.energym.dto.Clases;

public interface IClasesService {

	Set<Clases> obtenerClases();
	
	Clases guardarClase(Clases clases);
	
	Clases obtenerClaseId(Long idClase);
	
	String actualizarClase(Clases clases);
	
	String eliminarClase(Long idClase);
	
	List<Clases> listarClasesDeUnaCategoria(Long idCategoria);
	
	List<Clases> obtenerClasesActivas();
	
	List<Clases> listarClasesActivasDeUnaCategoria(Categoria categoria);
}
