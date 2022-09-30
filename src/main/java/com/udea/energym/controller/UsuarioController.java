package com.udea.energym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udea.energym.dto.Usuario;
import com.udea.energym.service.IUsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/buscar-usuario/{idUsuario}")
	public ResponseEntity<Usuario> buscarUsuarioId(@PathVariable Long idUsuario) {
		return ResponseEntity.ok().body(usuarioService.buscarUsuarioId(idUsuario));
	}

    @DeleteMapping("eliminar-usuario/{usuarioId}")
    public String eliminarUsuario(@PathVariable("usuarioId") Long usuarioId){
    	return usuarioService.eliminarUsuario(usuarioId);
    }
    
    @PutMapping("/actualizar-usuario")
	public ResponseEntity<String> actualizarUsuario(@RequestBody Usuario usuario) {
		return ResponseEntity.ok().body(usuarioService.actualizarUsuario(usuario));
	}
    
    @GetMapping("/lista-usuarios")
	public List<Usuario> listarUsuarios(){
		return usuarioService.listarUsuarios();
	}
}
