package com.mastertech.pontoeletronico.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mastertech.pontoeletronico.entity.PontoEntity;
import com.mastertech.pontoeletronico.entity.UsuarioEntity;
import com.mastertech.pontoeletronico.enumeration.TipoBatimento;
import com.mastertech.pontoeletronico.model.RelatorioHorasModel;
import com.mastertech.pontoeletronico.repository.PontoRepository;
import com.mastertech.pontoeletronico.repository.UsuarioRepository;

@Service
public class PontoService {

	private PontoRepository repository;
	private UsuarioRepository usuarioRepository;
	public PontoService (PontoRepository repository, UsuarioRepository usuarioRepository) {
		this.repository = repository;
		this.usuarioRepository = usuarioRepository;
	}
	
	public PontoEntity criarPonto (PontoEntity pontoEntity) {
		return this.repository.save(pontoEntity);
	}
	
	public RelatorioHorasModel obterRelatorio (Long usuarioId) {
		List<PontoEntity> listaPonto = repository.findPontoByUsuario(usuarioId);
		Optional<UsuarioEntity> optional = usuarioRepository.findById(usuarioId);		
		List<Long> horasTrabalhadasPorDia = new ArrayList<Long>();
		RelatorioHorasModel model = new RelatorioHorasModel();
		
		
		Map<LocalDateTime, List<PontoEntity>> mapaHorarios = listaPonto.stream()
		.collect(Collectors.groupingBy(ponto -> ponto.getDataHoraBatida().truncatedTo(ChronoUnit.DAYS)));
		
		calcularHorasPorDia(horasTrabalhadasPorDia, mapaHorarios);
		
		model.setHorasTrabalhadas(horasTrabalhadasPorDia.stream().reduce(0L, Long::sum));
		model.setUsuario(optional.get());
		
		return model;
	}

	private void calcularHorasPorDia(List<Long> horasTrabalhadasPorDia,
			Map<LocalDateTime, List<PontoEntity>> mapaHorarios) {
		mapaHorarios.entrySet().stream().forEach(entrada -> {
			List<PontoEntity> lista = entrada.getValue();
			Map<TipoBatimento, List<PontoEntity>> collect = lista.stream().collect(Collectors.groupingBy(ponto -> ponto.getTipo()));
			PontoEntity pontoEntrada = collect.get(TipoBatimento.ENTRADA).get(0);
			PontoEntity pontoSaida = collect.get(TipoBatimento.SAIDA).get(0);
			
			horasTrabalhadasPorDia.add(pontoEntrada.getDataHoraBatida().until(pontoSaida.getDataHoraBatida(), ChronoUnit.HOURS));
		});
	}
}
