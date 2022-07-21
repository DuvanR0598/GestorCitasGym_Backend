package com.udea.energym.entity;

import java.sql.Date;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SesionEntity {
    private Date fecha;
    private Date hora;
    private int duracion;
    private int numPersonas;
    private String profesional;

    public SesionEntity() {
        fecha = null;
        hora = null;
        duracion = 0;
        numPersonas = 0;
        profesional = "";
    }

    public SesionEntity(Date fecha, Date hora, int duracion, int numPersonas, String profesional) {
        this.fecha = fecha;
        this.hora = hora;
        this.duracion = duracion;
        this.numPersonas = numPersonas;
        this.profesional = profesional;
    }
}