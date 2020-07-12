package com.mastertech.pontoeletronico.enumeration;

public enum TipoBatimento {

	ENTRADA("entrada"), SAIDA("saida");

	TipoBatimento(String tipo) {
		this.tipo = tipo;
	}

	private String tipo;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


}
