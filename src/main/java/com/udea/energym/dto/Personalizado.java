package com.udea.energym.dto;

import java.time.LocalDateTime;

public class Personalizado extends Sesion{

    public Personalizado(LocalDateTime fecha, LocalDateTime hora, Profesional profesional) {
        super(fecha, hora, 1, 1, profesional);
    }
}