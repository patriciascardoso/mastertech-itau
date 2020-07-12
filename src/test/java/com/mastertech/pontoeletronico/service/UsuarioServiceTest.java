package com.mastertech.pontoeletronico.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.mastertech.pontoeletronico.converter.UsuarioConverter;
import com.mastertech.pontoeletronico.entity.UsuarioEntity;
import com.mastertech.pontoeletronico.model.UsuarioModel;
import com.mastertech.pontoeletronico.repository.UsuarioRepository;

public class UsuarioServiceTest {

	@Mock
	private UsuarioRepository repository;

	@Mock
	private UsuarioConverter converter;

	@InjectMocks
	private UsuarioService service;

	private UsuarioEntity usuario;

	private UsuarioEntity usuario2;

	private UsuarioModel usuarioModel;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		usuario = new UsuarioEntity();
		usuario.setCpf("1111111111");
		usuario.setDataCadastro(LocalDate.now());
		usuario.setEmail("teste@teste.com");
		usuario.setNomeCompleto("Patricia");

		usuario2 = new UsuarioEntity();
		usuario2.setCpf("1111111111");
		usuario2.setDataCadastro(LocalDate.now());
		usuario2.setEmail("teste@teste.com");
		usuario2.setNomeCompleto("Patricia");

		usuarioModel = new UsuarioModel();
		usuarioModel.setCpf("111111111111");
		usuarioModel.setDataCadastro("10/10/2020");
		usuarioModel.setEmail("teste@teste.com");
		usuarioModel.setNome("Renato");

	}

	@Test
	public void deveCriarUsuario() {
		Mockito.when(repository.save(Mockito.any(UsuarioEntity.class))).thenReturn(new UsuarioEntity());
		Mockito.when(converter.convertFromEntityToModel(Mockito.any(UsuarioEntity.class))).thenReturn(new UsuarioModel());

		UsuarioModel usuarioModel = service.criarUsuario(new UsuarioEntity());

		Assert.assertNotNull(usuarioModel);
	}

	@Test
	public void deveListarUsuarios() {

		List<UsuarioEntity> lista = new ArrayList<UsuarioEntity>();
		lista.add(usuario);
		lista.add(usuario2);

		Mockito.when(repository.findAll()).thenReturn(lista);
		Mockito.when(converter.convertFromEntityToModel(Mockito.any(UsuarioEntity.class))).thenReturn(usuarioModel);

		List<UsuarioModel> list = service.listar();

		Assert.assertNotNull(list);
		Assert.assertEquals(2,list.size());

	}

	@Test
	public void deveRetornarUsuarioPorId() {

		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(usuario));
		Mockito.when(converter.convertFromEntityToModel(Mockito.any(UsuarioEntity.class))).thenReturn(usuarioModel);
		UsuarioModel model = service.obterUsuarioPorId(1L);

		Assert.assertNotNull(model);
	}

	@Test
	public void deveAtualizarUsuario() {
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(usuario));
		Mockito.when(repository.save(Mockito.any(UsuarioEntity.class))).thenReturn(usuario);
		Mockito.when(converter.convertFromEntityToModel(Mockito.any(UsuarioEntity.class))).thenReturn(usuarioModel);
		UsuarioModel model = service.atualizar(1L, usuarioModel);

		Assert.assertNotNull(model);
	}
}
