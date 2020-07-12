package com.mastertech.pontoeletronico.model;

import com.mastertech.pontoeletronico.enumeration.TipoBatimento;

public class PontoModel {

	private Long id;
	private UsuarioModel usuario;
	private String dataHoraBatida;
	private TipoBatimento tipo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UsuarioModel getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}
	public String getDataHoraBatida() {
		return dataHoraBatida;
	}
	public void setDataHoraBatida(String dataHoraBatida) {
		this.dataHoraBatida = dataHoraBatida;
	}
	public TipoBatimento getTipo() {
		return tipo;
	}
	public void setTipo(TipoBatimento tipo) {
		this.tipo = tipo;
	}
	
	
}
