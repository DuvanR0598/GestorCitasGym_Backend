package com.udea.energym.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udea.energym.dto.Persona;
import com.udea.energym.entity.PersonaEntity;
import com.udea.energym.repository.PersonaRepository;
import com.udea.energym.service.IPersonaCrudService;

@Service
public class PersonaCrudServiceImpl implements IPersonaCrudService{
	
	@Autowired
	PersonaRepository personaRepository;

	@Override
	public Persona agregarPersona(Persona persona) {
		personaRepository.save(dtoToEntity(persona));
		return persona;
	}
	
	private PersonaEntity dtoToEntity(Persona persona) {
		PersonaEntity personaEnt = new PersonaEntity();
		personaEnt.setCedula(persona.getCedula());
		personaEnt.setNombre(persona.getNombre());
		personaEnt.setApellido(persona.getApellido());
		personaEnt.setGenero(persona.getGenero());
		personaEnt.setEdad(persona.getEdad());
		personaEnt.setCelular(persona.getCelular());
		personaEnt.setEmail(persona.getEmail());
		personaEnt.setPeso(persona.getPeso());
		personaEnt.setAltura(persona.getAltura());
		return personaEnt;
		
	}
}
