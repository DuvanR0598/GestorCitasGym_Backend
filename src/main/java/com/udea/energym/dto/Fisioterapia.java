package com.udea.energym.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Fisioterapia extends Sesion{

    public Fisioterapia(LocalDateTime fecha, LocalDateTime hora, Profesional profesional) {
        super(fecha, hora, 1, 1, profesional);
    }
}