package com.mastertech.pontoeletronico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mastertech.pontoeletronico.entity.PontoEntity;

public interface PontoRepository extends JpaRepository<PontoEntity, Long> {

	@Query("select p from PontoEntity p inner join p.usuario u where u.id = :usuarioId ")
	List<PontoEntity> findPontoByUsuario(Long usuarioId);
}
