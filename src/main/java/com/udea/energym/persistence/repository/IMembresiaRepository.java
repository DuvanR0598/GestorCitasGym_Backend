package com.udea.energym.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udea.energym.persistence.entity.MembresiaEntity;

@Repository
public interface IMembresiaRepository extends JpaRepository<MembresiaEntity, Long> {

}
