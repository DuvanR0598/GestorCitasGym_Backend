package com.udea.energym.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udea.energym.persistence.entity.UsuarioEntity;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

	Boolean existsByUsername(String username);
	
    Optional<UsuarioEntity> findByUsername(String username);

}
