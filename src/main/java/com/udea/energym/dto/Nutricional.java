package com.udea.energym.dto;

import java.time.LocalDateTime;

public class Nutricional extends Sesion{

    public Nutricional(LocalDateTime fecha, LocalDateTime hora, Profesional profesional) {
        super(fecha, hora, 1, 1, profesional);
    }
}