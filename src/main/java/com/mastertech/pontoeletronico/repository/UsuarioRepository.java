package com.mastertech.pontoeletronico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mastertech.pontoeletronico.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>{

}
