package com.udea.energym.service.impl;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udea.energym.dto.Categoria;
import com.udea.energym.persistence.entity.CategoriaEntity;
import com.udea.energym.persistence.repository.ICategoriaRepository;
import com.udea.energym.service.ICategoriaService;

@Service
public class CategoriaServiceImpl implements ICategoriaService {
	
	@Autowired
	private ICategoriaRepository categoriaRepository;

	@Override
	public Set<CategoriaEntity> obtenerCategorias() {
		return new LinkedHashSet<>(categoriaRepository.findAll());
	}

	@Override
	public Categoria agregarCategoria(Categoria categoria) {
		CategoriaEntity categoriaEnt = categoriaRepository.save(dtoToEntity(categoria));
		categoria.setIdCategoria(categoriaEnt.getIdCategoria());
		return categoria;
	}

	@Override
	public Categoria obtenerCategoriaId(Long idCategoria) {
		CategoriaEntity categoriaEnt = null;
		Optional<CategoriaEntity> categoriaOptEnt = categoriaRepository.findById(idCategoria);
		Categoria categoria = null;
		
		if (categoriaOptEnt.isPresent()) {
			categoriaEnt = categoriaOptEnt.get();
			categoria = entityToDto(categoriaEnt);
		}
		return categoria;
	}

	@Override
	public String actualizarCategoria(Categoria categoria) {
		Optional<CategoriaEntity> catEntOpt = categoriaRepository.findById(categoria.getIdCategoria());
		if(catEntOpt.isPresent()) {
			CategoriaEntity categoriaEnt = catEntOpt.get();
			categoriaEnt.setTitulo(categoria.getTitulo());
			categoriaEnt.setDescripcion(categoria.getDescripcion());
			categoriaEnt.setNumPersonas(categoria.getNumPersonas());
			categoriaRepository.save(categoriaEnt);
			return "Categoria actualizada...";
		}
		return "La categoria no existe...";
	}

	@Override
	public String eliminarCategoria(Long idCategoria) {
		if(categoriaRepository.findById(idCategoria).isPresent()) {
			categoriaRepository.deleteById(idCategoria);
			return "Eliminado correctamente!";
		}
		return "Error! la categoria no existe";
	}
	
	private CategoriaEntity dtoToEntity(Categoria categoria) {
		CategoriaEntity categoriaEnt = new CategoriaEntity();
		categoriaEnt.setTitulo(categoria.getTitulo());
		categoriaEnt.setDescripcion(categoria.getDescripcion());
		categoriaEnt.setNumPersonas(categoria.getNumPersonas());
		return categoriaEnt;
	}
	
	private Categoria entityToDto(CategoriaEntity categoriaEnt) {
		Categoria categoria = new Categoria();
		categoria.setIdCategoria(categoriaEnt.getIdCategoria());
		categoria.setTitulo(categoriaEnt.getTitulo());
		categoria.setDescripcion(categoriaEnt.getDescripcion());
		categoria.setNumPersonas(categoriaEnt.getNumPersonas());
		return categoria;
	}
}
