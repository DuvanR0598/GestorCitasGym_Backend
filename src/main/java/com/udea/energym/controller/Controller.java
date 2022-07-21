package com.udea.energym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.udea.energym.dto.Fisioterapia;
import com.udea.energym.service.ISesionCrudService;

@RestController
public class Controller {

    @Autowired
    ISesionCrudService iSesion;

    @PostMapping("/agregar-sesion")
    public ResponseEntity<Fisioterapia> agregarFisioterapia(@RequestBody Fisioterapia fisioterapia){
        return ResponseEntity.ok().body(iSesion.agregarFisioterapia(fisioterapia));
    }
}