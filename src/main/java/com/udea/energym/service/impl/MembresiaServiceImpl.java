package com.udea.energym.service.impl;

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
	public Membresia guardarMembresia(Membresia membresia) {
		MembresiaEntity membresiaEnt = membresiaRepository.save(dtoToEntity(membresia));
		membresia.setIdMembresia(membresiaEnt.getIdMembresia());
		return membresia;
	}
	
	private MembresiaEntity dtoToEntity (Membresia membresia) {
		MembresiaEntity membresiaEnt = new MembresiaEntity();
		membresiaEnt.setFechaInicio(membresia.getFechaInicio());
		membresiaEnt.setFechaVencimiento(membresia.getFechaVencimiento());
		membresiaEnt.setEstado(membresia.getEstado());	
		return membresiaEnt;
	}

}
