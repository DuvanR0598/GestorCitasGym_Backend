package com.udea.energym.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.udea.energym.dto.Usuario;
import com.udea.energym.persistence.entity.UsuarioEntity;
import com.udea.energym.persistence.entity.UsuarioRolEntity;
import com.udea.energym.persistence.repository.IRolRepository;
import com.udea.energym.persistence.repository.IUsuarioRepository;
import com.udea.energym.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IRolRepository rolRepository;

    @Override
    public UsuarioEntity guardarUsuario(UsuarioEntity usuario, Set<UsuarioRolEntity> usuarioRoles) {
       
        for(UsuarioRolEntity usuarioRol:usuarioRoles){
            rolRepository.save(usuarioRol.getRol());
        }
        usuario.getRoles().addAll(usuarioRoles);
        
        return usuarioRepository.save(usuario);
    }

    @Override
    public String eliminarUsuario(Long usuarioId) {
    	if(usuarioRepository.findById(usuarioId).isPresent()) {
    		usuarioRepository.deleteById(usuarioId);
    		return "Eliminado correctamente!";
    	}
    	return "Error! el usuario no existe";
    }

	@Override
	public Usuario buscarUsuarioId(Long id) {
		UsuarioEntity usuarioEnt = null;
		Optional<UsuarioEntity> usuarioOptEnt = usuarioRepository.findById(id);
		Usuario usuario = null;
		
		if (usuarioOptEnt.isPresent()) {
			usuarioEnt = usuarioOptEnt.get();
			usuario = entityToDto(usuarioEnt);
		}
		return usuario;
	}
	
	@Override
	public String actualizarUsuario(Usuario usuario) {
		Optional<UsuarioEntity> usuarioEntOpt = usuarioRepository.findById(usuario.getCedula());
		if(usuarioEntOpt.isPresent()) {
			UsuarioEntity usuarioEnt1 = usuarioEntOpt.get();
			UsuarioEntity usuarioEnt = dtoToEntity(usuario);
			usuarioEnt.setPassword(usuarioEnt1.getPassword());
			
			usuarioRepository.save(usuarioEnt);
			return "Usuario actualizado con exito";
		}
		return "El usuario no existe en el sistema";
	}
	
	@Override
	public List<Usuario> listarUsuarios() {
		List<UsuarioEntity> listUsuarioEnt = usuarioRepository.findAll();
		List<Usuario> listUsuario = new ArrayList<>();
		UsuarioEntity usuarioEnt;
		Usuario usuario;
		
		for(int i=0; i<listUsuarioEnt.size(); i++) {
			usuarioEnt = listUsuarioEnt.get(i);
			usuario = entityToDto(usuarioEnt);
			listUsuario.add(usuario);
		}
		return listUsuario;
	}
	
	private UsuarioEntity dtoToEntity(Usuario usuario) {
		UsuarioEntity usuarioEnt = new UsuarioEntity();
		usuarioEnt.setCedula(usuario.getCedula());
		usuarioEnt.setNombre(usuario.getNombre());
		usuarioEnt.setApellido(usuario.getApellido());
		usuarioEnt.setGenero(usuario.getGenero());
		usuarioEnt.setCelular(usuario.getCelular());
		usuarioEnt.setFechaNacimiento(usuario.getFechaNacimiento());
		usuarioEnt.setEmail(usuario.getEmail());
		usuarioEnt.setPeso(usuario.getPeso());
		usuarioEnt.setAltura(usuario.getAltura());
		usuarioEnt.setUsername(usuario.getUsername());
		return usuarioEnt;
	}
	
	private Usuario entityToDto(UsuarioEntity usuarioEnt) {
		Usuario usuario = new Usuario();
		usuario.setCedula(usuarioEnt.getCedula());
		usuario.setNombre(usuarioEnt.getNombre());
		usuario.setApellido(usuarioEnt.getApellido());
		usuario.setGenero(usuarioEnt.getGenero());
		usuario.setCelular(usuarioEnt.getCelular());
		usuario.setFechaNacimiento(usuarioEnt.getFechaNacimiento());
		usuario.setEmail(usuarioEnt.getEmail());
		usuario.setPeso(usuarioEnt.getPeso());
		usuario.setAltura(usuarioEnt.getAltura());
		usuario.setUsername(usuarioEnt.getUsername());
		return usuario;
	}

	@Override
	public Map<String, Object> listarUsuarios(Pageable pageable) {
		Page<UsuarioEntity> pageUsuarioEnt = usuarioRepository.buscarUsuarioPaginado(pageable);
		List<UsuarioEntity> listaUsuarioEntity = pageUsuarioEnt.getContent();
		Map<String, Object> listaP = new HashMap<>();
		
		listaUsuarioEntity
		.stream()
		.map(this::entityToDto)
		.collect(Collectors.toList());
		
		listaP.put("listado", listaUsuarioEntity);
		listaP.put("paginaActual", pageUsuarioEnt.getNumber());
		listaP.put("totalElementos", pageUsuarioEnt.getTotalElements());
		listaP.put("totalPaginas", pageUsuarioEnt.getTotalPages());
		return listaP;
	}

	
}