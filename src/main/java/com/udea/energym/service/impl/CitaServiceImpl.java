package com.udea.energym.service.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udea.energym.persistence.entity.CitaEntity;
import com.udea.energym.persistence.repository.ICitaRepository;
import com.udea.energym.service.ICitaService;

@Service
public class CitaServiceImpl implements ICitaService{
	
	@Autowired
	private ICitaRepository citaRepository;

	//organizar
	@Override
	public Set<CitaEntity> obtenerCitas() {
		return new LinkedHashSet<>(citaRepository.findAll());
	}

	@Override
	public CitaEntity obtenerCitaId(Long idCita) {
		return citaRepository.findById(idCita).get();
	}
	
	@Override
	public CitaEntity agregarCita(CitaEntity citaEnt) {
		return citaRepository.save(citaEnt);
	}

	@Override
	public CitaEntity actualizarCita(CitaEntity citaEntity) {
		return citaRepository.save(citaEntity);
//		Optional<CitaEntity> citaEntOpt = citaRepository.findById(citaEntity.getIdCita());
//		
//		if(citaEntOpt.isPresent()) {
//			citaRepository.save(citaEntity);
//			return "Cita actualizada con exito";
//		}
//		return "La cita no existe en el sistema";
	}

	@Override
	public String eliminarCita(Long idCita) {
		if(citaRepository.findById(idCita).isPresent()) {
			citaRepository.deleteById(idCita);
			return "Cita eliminada correctamente";
		}
		return "Error, la cita no existe";
	}
	
//	private CitaEntity dtoToEntity(Cita cita) {
//		CitaEntity citaEnt = new CitaEntity();
//		citaEnt.setFecha(cita.getFecha());
//		citaEnt.setHora(cita.getHora());
//		citaEnt.setDuracion(cita.getDuracion());
//		citaEnt.getCategoriaEnt().setIdCategoria(cita.getCategoria().getIdCategoria());
//		citaEnt.getUsuarioEnt().setCedula(cita.getUsuario().getCedula());
//		return citaEnt;
//	}
	
}
