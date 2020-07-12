package com.mastertech.pontoeletronico.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.mastertech.pontoeletronico.converter.UsuarioConverter;
import com.mastertech.pontoeletronico.entity.UsuarioEntity;
import com.mastertech.pontoeletronico.model.UsuarioModel;
import com.mastertech.pontoeletronico.repository.UsuarioRepository;

@Service
public class UsuarioService {


	private UsuarioRepository repository;
	private UsuarioConverter converter;

	public UsuarioService (UsuarioRepository repository, UsuarioConverter converter) {
		this.repository = repository;
		this.converter = converter;
	}

	public UsuarioModel criarUsuario (UsuarioEntity entity) {
		 UsuarioEntity usuario = this.repository.save(entity);

		 return converter.convertFromEntityToModel(usuario);
	}

	public List<UsuarioModel> listar(){
		List<UsuarioEntity> usuarios = this.repository.findAll();

		return usuarios.stream()
				.map(usuario -> converter.convertFromEntityToModel(usuario))
				.collect(Collectors.toList());
	}

	public UsuarioModel obterUsuarioPorId(Long id) {
		Optional<UsuarioEntity> usuarioOptional = this.repository.findById(id);

		if(usuarioOptional.isPresent()) {
			return converter.convertFromEntityToModel(usuarioOptional.get());
		}

		return null;
	}

	public UsuarioModel atualizar(Long codigo, UsuarioModel model) {
		Optional<UsuarioEntity> usuarioOptional = repository.findById(codigo);

		if(usuarioOptional.isPresent()) {
			UsuarioEntity usuarioEntity = usuarioOptional.get();

			usuarioEntity.setNomeCompleto(verificarValorNulo(model.getNome(), usuarioEntity.getNomeCompleto()));
			usuarioEntity.setCpf(verificarValorNulo(model.getCpf(), usuarioEntity.getCpf()));
			usuarioEntity.setEmail(verificarValorNulo(model.getEmail(),usuarioEntity.getEmail()));

			UsuarioEntity entity = repository.save(usuarioEntity);
			return converter.convertFromEntityToModel(entity);
		}else {
			throw new EmptyResultDataAccessException(1);
		}
	}

	private String verificarValorNulo(String valorEnviado, String valorPadrao) {
		return StringUtils.isBlank(valorEnviado) ? valorPadrao : valorEnviado;
	}
}
