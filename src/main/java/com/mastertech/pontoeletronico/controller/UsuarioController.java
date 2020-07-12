package com.mastertech.pontoeletronico.controller;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mastertech.pontoeletronico.converter.UsuarioConverter;
import com.mastertech.pontoeletronico.entity.UsuarioEntity;
import com.mastertech.pontoeletronico.model.UsuarioModel;
import com.mastertech.pontoeletronico.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private UsuarioService service;

	private UsuarioConverter converter;

	public UsuarioController(UsuarioService service, UsuarioConverter converter) {
		this.service = service;
		this.converter = converter;
	}

	@PostMapping
	public ResponseEntity<UsuarioModel> criar(@Valid @RequestBody UsuarioModel usuario){
		UsuarioEntity usuarioEntity = converter.convertFromModelToEntity(usuario);
		UsuarioModel usuarioCriado = service.criarUsuario(usuarioEntity);

		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado);
	}

	@GetMapping
	public List<UsuarioModel> listarUsuarios(){
		return service.listar();
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<UsuarioModel> buscarPorCodigo(@PathVariable Long codigo){
		UsuarioModel usuarioModel = service.obterUsuarioPorId(codigo);

		return Objects.nonNull(usuarioModel) ? ResponseEntity.ok(usuarioModel) : ResponseEntity.notFound().build();

	}

	@PutMapping("/{codigo}")
	public ResponseEntity<UsuarioModel> atualizarUsuario(@PathVariable Long codigo, @RequestBody UsuarioModel model) {
		UsuarioModel usuario = this.service.atualizar(codigo, model);

		return ResponseEntity.ok(usuario);
	}



}
