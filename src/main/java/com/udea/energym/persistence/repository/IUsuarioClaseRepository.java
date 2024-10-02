package com.udea.energym.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udea.energym.persistence.entity.ClasesEntity;
import com.udea.energym.persistence.entity.UsuarioClasesEntity;

public interface IUsuarioClaseRepository extends JpaRepository<UsuarioClasesEntity, Long> {

	List<UsuarioClasesEntity> findByClasesEnt(ClasesEntity clase);
	
	void deleteByClasesEnt(ClasesEntity clasesEnt);
}
