package com.mastertech.pontoeletronico.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.mastertech.pontoeletronico.entity.PontoEntity;
import com.mastertech.pontoeletronico.entity.UsuarioEntity;
import com.mastertech.pontoeletronico.model.PontoModel;
import com.mastertech.pontoeletronico.repository.UsuarioRepository;

@Component
public class PontoConverter {
	
	private UsuarioRepository usuarioRepository;
	
	public PontoConverter(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
    
	public PontoEntity convertFromModelToEntity(PontoModel model) {
		PontoEntity entity = new PontoEntity();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		entity.setTipo(model.getTipo());
		entity.setDataHoraBatida(LocalDateTime.parse(model.getDataHoraBatida(), formatter));
		
		Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findById(model.getUsuario().getId());
		
		entity.setUsuario(usuarioOptional.orElseGet(UsuarioEntity::new));
		
		return entity;
	}
	
}