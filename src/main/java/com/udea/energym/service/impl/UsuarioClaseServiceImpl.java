package com.udea.energym.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.udea.energym.dto.Categoria;
import com.udea.energym.dto.Clases;
import com.udea.energym.dto.Usuario;
import com.udea.energym.persistence.entity.ClasesEntity;
import com.udea.energym.persistence.entity.UsuarioClasesEntity;
import com.udea.energym.persistence.entity.UsuarioEntity;
import com.udea.energym.persistence.repository.IClasesRepository;
import com.udea.energym.persistence.repository.IUsuarioClaseRepository;
import com.udea.energym.persistence.repository.IUsuarioRepository;
import com.udea.energym.service.IUsuarioClaseService;

@Service
public class UsuarioClaseServiceImpl implements IUsuarioClaseService {
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Autowired
	private IClasesRepository clasesRepository;
	
	@Autowired
    private IUsuarioClaseRepository usuarioClaseRepository;
	
	@Autowired
	private MembresiaServiceImpl membresiaService;

	@Override
	public void inscribirUsuarioClase(Long idClase, Long cedulaUsuario) {
		// Obtener el nombre de usuario del usuario actual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        // Buscar el usuario por el nombre de usuario
        UsuarioEntity usuarioEnt = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con el nombre de usuario: " + username));
		
        // Validar que la cédula del usuario autenticado sea la misma que la cédula proporcionada
        if (!usuarioEnt.getCedula().equals(cedulaUsuario)) {
            throw new SecurityException("El usuario no corresponde con el logueado en el sistema");
        }
        
        
        
        
     // Verificar que el usuario tenga una membresía activa
        boolean membresiaActiva = membresiaService.verificarMembresiaActiva(usuarioEnt);
        if (!membresiaActiva) {
            throw new IllegalStateException("La membresía debe estar activa para inscribirse en la clase.");
        }
        
        
        
        
        
        // Buscar la clase por id
		ClasesEntity clase = clasesRepository.findById(idClase)
                .orElseThrow(() -> new EntityNotFoundException("Clase no encontrada"));

		//Validación de Capacid  ad, asegura que la cantidad de usuarios inscritos no supere la 
		//capacidad máxima
		if (clase.getUsuarioClases().size() >= clase.getCapacidadMax()) {
		    throw new IllegalStateException("La clase ha alcanzado su capacidad máxima de " + 
		    		clase.getCapacidadMax() + " usuarios inscritos.");
		}

        UsuarioClasesEntity usuarioClase = new UsuarioClasesEntity(); 
        usuarioClase.setClasesEnt(clase);
        usuarioClase.setUsuarioEnt(usuarioEnt);
        
        usuarioClaseRepository.save(usuarioClase);
	}

	@Override
	public List<Usuario> obtenerUsuariosInscritos(Long idClase) {
		ClasesEntity clase = clasesRepository.findById(idClase)
                .orElseThrow(() -> new EntityNotFoundException("Clase no encontrada"));
        
        List<UsuarioClasesEntity> usuarioClasesEntities = usuarioClaseRepository.findByClasesEnt(clase);
        
        return usuarioClasesEntities.stream()
                .map(UsuarioClasesEntity::getUsuarioEnt)
                .map(this::entityToDto)
                .collect(Collectors.toList());
	}
	
	private Usuario entityToDto(UsuarioEntity usuarioEnt) {
        Usuario usuario = new Usuario();
        usuario.setCedula(usuarioEnt.getCedula());
        usuario.setNombre(usuarioEnt.getNombre());
        usuario.setApellido(usuarioEnt.getApellido());
        usuario.setEmail(usuarioEnt.getEmail());
        return usuario;
    }

	@Override
	public boolean cancelarInscripcion(Long inscripcionId) {
		 if (usuarioClaseRepository.findById(inscripcionId).isPresent()) {
//			 UsuarioClasesEntity inscripcion = usuarioClaseRepository.findById(inscripcionId).get();
//	         LocalDate fechaClase = inscripcion.getClasesEnt().getFechaClase();
//	         LocalDate fechaActual = LocalDate.now();
//	         if (Duration.between(fechaActual, fechaClase).toHours() >= 24) {
//	        	 usuarioClaseRepository.deleteById(inscripcionId);
//	         }
			 usuarioClaseRepository.deleteById(inscripcionId);
             return true;
	     }
		return false;
	}
	
	public List<Clases> listarClasesPorUsuario(UsuarioEntity usuario) {
		// Obtener las inscripciones del usuario
		List<UsuarioClasesEntity> inscripciones = usuarioClaseRepository.findAllByUsuarioEnt(usuario);
		// Crear una lista para almacenar los DTOs de clases
	    List<Clases> clasesDtoList  = new ArrayList<>();
	    
	 // Recorremos las inscripciones y obtenemos cada clase
	    for (UsuarioClasesEntity inscripcion : inscripciones) {
	    	ClasesEntity claseEntity = inscripcion.getClasesEnt(); // Obtener la clase de la inscripción
	     // Si la clase no es null y no está duplicada, procedemos a mapearla
	        if (claseEntity  != null && !clasesDtoList.stream().anyMatch(c -> c.getIdClases().equals(claseEntity.getIdClases()))) { // Evitar duplicados
	        	Clases claseDto = entityToDto(claseEntity);
	        	
	        	// Mapear la categoría si es necesario
	            if (claseEntity.getCategoria() != null) {
	            	Categoria categoria = new Categoria();
	                categoria.setIdCategoria(claseEntity.getCategoria().getIdCategoria());
	                categoria.setTitulo(claseEntity.getCategoria().getTitulo());
	                categoria.setDescripcion(claseEntity.getCategoria().getDescripcion());
	                claseDto.setCategoria(categoria);
	            }
	         // Agregar el DTO mapeado a la lista
	            clasesDtoList.add(claseDto);
	        }
	    }
	    return clasesDtoList;
	}
	
	private Clases entityToDto (ClasesEntity claseEnt) {
		Clases claseDto = new Clases();
        claseDto.setIdClases(claseEnt.getIdClases());
        claseDto.setNombreClase(claseEnt.getNombreClase());
        claseDto.setTipoClase(claseEnt.getTipoClase());
        claseDto.setInstructor(claseEnt.getInstructor());
        claseDto.setUbicacion(claseEnt.getUbicacion());
        claseDto.setFechaClase(claseEnt.getFechaClase());
        claseDto.setHora(claseEnt.getHora());
        claseDto.setCapacidadMax(claseEnt.getCapacidadMax());
        claseDto.setActivo(claseEnt.isActivo());
        return claseDto;
	}

}
