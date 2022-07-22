package com.udea.energym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.udea.energym.dto.Persona;
import com.udea.energym.service.IPersonaCrudService;

@RestController
public class Controller {
	
	@Autowired
	IPersonaCrudService iPersona;

	@PostMapping("/agregar-persona")
	public ResponseEntity<Persona> agregarPersona(@RequestBody Persona persona) {
		return ResponseEntity.ok().body(iPersona.agregarPersona(persona));
	}
}
