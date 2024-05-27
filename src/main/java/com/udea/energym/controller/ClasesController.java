package com.udea.energym.controller;

import java.util.List;

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
import com.udea.energym.dto.Clases;
import com.udea.energym.dto.Usuario;
import com.udea.energym.dto.UsuarioClases;
import com.udea.energym.service.IClasesService;

@RestController
@RequestMapping("/clases")
@CrossOrigin("*")
public class ClasesController {
	
	@Autowired
	private IClasesService clasesService;
	
	@PostMapping("/guardar-clase")
	public ResponseEntity<Clases> guardarClase(@RequestBody Clases clases){
		return ResponseEntity.ok().body(clasesService.guardarClase(clases));
	}
	
	@GetMapping("/buscar-clase/{idClase}")
	public ResponseEntity<Clases> buscarClaseId(@PathVariable Long idClase) {
		return ResponseEntity.ok().body(clasesService.obtenerClaseId(idClase));
	}
	
	@GetMapping("/lista-clases")
	public ResponseEntity<?> listarClases(){
		return ResponseEntity.ok(clasesService.obtenerClases());
	}
	
	@PutMapping("/actualizar-clase")
	public ResponseEntity<String> actualizarClase(@RequestBody Clases clases) {
		return ResponseEntity.ok().body(clasesService.actualizarClase(clases));
	} 
	
	@DeleteMapping("/eliminar-clase/{idClase}")
	public String eliminarClase(@PathVariable Long idClase) {
		return clasesService.eliminarClase(idClase);
	}
	
	@GetMapping("/clasesbycategoria/{idCategoria}")
	public List<Clases> listarclasesDeUnaCategoria(@PathVariable Long idCategoria){
		return clasesService.listarClasesDeUnaCategoria(idCategoria);
	}
	
	@GetMapping("/clases-activas")
    public List<Clases> obtenerClasesActivas() {
        return clasesService.obtenerClasesActivas();
    }
	
	@GetMapping("/clasesactivasbycategoria/{idCategoria}")
    public List<Clases> listarClasesActivasPorCategoria(@PathVariable Long idCategoria) {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(idCategoria);
        return clasesService.listarClasesActivasDeUnaCategoria(categoria);
    }
	
	//---------------------------------------------------------------------------------
	
	@PostMapping("/inscribir")
    public ResponseEntity<String> inscribirUsuarioClase(@RequestBody UsuarioClases usuarioClases) {
        clasesService.inscribirUsuarioClase(usuarioClases.getIdClase(), usuarioClases.getCedulaUsuario());
        return ResponseEntity.ok("Usuario inscrito correctamente");
    }

    @GetMapping("/inscritos/{idClase}")
    public ResponseEntity<List<Usuario>> obtenerUsuariosInscritos(@PathVariable Long idClase) {
        List<Usuario> usuarios = clasesService.obtenerUsuariosInscritos(idClase);
        return ResponseEntity.ok(usuarios);
    }
}
