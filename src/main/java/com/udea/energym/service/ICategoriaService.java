package com.udea.energym.service;

import java.util.Set;

import com.udea.energym.dto.Categoria;

public interface ICategoriaService {
	
	Set<Categoria> obtenerCategorias();
	
	Categoria guardarCategoria(Categoria categoria);
	
	Categoria obtenerCategoriaId(Long idCategoria);
	
	String actualizarCategoria(Categoria categoria);
	
	String eliminarCategoria(Long idCategoria);

}
