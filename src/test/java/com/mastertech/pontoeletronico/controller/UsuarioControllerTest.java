package com.mastertech.pontoeletronico.controller;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mastertech.pontoeletronico.converter.UsuarioConverter;
import com.mastertech.pontoeletronico.entity.UsuarioEntity;
import com.mastertech.pontoeletronico.model.UsuarioModel;
import com.mastertech.pontoeletronico.service.UsuarioService;

@RunWith(SpringRunner.class)
@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

	@Autowired
    private MockMvc mvc;

	@MockBean
	private UsuarioService service;

	@MockBean
	private UsuarioConverter converter;

	private String requestJson;

	private UsuarioModel usuario;

	@Before
	public void setup() throws JsonProcessingException {
		usuario = new UsuarioModel();
		usuario.setCpf("111111111111");
		usuario.setDataCadastro("10/10/2020");
		usuario.setEmail("teste@teste.com");
		usuario.setNome("Patricia");

		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		requestJson=ow.writeValueAsString(usuario);
	}

	@Test
	public void deveCriarUsuario() throws Exception {
		Mockito.when(converter.convertFromModelToEntity(Mockito.any(UsuarioModel.class))).thenReturn(new UsuarioEntity());
		Mockito.when(service.criarUsuario(Mockito.any(UsuarioEntity.class))).thenReturn(new UsuarioModel());


		mvc.perform(MockMvcRequestBuilders.post("/usuario").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson)).andExpect(status().isCreated());
		verify(service, VerificationModeFactory.times(1)).criarUsuario((Mockito.any()));
	}

	@Test
	public void deveListarUsuarios() throws Exception {

		List<UsuarioModel> lista = new ArrayList<UsuarioModel>();
		lista.add(usuario);

		Mockito.when(service.listar()).thenReturn(lista);

		mvc.perform(MockMvcRequestBuilders.get("/usuario").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
	}

	@Test
	public void deveObterUsuarioPorId() throws Exception {

		Mockito.when(service.obterUsuarioPorId(Mockito.anyLong())).thenReturn(usuario);

		mvc.perform(MockMvcRequestBuilders.get("/usuario/1").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
	}

	public void deveAtualizarUsuarioPorId() throws Exception {

		Mockito.when(service.atualizar(1L, usuario)).thenReturn(new UsuarioModel());

		mvc.perform(MockMvcRequestBuilders.put("/usuario/1").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson)).andExpect(status().isOk());
	}


}
