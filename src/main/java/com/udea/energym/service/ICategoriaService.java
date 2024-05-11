package com.udea.energym.service;

import java.util.Set;

import com.udea.energym.dto.Categoria;
import com.udea.energym.persistence.entity.CategoriaEntity;

public interface ICategoriaService {
	
	Set<CategoriaEntity> obtenerCategorias();
	
	Categoria guardarCategoria(Categoria categoria);
	
	Categoria obtenerCategoriaId(Long idCategoria);
	
	String actualizarCategoria(Categoria categoria);
	
	String eliminarCategoria(Long idCategoria);

}
