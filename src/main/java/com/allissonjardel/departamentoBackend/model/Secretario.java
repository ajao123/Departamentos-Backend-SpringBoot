package com.allissonjardel.departamentoBackend.model;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import com.allissonjardel.departamentoBackend.util.Views;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@DiscriminatorValue(value = "secretario")
public class Secretario  extends Funcionario{

	private static final long serialVersionUID = 1L;
	
	@JsonView({Views.Departamento.class, Views.Secretario.class, Views.Dependente.class})
	private String grauEscolaridade;
	
	public Secretario() {
		// TODO Auto-generated constructor stub
	}
	
	public Secretario(Long id, String grauEscolaridade,  String nome, Endereco endereco, Integer sexo, LocalDate dataNascimento, Double salario,
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
