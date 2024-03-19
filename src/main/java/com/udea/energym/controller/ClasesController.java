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

import com.udea.energym.dto.Clases;
import com.udea.energym.service.IClasesService;

@RestController
@RequestMapping("/clases")
@CrossOrigin("*")
public class ClasesController {
	
	@Autowired
	private IClasesService clasesService;
	
	@PostMapping("/guardar-clase")
	public ResponseEntity<Clases> guardarClase(@RequestBody Clases clases){
		return ResponseEntity.ok().body(clasesService.guardarClase(clases));
	}
	
	@GetMapping("/buscar-clase/{idClase}")
	public ResponseEntity<Clases> buscarClaseId(@PathVariable Long idCategoria) {
		return ResponseEntity.ok().body(clasesService.obtenerClaseId(idCategoria));
	}
	
	@GetMapping("/lista-clases")
	public ResponseEntity<?> listarClases(){
		return ResponseEntity.ok(clasesService.obtenerClases());
	}
	
	@PutMapping("/actualizar-clase")
	public ResponseEntity<String> actualizarClase(@RequestBody Clases clases) {
		return ResponseEntity.ok().body(clasesService.actualizarClase(clases));
	} 
	
	@DeleteMapping("/eliminar-clase/{idClase}")
	public String eliminarClase(@PathVariable Long idCategoria) {
		return clasesService.eliminarClase(idCategoria);
	}
}
