package com.udea.energym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udea.energym.dto.Membresia;
import com.udea.energym.service.IMembresiaService;


@RestController
@RequestMapping("/membresias")
@CrossOrigin("*")
public class MembresiaController {
	
	@Autowired
	private IMembresiaService membresiaService;
	
	@PostMapping("/guardar-membresia")
	public ResponseEntity<Membresia> guardarMembresia(@RequestBody Membresia membresia){
		return ResponseEntity.ok().body(membresiaService.guardarMembresia(membresia));
	}

}
