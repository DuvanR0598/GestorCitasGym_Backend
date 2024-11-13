package com.udea.energym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udea.energym.dto.Clases;
import com.udea.energym.dto.Usuario;
import com.udea.energym.dto.UsuarioClases;
import com.udea.energym.persistence.entity.UsuarioEntity;
import com.udea.energym.service.IUsuarioClaseService;

@RestController
@RequestMapping("/reserva")
@CrossOrigin("*")
public class UsuarioClasesController {
	
	@Autowired
	private IUsuarioClaseService usuarioClaseService;
	
	@PostMapping("/inscribir")
    public ResponseEntity<String> inscribirUsuarioClase(@RequestBody UsuarioClases usuarioClases) {
		usuarioClaseService.inscribirUsuarioClase(usuarioClases.getIdClase(), usuarioClases.getCedulaUsuario());
        return ResponseEntity.ok("Usuario inscrito correctamente");
    }
	
	@GetMapping("/inscritos/{idClase}")
    public ResponseEntity<List<Usuario>> obtenerUsuariosInscritos(@PathVariable Long idClase) {
        List<Usuario> usuarios = usuarioClaseService.obtenerUsuariosInscritos(idClase);
        return ResponseEntity.ok(usuarios);
    }
	
	@PostMapping("/cancelar/{inscripcionId}")
    public ResponseEntity<?> cancelarClase(@PathVariable Long inscripcionId) {
        try {
            boolean resultado = usuarioClaseService.cancelarInscripcion(inscripcionId);
            if (resultado) {
                return ResponseEntity.ok("Inscripción cancelada exitosamente.");
            } else {
                return ResponseEntity.badRequest().body("No se puede cancelar la inscripción. El tiempo límite ha pasado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en la cancelación de la inscripción.");
        }
    }
	
	// Método para listar las clases a las que un usuario está inscrito
    @GetMapping("/mis-clases/{usuarioId}")
    public ResponseEntity<List<Clases>> listarClasesPorUsuario(@PathVariable Long usuarioId) {
        UsuarioEntity usuarioEnt = new UsuarioEntity();
        usuarioEnt.setCedula(usuarioId);
        List<Clases> clases = usuarioClaseService.listarClasesPorUsuario(usuarioEnt);
        return ResponseEntity.ok(clases);
    }
}
