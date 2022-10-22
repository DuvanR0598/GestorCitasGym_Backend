package com.udea.energym.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Pageable;

import com.udea.energym.dto.Usuario;
import com.udea.energym.persistence.entity.UsuarioEntity;
import com.udea.energym.persistence.entity.UsuarioRolEntity;

public interface IUsuarioService {

    UsuarioEntity guardarUsuario(UsuarioEntity usuario, Set<UsuarioRolEntity> usuarioRoles);
    
    Usuario buscarUsuarioId(Long id);

    String eliminarUsuario(Long usuarioId);
    
    String actualizarUsuario(Usuario usuario);
    
    List<Usuario> listarUsuarios();
    
    Map<String, Object> listarUsuarios(Pageable pageable);
}
