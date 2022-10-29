package com.udea.energym.service;

import java.util.Set;

import com.udea.energym.persistence.entity.CitaEntity;

public interface ICitaService {

	Set<CitaEntity> obtenerCitas();
	
	CitaEntity obtenerCitaId(Long idCita);
	
	CitaEntity agregarCita(CitaEntity citaEnt);
	
	CitaEntity actualizarCita(CitaEntity citaEnt);
	
	String eliminarCita(Long idCita);
	
//	List<CitaEntity> listarExamenesDeUnaCategoria(CategoriaEntity categoriaEnt);
}
