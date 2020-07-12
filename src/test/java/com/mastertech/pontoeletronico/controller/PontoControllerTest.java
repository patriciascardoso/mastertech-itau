package com.mastertech.pontoeletronico.controller;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mastertech.pontoeletronico.converter.PontoConverter;
import com.mastertech.pontoeletronico.entity.PontoEntity;
import com.mastertech.pontoeletronico.enumeration.TipoBatimento;
import com.mastertech.pontoeletronico.model.PontoModel;
import com.mastertech.pontoeletronico.model.RelatorioHorasModel;
import com.mastertech.pontoeletronico.service.PontoService;

@RunWith(SpringRunner.class)
@WebMvcTest(PontoController.class)
public class PontoControllerTest {

	@Autowired
    private MockMvc mvc;

	@MockBean
	private PontoConverter converter;

	@MockBean
	private PontoService service;

	@Test
	public void deveSalvarPontoDoUsuario() throws Exception {
		Mockito.when(converter.convertFromModelToEntity(Mockito.any(PontoModel.class))).thenReturn(new PontoEntity());
		Mockito.when(service.criarPonto(Mockito.any(PontoEntity.class))).thenReturn(new PontoEntity());

		PontoModel model = new PontoModel();
		model.setDataHoraBatida("04/03/2020 18:00");
		model.setTipo(TipoBatimento.SAIDA);

		 ObjectMapper mapper = new ObjectMapper();
		 ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		    String requestJson=ow.writeValueAsString(model );

		mvc.perform(MockMvcRequestBuilders.post("/ponto").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson)).andExpect(status().isOk());
		verify(service, VerificationModeFactory.times(1)).criarPonto((Mockito.any()));

	}

	@Test
	public void deveRetornarHorasTrabalhadas() throws Exception {
		Mockito.when(service.obterRelatorio(Mockito.anyLong())).thenReturn(new RelatorioHorasModel());

		mvc.perform(MockMvcRequestBuilders.get("/ponto/1").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
	}
}
