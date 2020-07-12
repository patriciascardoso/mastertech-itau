package com.mastertech.pontoeletronico.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.mastertech.pontoeletronico.entity.PontoEntity;
import com.mastertech.pontoeletronico.entity.UsuarioEntity;
import com.mastertech.pontoeletronico.enumeration.TipoBatimento;
import com.mastertech.pontoeletronico.model.RelatorioHorasModel;
import com.mastertech.pontoeletronico.repository.PontoRepository;
import com.mastertech.pontoeletronico.repository.UsuarioRepository;

public class PontoServiceTest {

	@Mock
	private PontoRepository repository;

	@Mock
	private UsuarioRepository usuarioRepository;

	@InjectMocks
	private PontoService service;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void deveSalvarPontoNoRepository() {
		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setCpf("1111111111");
		usuario.setDataCadastro(LocalDate.now());
		usuario.setEmail("teste@teste.com");
		usuario.setNomeCompleto("Patricia");

		PontoEntity entity = new PontoEntity();
		entity.setDataHoraBatida(LocalDateTime.now());
		entity.setTipo(TipoBatimento.ENTRADA);
		entity.setUsuario(usuario);

		Mockito.when(repository.save(Mockito.any(PontoEntity.class))).thenReturn(entity);

		PontoEntity ponto = service.criarPonto(entity);

		Assert.assertNotNull(ponto);

	}

	@Test
	public void deveGerarRelatorioBatidas() {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setCpf("1111111111");
		usuario.setDataCadastro(LocalDate.now());
		usuario.setEmail("teste@teste.com");
		usuario.setNomeCompleto("Patricia");

		PontoEntity entrada = new PontoEntity();
		entrada.setDataHoraBatida(LocalDateTime.parse("10/10/2020 10:00", formatter));
		entrada.setTipo(TipoBatimento.ENTRADA);
		entrada.setUsuario(usuario);

		PontoEntity saida = new PontoEntity();
		saida.setDataHoraBatida(LocalDateTime.parse("10/10/2020 18:00", formatter));
		saida.setTipo(TipoBatimento.SAIDA);
		saida.setUsuario(usuario);

		ArrayList<PontoEntity> lista = new ArrayList<PontoEntity>();
		lista.add(entrada);
		lista.add(saida);

		Mockito.when(repository.findPontoByUsuario(Mockito.anyLong())).thenReturn(lista);
		Mockito.when(usuarioRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(usuario));

		RelatorioHorasModel relatorio = service.obterRelatorio(1L);

		Assert.assertNotNull(relatorio);
		Assert.assertEquals(8L, (long) relatorio.getHorasTrabalhadas());

	}


}
