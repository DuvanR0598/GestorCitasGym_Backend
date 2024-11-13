package com.udea.energym.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udea.energym.persistence.entity.UsuarioEntity;
import com.udea.energym.persistence.entity.UsuarioMembresiaEntity;

@Repository
public interface IUsuarioMembresiaRepository extends JpaRepository<UsuarioMembresiaEntity, Long> {
	
	Optional<UsuarioMembresiaEntity> findByUsuarioAndMembresia_ActivaTrue(UsuarioEntity usuario);

}
