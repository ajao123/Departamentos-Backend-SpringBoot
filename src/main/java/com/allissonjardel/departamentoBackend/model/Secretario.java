package com.allissonjardel.departamentoBackend.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.allissonjardel.departamentoBackend.util.Views;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Secretario  extends Funcionario{

	@JsonView({Views.Departamento.class, Views.Secretario.class})
	@Column(nullable=false)
	private String grauEscolaridade;
	
	public Secretario() {
		// TODO Auto-generated constructor stub
	}
	
	public Secretario(Long id, String grauEscolaridade,  String nome, String endereco, String sexo, LocalDate dataNascimento, Double salario,
			Departamento departamento) {
		super(id, nome, endereco, sexo, dataNascimento, salario, departamento);
		this.grauEscolaridade = grauEscolaridade;
	}

	public String getGrauEscolaridade() {
		return grauEscolaridade;
	}

	public void setGrauEscolaridade(String grauEscolaridade) {
		this.grauEscolaridade = grauEscolaridade;
	}
	
	
	
}
