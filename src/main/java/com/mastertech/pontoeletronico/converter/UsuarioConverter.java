package com.mastertech.pontoeletronico.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.mastertech.pontoeletronico.entity.UsuarioEntity;
import com.mastertech.pontoeletronico.model.UsuarioModel;

@Component
public class UsuarioConverter {

	public UsuarioEntity convertFromModelToEntity(UsuarioModel model) {
		UsuarioEntity entity = new UsuarioEntity();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		entity.setNomeCompleto(model.getNome());
		entity.setCpf(model.getCpf());
		entity.setEmail(model.getEmail());
		entity.setDataCadastro(LocalDate.parse(model.getDataCadastro(), formatter));

		return entity;
	}

	public UsuarioModel convertFromEntityToModel(UsuarioEntity entity) {
		UsuarioModel model = new UsuarioModel();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		model.setId(entity.getId());
		model.setCpf(entity.getCpf());
		model.setDataCadastro(entity.getDataCadastro().format(formatter));
		model.setEmail(entity.getEmail());
		model.setNome(entity.getNomeCompleto());

		return model;

	}
}
