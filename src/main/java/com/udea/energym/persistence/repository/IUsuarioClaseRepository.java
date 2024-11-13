package com.udea.energym.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udea.energym.persistence.entity.ClasesEntity;
import com.udea.energym.persistence.entity.UsuarioClasesEntity;
import com.udea.energym.persistence.entity.UsuarioEntity;

public interface IUsuarioClaseRepository extends JpaRepository<UsuarioClasesEntity, Long> {

	List<UsuarioClasesEntity> findByClasesEnt(ClasesEntity clase);
	
	void deleteByClasesEnt(ClasesEntity clasesEnt);
	
	// Método para listar las clases a las que un usuario está inscrito
	List<UsuarioClasesEntity> findAllByUsuarioEnt(UsuarioEntity usuario);

}
