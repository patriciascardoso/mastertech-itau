package com.mastertech.pontoeletronico.model;

import com.mastertech.pontoeletronico.entity.UsuarioEntity;

public class RelatorioHorasModel {
	
	private Long horasTrabalhadas;
	private UsuarioEntity usuario;
	public Long getHorasTrabalhadas() {
		return horasTrabalhadas;
	}
	public void setHorasTrabalhadas(Long horasTrabalhadas) {
		this.horasTrabalhadas = horasTrabalhadas;
	}
	public UsuarioEntity getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}
	
	

}
