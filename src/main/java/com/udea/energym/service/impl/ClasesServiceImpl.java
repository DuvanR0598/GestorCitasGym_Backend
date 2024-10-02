package com.udea.energym.service.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.udea.energym.dto.Categoria;
import com.udea.energym.dto.Clases;
import com.udea.energym.dto.Usuario;
import com.udea.energym.persistence.entity.CategoriaEntity;
import com.udea.energym.persistence.entity.ClasesEntity;
import com.udea.energym.persistence.entity.UsuarioClasesEntity;
import com.udea.energym.persistence.entity.UsuarioEntity;
import com.udea.energym.persistence.repository.ICategoriaRepository;
import com.udea.energym.persistence.repository.IClasesRepository;
import com.udea.energym.persistence.repository.IUsuarioClaseRepository;
import com.udea.energym.persistence.repository.IUsuarioRepository;
import com.udea.energym.service.IClasesService;

@Service
public class ClasesServiceImpl implements IClasesService {
	
	@Autowired
	private IClasesRepository clasesRepository;
	
	@Autowired 
	private ICategoriaRepository categoriaRepository;
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Autowired
    private IUsuarioClaseRepository usuarioClaseRepository;

	@Override
	public Set<Clases> obtenerClases() {
		Set<ClasesEntity> clasesEntities = new LinkedHashSet<>(clasesRepository.findAll());
	    Set<Clases> clasesDTOs = new LinkedHashSet<>();
	    for (ClasesEntity clasesEntity : clasesEntities) {
	        Clases clasesDTO = entityToDto(clasesEntity);
	        clasesDTOs.add(clasesDTO);
	    }
	    return clasesDTOs;
	}

	@Override
	public Clases guardarClase(Clases clases) {
		// Obtener el nombre de usuario del usuario actual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        // Buscar el usuario por el nombre de usuario
        UsuarioEntity usuarioEnt = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con el nombre de usuario: " + username));

        // Convertir el DTO a una entidad Clases
        ClasesEntity claseEnt = dtoToEntity(clases);
        
        // Asociar al usuario con la clase
        UsuarioClasesEntity usuarioClaseEnt = new UsuarioClasesEntity();
        usuarioClaseEnt.setUsuarioEnt(usuarioEnt);
        usuarioClaseEnt.setClasesEnt(claseEnt);
        claseEnt.getUsuarioClases().add(usuarioClaseEnt);
        
        // Guardar la clase
        claseEnt = clasesRepository.save(claseEnt);
        
        // Devolver la clase convertida a DTO
		return entityToDto(claseEnt);
	}

	@Override
	public Clases obtenerClaseId(Long idClase) {
		ClasesEntity claseEnt = null;
		Optional<ClasesEntity> claseOptEnt = clasesRepository.findById(idClase);
		Clases clases = null;
		
		if (claseOptEnt.isPresent()) {
			claseEnt = claseOptEnt.get();
			clases = entityToDto(claseEnt);
		}
		return clases;
	}

	@Override
	public String actualizarClase(Clases clases) {
		Optional<ClasesEntity> claEntOpt = clasesRepository.findById(clases.getIdClases());
		if(claEntOpt.isPresent()) {
			ClasesEntity clasesEnt = claEntOpt.get();
			clasesEnt.setNombreClase(clases.getNombreClase());
			clasesEnt.setTipoClase(clases.getTipoClase());
			clasesEnt.setInstructor(clases.getInstructor());
			clasesEnt.setUbicacion(clases.getUbicacion());
			clasesEnt.setFechaClase(clases.getFechaClase());
			clasesEnt.setHora(clases.getHora());
			clasesEnt.setCapacidadMax(clases.getCapacidadMax());
			clasesEnt.setActivo(clases.isActivo());
			clasesRepository.save(clasesEnt);
			return "Clase actualizada...";
		}
		return "La clase no existe...";
	}

	@Override
	public String eliminarClase(Long idClase) {
		Optional<ClasesEntity> clase = clasesRepository.findById(idClase);
		if(clase.isPresent()) {
			clasesRepository.deleteById(idClase);
			return "¡Clase eliminada correctamente!";
		}
		return "Error! la clase no existe";
	}
	
	@Override
	public List<Clases> listarClasesDeUnaCategoria(Long idCategoria) {
		CategoriaEntity categoriaEntity = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada"));
		
		List<ClasesEntity> clasesEntities = clasesRepository.findByCategoria(categoriaEntity);
		return clasesEntities.stream().map(this::entityToDto).collect(Collectors.toList());
	}

	@Override
	public List<Clases> obtenerClasesActivas() {
		List<ClasesEntity> clasesEntities = clasesRepository.findByActivo(true);
		return clasesEntities.stream().map(this::entityToDto).collect(Collectors.toList());
	}

	@Override
	public List<Clases> listarClasesActivasDeUnaCategoria(Categoria categoria) {
		CategoriaEntity categoriaEntity = new CategoriaEntity();
		categoriaEntity.setIdCategoria(categoria.getIdCategoria());
		
		List<ClasesEntity> clasesEntities = clasesRepository.findByCategoriaAndActivo(categoriaEntity, true);
		return clasesEntities.stream().map(this::entityToDto).collect(Collectors.toList());
	}
	
	private ClasesEntity dtoToEntity(Clases clases) {
		ClasesEntity clasesEnt = new ClasesEntity();
		clasesEnt.setNombreClase(clases.getNombreClase());
		clasesEnt.setTipoClase(clases.getTipoClase());
		clasesEnt.setInstructor(clases.getInstructor());
		clasesEnt.setUbicacion(clases.getUbicacion());
		clasesEnt.setFechaClase(clases.getFechaClase());
		clasesEnt.setHora(clases.getHora());
		clasesEnt.setCapacidadMax(clases.getCapacidadMax());
		clasesEnt.setActivo(clases.isActivo());
		
		// Obtener la categoría del DTO y convertirla en una entidad de categoría
		CategoriaEntity categoriaEnt = categoriaRepository.findById(clases.getCategoria().getIdCategoria())
				.orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada"));
		
		clasesEnt.setCategoria(categoriaEnt);
		
		return clasesEnt;
	}
	
	private Clases entityToDto(ClasesEntity clasesEnt) {
		Clases clases = new Clases();
		clases.setIdClases(clasesEnt.getIdClases());
		clases.setNombreClase(clasesEnt.getNombreClase());
		clases.setTipoClase(clasesEnt.getTipoClase());
		clases.setInstructor(clasesEnt.getInstructor());
		clases.setUbicacion(clasesEnt.getUbicacion());
		clases.setFechaClase(clasesEnt.getFechaClase());
		clases.setHora(clasesEnt.getHora());
		clases.setCapacidadMax(clasesEnt.getCapacidadMax());
		clases.setActivo(clasesEnt.isActivo());
		
		// Obtener la categoría asociada a la clase y mapearla al DTO
	    Categoria categoria = new Categoria();
	    categoria.setIdCategoria(clasesEnt.getCategoria().getIdCategoria());
	    categoria.setTitulo(clasesEnt.getCategoria().getTitulo());
	    categoria.setDescripcion(clasesEnt.getCategoria().getDescripcion());
	    // Asignar la categoría al DTO de la clase
	    clases.setCategoria(categoria);
		return clases;
	}

	
	
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
        
        // Buscar la clase por id
		ClasesEntity clase = clasesRepository.findById(idClase)
                .orElseThrow(() -> new EntityNotFoundException("Clase no encontrada"));


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
        // Mapear otros campos según sea necesario
        return usuario;
    }
}
