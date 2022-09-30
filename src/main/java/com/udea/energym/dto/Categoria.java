package com.udea.energym.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Categoria {

	private Long idCategoria;
	private String titulo;
	private String descripcion;
	private int numPersonas;
}
