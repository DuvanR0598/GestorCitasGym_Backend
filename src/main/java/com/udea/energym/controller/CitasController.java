package com.udea.energym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udea.energym.persistence.entity.CitaEntity;
import com.udea.energym.service.ICitaService;

@RestController
@RequestMapping("/citas")
@CrossOrigin("*")
public class CitasController {
	
	@Autowired
	private ICitaService citaService;

	@PostMapping("/agregar-cita")
    public ResponseEntity<CitaEntity> agregarCita(@RequestBody CitaEntity citaEnt){
		return ResponseEntity.ok().body(citaService.agregarCita(citaEnt));
	}
	
	@PutMapping("/actualizar-cita")
    public ResponseEntity<CitaEntity> actualizarCita(@RequestBody CitaEntity citaEnt){
		return ResponseEntity.ok(citaService.actualizarCita(citaEnt));
	}
	
	@GetMapping("/lista-citas")
	public ResponseEntity<?> listarCategorias(){
		return ResponseEntity.ok(citaService.obtenerCitas());
	}
	
	@GetMapping("/buscar-cita/{idCita}")
	public ResponseEntity<CitaEntity> buscarCitaId(@PathVariable Long idCita) {
		return ResponseEntity.ok().body(citaService.obtenerCitaId(idCita));
	}
	
	@DeleteMapping("/eliminar-cita/{idCita}")
	public String eliminarProducto(@PathVariable Long idCita) {
		return citaService.eliminarCita(idCita);
	}
}