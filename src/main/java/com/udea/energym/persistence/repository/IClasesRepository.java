package com.udea.energym.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udea.energym.persistence.entity.CategoriaEntity;
import com.udea.energym.persistence.entity.ClasesEntity;

@Repository
public interface IClasesRepository extends JpaRepository<ClasesEntity, Long> {
	
	List<ClasesEntity> findByCategoria(CategoriaEntity categoria);
	
	List<ClasesEntity> findByActivo(Boolean estado);
	
	List<ClasesEntity> findByCategoriaAndActivo(CategoriaEntity categoriaEnt, Boolean estado);

}
