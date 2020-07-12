package com.mastertech.pontoeletronico.model;

import javax.validation.constraints.NotBlank;

public class UsuarioModel {

	private Long id;

	@NotBlank
	private String nome;

	@NotBlank
	private String cpf;

	@NotBlank
	private String email;

	@NotBlank
	private String dataCadastro;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}


}
