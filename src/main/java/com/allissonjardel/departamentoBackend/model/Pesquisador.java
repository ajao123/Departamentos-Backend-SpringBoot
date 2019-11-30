package com.allissonjardel.departamentoBackend.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.allissonjardel.departamentoBackend.util.Views;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Pesquisador extends Funcionario{
	
	@JsonView({Views.Departamento.class, Views.Pesquisador.class})
	@Column(nullable=false)
	private String areaAtuacao;
	
	public Pesquisador() {
		// TODO Auto-generated constructor stub
	}

	public Pesquisador(Long id, String areaAtuacao,  String nome, Endereco endereco, String sexo, LocalDate dataNascimento, Double salario,
			Departamento departamento) {
		super(id, nome, endereco, sexo, dataNascimento, salario, departamento);
		this.areaAtuacao = areaAtuacao;
	}

	public String getAreaAtuacao() {
		return areaAtuacao;
	}

	public void setAreaAtuacao(String areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}

}














