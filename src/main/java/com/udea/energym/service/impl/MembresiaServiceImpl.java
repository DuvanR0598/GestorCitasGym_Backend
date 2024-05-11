package com.udea.energym.service.impl;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udea.energym.dto.Membresia;
import com.udea.energym.persistence.entity.MembresiaEntity;
import com.udea.energym.persistence.repository.IMembresiaRepository;
import com.udea.energym.service.IMembresiaService;

@Service
public class MembresiaServiceImpl implements IMembresiaService {
	
	@Autowired
	private IMembresiaRepository membresiaRepository;

	@Override
	public Set<MembresiaEntity> obtenerMembresias() {
		return new LinkedHashSet<>(membresiaRepository.findAll());
	}
	
	@Override
	public Membresia guardarMembresia(Membresia membresia) {
		MembresiaEntity membresiaEnt = membresiaRepository.save(dtoToEntity(membresia));
		membresia.setIdMembresia(membresiaEnt.getIdMembresia());
		return membresia;
	}
	
	@Override
	public Membresia obtenerMembresiaId(Long idMembresia) {
		MembresiaEntity membresiaEnt = null;
		Optional<MembresiaEntity> membresiaOptEnt = membresiaRepository.findById(idMembresia);
		Membresia membresia = null;
		
		if (membresiaOptEnt.isPresent()) {
			membresiaEnt =  membresiaOptEnt.get();
			membresia = entityToDto(membresiaEnt);
		}
		return membresia;
	}

	@Override
	public String actualizarMembresia(Membresia membresia) {
		Optional<MembresiaEntity> membresiaOptEnt = membresiaRepository.findById(membresia.getIdMembresia());
		if(membresiaOptEnt.isPresent()) {
			MembresiaEntity membresiaEnt = membresiaOptEnt.get();
			membresiaEnt.setTitulo(membresia.getTitulo());
			membresiaEnt.setFechaInicio(membresia.getFechaInicio());
			membresiaEnt.setFechaVencimiento(membresia.getFechaVencimiento());
			membresiaEnt.setEstado(membresia.getEstado());
			return "Membresia actualizada";
		}
		return "La membresia no existe...";
	}

	@Override
	public String eliminarMembresia(Long idMembresia) {
		if(membresiaRepository.findById(idMembresia).isPresent()) {
			membresiaRepository.deleteById(idMembresia);
			return "¡Membresia eliminada correctamente!";
		}
		return "Error! la membresia no existe";
	}
	
	private MembresiaEntity dtoToEntity (Membresia membresia) {
		MembresiaEntity membresiaEnt = new MembresiaEntity();
		
		membresiaEnt.setTitulo(membresia.getTitulo());
		membresiaEnt.setFechaInicio(membresia.getFechaInicio());
		membresiaEnt.setFechaVencimiento(membresia.getFechaVencimiento());
		membresiaEnt.setEstado(membresia.getEstado());	
		return membresiaEnt;
	}

	private Membresia entityToDto(MembresiaEntity membresiaEnt) {
		Membresia membresia = new Membresia();
		membresia.setTitulo(membresiaEnt.getTitulo());
		membresia.setFechaInicio(membresiaEnt.getFechaInicio());
		membresia.setFechaVencimiento(membresiaEnt.getFechaVencimiento());
		membresia.setEstado(membresiaEnt.getEstado());
		return membresia;
	}
}
