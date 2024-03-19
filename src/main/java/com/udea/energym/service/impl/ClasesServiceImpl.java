package com.udea.energym.service.impl;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udea.energym.dto.Clases;
import com.udea.energym.persistence.entity.ClasesEntity;
import com.udea.energym.persistence.repository.IClasesRepository;
import com.udea.energym.service.IClasesService;

@Service
public class ClasesServiceImpl implements IClasesService {
	
	@Autowired
	private IClasesRepository clasesRepository;

	@Override
	public Set<ClasesEntity> obtenerClases() {
		return new LinkedHashSet<>(clasesRepository.findAll());
	}

	@Override
	public Clases guardarClase(Clases clases) {
		ClasesEntity claseEnt = clasesRepository.save(dtoToEntity(clases));
		clases.setIdClases(claseEnt.getIdClases());
		return clases;
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
			return "Categoria actualizada...";
		}
		return "La categoria no existe...";
	}

	@Override
	public String eliminarClase(Long idClase) {
		if(clasesRepository.findById(idClase).isPresent()) {
			clasesRepository.deleteById(idClase);
			return "Eliminado correctamente!";
		}
		return "Error! la categoria no existe";
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
		return clases;
	}
}
