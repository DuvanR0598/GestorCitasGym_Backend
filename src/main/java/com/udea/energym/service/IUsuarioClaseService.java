package com.udea.energym.service;

import java.util.List;

import com.udea.energym.dto.Clases;
import com.udea.energym.dto.Usuario;
import com.udea.energym.persistence.entity.UsuarioEntity;

public interface IUsuarioClaseService {
	
	void inscribirUsuarioClase(Long idClase, Long cedulaUsuario);

    List<Usuario> obtenerUsuariosInscritos(Long idClase);
    
    boolean cancelarInscripcion(Long inscripcionId);
    
    List<Clases> listarClasesPorUsuario(UsuarioEntity usuario);
    
}
