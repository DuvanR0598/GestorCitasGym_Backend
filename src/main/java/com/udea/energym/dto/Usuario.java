package com.udea.energym.dto;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.validation.constraints.AssertTrue;

import com.udea.energym.util.validaciones.ValidPassword;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario {

	private Long cedula;
	private String nombre;
	private String apellido;
	private char genero;
	private LocalDate fechaNacimiento;
	private String celular;
	private String email;
	private float peso;
	private float altura;
	private String username;
	
	@ValidPassword
	private String password;
	private List<Rol> listaRoles;
	private List<Membresia> listaMembresias;
	
	@AssertTrue(message = "El usuario debe tener entre 12 y 70 años.")
    public boolean isEdadValida() {
        if (this.fechaNacimiento == null) {
            return false;
        }
        int edad = Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
        return edad >= 12 && edad <= 95;
    }
}
