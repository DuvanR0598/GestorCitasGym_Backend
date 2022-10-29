package com.udea.energym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udea.energym.dto.Categoria;
import com.udea.energym.service.ICategoriaService;

@RestController
@RequestMapping("/categorias")
@CrossOrigin("*")
public class CategoriaController {
	
	@Autowired
	private ICategoriaService categoriaService;
	
	@PostMapping("/guardar-categoria")
	public ResponseEntity<Categoria> guardarCategoria(@RequestBody Categoria categoria){
		return ResponseEntity.ok().body(categoriaService.agregarCategoria(categoria));
	}
	
	@GetMapping("/buscar-categoria/{idCategoria}")
	public ResponseEntity<Categoria> buscarCategoriaId(@PathVariable Long idCategoria) {
		return ResponseEntity.ok().body(categoriaService.obtenerCategoriaId(idCategoria));
	}
	
	@GetMapping("/lista-categorias")
	public ResponseEntity<?> listarCategorias(){
		return ResponseEntity.ok(categoriaService.obtenerCategorias());
	}
	
	@PutMapping("/actualizar-categoria")
	public ResponseEntity<String> actualizarCategoria(@RequestBody Categoria categoria) {
		return ResponseEntity.ok().body(categoriaService.actualizarCategoria(categoria));
	} 
	
	@DeleteMapping("/eliminar-categoria/{idCategoria}")
	public String eliminarCategoria(@PathVariable Long idCategoria) {
		return categoriaService.eliminarCategoria(idCategoria);
	}
}
