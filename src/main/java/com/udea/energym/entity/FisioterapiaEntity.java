package com.udea.energym.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;
import javax.persistence.Table;

@Entity
@Table(name = "fisioterapia")
@PrimaryKeyJoinColumns({
    @PrimaryKeyJoinColumn(name = "hora"),
    @PrimaryKeyJoinColumn(name = "fecha"),
    @PrimaryKeyJoinColumn(name = "profesional")
})
public class FisioterapiaEntity extends SesionEntity{

    public FisioterapiaEntity(Date fecha, Date hora, int duracion, int numPersonas, String profesional) {
        super(fecha, hora, duracion, numPersonas, profesional);
    }

    public FisioterapiaEntity() {
        super();
    }
}