package com.mastertech.pontoeletronico.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mastertech.pontoeletronico.converter.PontoConverter;
import com.mastertech.pontoeletronico.entity.PontoEntity;
import com.mastertech.pontoeletronico.model.PontoModel;
import com.mastertech.pontoeletronico.model.RelatorioHorasModel;
import com.mastertech.pontoeletronico.service.PontoService;

@RestController
@RequestMapping("/ponto")
public class PontoController {
	
	private PontoConverter converter;
	private PontoService service;
	
	public PontoController(PontoConverter converter,  PontoService service) {
		this.converter = converter;
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<PontoEntity> marcarPontoEletronico(@RequestBody PontoModel model){
		
		PontoEntity pontoEntity = converter.convertFromModelToEntity(model);
		PontoEntity ponto = service.criarPonto(pontoEntity);
		
		return ResponseEntity.ok(ponto);
	}
	
	@GetMapping("/{usuarioId}")
	public ResponseEntity<RelatorioHorasModel> obterPontoPorUsuario(@PathVariable Long usuarioId) {
		RelatorioHorasModel relatorioHorasModel = service.obterRelatorio(usuarioId);
		
		return ResponseEntity.ok(relatorioHorasModel);
	}

}
