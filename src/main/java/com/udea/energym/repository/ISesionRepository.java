package com.udea.energym.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udea.energym.entity.SesionEntity;

public interface ISesionRepository extends JpaRepository<SesionEntity, String>{
}