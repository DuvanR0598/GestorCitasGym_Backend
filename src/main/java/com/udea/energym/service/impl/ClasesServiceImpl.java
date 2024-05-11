package com.udea.energym.service.impl;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.udea.energym.dto.Categoria;
import com.udea.energym.dto.Clases;
import com.udea.energym.persistence.entity.CategoriaEntity;
import com.udea.energym.persistence.entity.ClasesEntity;
import com.udea.energym.persistence.entity.UsuarioClasesEntity;
import com.udea.energym.persistence.entity.UsuarioEntity;
import com.udea.energym.persistence.repository.ICategoriaRepository;
import com.udea.energym.persistence.repository.IClasesRepository;
import com.udea.energym.persistence.repository.IUsuarioRepository;
import com.udea.energym.service.IClasesService;

@Service
public class ClasesServiceImpl implements IClasesService {
	
	@Autowired
	private IClasesRepository clasesRepository;
	
	@Autowired 
	private ICategoriaRepository categoriaRepository;
	
	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Override
	public Set<Clases> obtenerClases() {
		Set<ClasesEntity> clasesEntities = new LinkedHashSet<>(clasesRepository.findAll());
	    Set<Clases> clasesDTOs = new LinkedHashSet<>();
	    for (ClasesEntity clasesEntity : clasesEntities) {
	        Clases clasesDTO = entityToDto(clasesEntity);
	        clasesDTOs.add(clasesDTO);
	    }
	    return clasesDTOs;
	}

	@Override
	public Clases guardarClase(Clases clases) {
		// Obtener el nombre de usuario del usuario actual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        // Buscar el usuario por el nombre de usuario
        UsuarioEntity usuarioEnt = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con el nombre de usuario: " + username));

        // Convertir el DTO a una entidad Clases
        ClasesEntity claseEnt = dtoToEntity(clases);
        
        // Asociar al usuario con la clase
        UsuarioClasesEntity usuarioClaseEnt = new UsuarioClasesEntity();
        usuarioClaseEnt.setUsuarioEnt(usuarioEnt);
        usuarioClaseEnt.setClasesEnt(claseEnt);
        claseEnt.getUsuarioClases().add(usuarioClaseEnt);
        
        // Guardar la clase
        claseEnt = clasesRepository.save(claseEnt);
        
        // Devolver la clase convertida a DTO
		return entityToDto(claseEnt);
	}

	@Override
	public Clases obtenerClaseId(Long idClase) {
		ClasesEntity claseEnt = null;
		Optional<ClasesEntity> claseOptEnt = clasesRepository.findById(idClase);
		Clases clases = null;
		
		if (claseOptEnt.isPresent()) {
			claseEnt = claseOptEnt.get();
			clases = entityToDto(claseEnt);
		}
		return clases;
	}

	@Override
	public String actualizarClase(Clases clases) {
		Optional<ClasesEntity> claEntOpt = clasesRepository.findById(clases.getIdClases());
		if(claEntOpt.isPresent()) {
			ClasesEntity clasesEnt = claEntOpt.get();
			clasesEnt.setNombreClase(clases.getNombreClase());
			clasesEnt.setTipoClase(clases.getTipoClase());
			clasesEnt.setInstructor(clases.getInstructor());
			clasesEnt.setUbicacion(clases.getUbicacion());
			clasesEnt.setFechaClase(clases.getFechaClase());
			clasesEnt.setHora(clases.getHora());
			clasesEnt.setCapacidadMax(clases.getCapacidadMax());
			clasesRepository.save(clasesEnt);
			return "Clase actualizada...";
		}
		return "La clase no existe...";
	}

	@Override
	public String eliminarClase(Long idClase) {
		if(clasesRepository.findById(idClase).isPresent()) {
			clasesRepository.deleteById(idClase);
			return "¡Clase eliminada correctamente!";
		}
		return "Error! la clase no existe";
	}
	
	private ClasesEntity dtoToEntity(Clases clases) {
		ClasesEntity clasesEnt = new ClasesEntity();
		clasesEnt.setNombreClase(clases.getNombreClase());
		clasesEnt.setTipoClase(clases.getTipoClase());
		clasesEnt.setInstructor(clases.getInstructor());
		clasesEnt.setUbicacion(clases.getUbicacion());
		clasesEnt.setFechaClase(clases.getFechaClase());
		clasesEnt.setHora(clases.getHora());
		clasesEnt.setCapacidadMax(clases.getCapacidadMax());
		
		// Obtener la categoría del DTO y convertirla en una entidad de categoría
		CategoriaEntity categoriaEnt = categoriaRepository.findById(clases.getCategoria().getIdCategoria())
				.orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada"));
		
		clasesEnt.setCategoria(categoriaEnt);
		
		return clasesEnt;
	}
	
	private Clases entityToDto(ClasesEntity clasesEnt) {
		Clases clases = new Clases();
		clases.setIdClases(clasesEnt.getIdClases());
		clases.setNombreClase(clasesEnt.getNombreClase());
		clases.setTipoClase(clasesEnt.getTipoClase());
		clases.setInstructor(clasesEnt.getInstructor());
		clases.setUbicacion(clasesEnt.getUbicacion());
		clases.setFechaClase(clasesEnt.getFechaClase());
		clases.setHora(clasesEnt.getHora());
		clases.setCapacidadMax(clasesEnt.getCapacidadMax());
		
		// Obtener la categoría asociada a la clase y mapearla al DTO
	    Categoria categoria = new Categoria();
	    categoria.setIdCategoria(clasesEnt.getCategoria().getIdCategoria());
	    categoria.setTitulo(clasesEnt.getCategoria().getTitulo());
	    categoria.setDescripcion(clasesEnt.getCategoria().getDescripcion());
	    // Asignar la categoría al DTO de la clase
	    clases.setCategoria(categoria);
		return clases;
	}
}
