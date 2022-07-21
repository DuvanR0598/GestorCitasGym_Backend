package com.udea.energym.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Sesion {
    private LocalDateTime fecha;
    private LocalDateTime hora;
    private int duracion;
    private int numPersonas;
    private Profesional profesional;
    
    public Sesion(LocalDateTime fecha, LocalDateTime hora, int duracion, int numPersonas, Profesional profesional) {
        this.fecha = fecha;
        this.hora = hora;
        this.duracion = duracion;
        this.numPersonas = numPersonas;
        this.profesional = profesional;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder("Datos de la sesión\nFecha: ");
        builder.append(this.getFecha().getYear() + "-" + this.getFecha().getMonthValue() + "-" + this.getFecha().getDayOfMonth());
        builder.append("\nHora: ");
        builder.append(this.getHora().getHour() + ":" + this.getHora().getMinute());
        builder.append("\nDuración: ");
        builder.append(this.getDuracion());
        builder.append("\nNúmero de Personas: ");
        builder.append(this.getNumPersonas());
        builder.append("\nCédula del Profesional: ");
        builder.append(this.getProfesional().getCedula());
        return builder.toString();
    }
}