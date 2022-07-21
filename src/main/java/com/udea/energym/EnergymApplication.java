package com.udea.energym;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

// import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.udea.energym.dto.Cita;
import com.udea.energym.dto.Cliente;
import com.udea.energym.dto.Persona;
import com.udea.energym.dto.Sesion;
import com.udea.energym.service.impl.SesionCrudServiceImpl;

@SpringBootApplication
public class EnergymApplication {

	public static void main(String[] args) {
		// SpringApplication.run(EnergymApplication.class, args);
		Cita cita = capturarDatos();
		System.out.println("Reserva exitosa:\n" + cita.getSesion().toString());
	}

	public static Cita capturarDatos(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese su cédula");
		String cedula = scanner.nextLine();
		System.out.println("Ingrese su nombre");
		String nombre = scanner.nextLine();
		System.out.println("Ingrese su apellido");
		String apellido = scanner.nextLine();
		System.out.println("Ingrese su celular");
		String celular = scanner.nextLine();
		System.out.println("Ingrese su correo electrónico");
		String email = scanner.nextLine();
		System.out.println("Ingrese su edad");
		int edad = scanner.nextInt();
		System.out.println("Ingrese su género:\nH: Si es hombre\nM: Si es mujer");
		String genero = scanner.nextLine();
		System.out.println("Ingrese su peso");
		float peso = scanner.nextFloat();
		System.out.println("Ingrese su altura");
		float altura = scanner.nextFloat();
		System.out.println("Seleccione una de las sesiones");
		SesionCrudServiceImpl controladorSesiones = new SesionCrudServiceImpl();
		Sesion[] sesiones = controladorSesiones.buscarSesiones();
		for (int i = 0; i < 3; i++) {
			System.out.print("Ingrese " + i + " para seleccionar a ");
			System.out.print(sesiones[i].getProfesional().getNombre() + " en el horario");
			System.out.print(sesiones[i].getFecha().format(DateTimeFormatter.ISO_LOCAL_DATE) + " ");
			System.out.print(sesiones[i].getHora().format(DateTimeFormatter.ISO_LOCAL_TIME) + "\n");
		}
		int horario = scanner.nextInt();
		Persona cliente = new Cliente(cedula, nombre, apellido, celular, email, edad, genero.charAt(0), peso, altura);
		Cita cita = new Cita();
		cita.crearCita((Cliente) cliente, sesiones[horario]);
		scanner.close();
		return cita;
	}
}