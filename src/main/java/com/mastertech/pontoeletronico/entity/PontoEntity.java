package com.mastertech.pontoeletronico.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mastertech.pontoeletronico.enumeration.TipoBatimento;

@Entity
@Table(name="TB_PONTO")
public class PontoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private LocalDateTime dataHoraBatida;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	@JsonBackReference
	private UsuarioEntity usuario;

	@Enumerated(EnumType.STRING)
	private TipoBatimento tipo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataHoraBatida() {
		return dataHoraBatida;
	}

	public void setDataHoraBatida(LocalDateTime dataHoraBatida) {
		this.dataHoraBatida = dataHoraBatida;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public TipoBatimento getTipo() {
		return tipo;
	}

	public void setTipo(TipoBatimento tipo) {
		this.tipo = tipo;
	}


}
