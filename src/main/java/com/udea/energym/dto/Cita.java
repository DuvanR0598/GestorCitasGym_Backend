package com.udea.energym.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cita {
    private Cliente cliente;
    private Sesion Sesion;

    public Cita(){
        cliente = null;
        Sesion = null;
    }

    public boolean crearCita(Cliente cliente, Sesion sesion){
        this.setCliente(cliente);
        this.setSesion(Sesion);
        return false;
    }
}