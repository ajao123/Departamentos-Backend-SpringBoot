package com.allissonjardel.departamentoBackend.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.allissonjardel.departamentoBackend.util.Views;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class FuncionarioLimpeza extends Funcionario{
	
	@JsonView({Views.Departamento.class, Views.FuncionarioLimpeza.class})
	@Column(nullable=false)
	private String cargo;
	
	@JsonView({Views.Departamento.class, Views.FuncionarioLimpeza.class})
	@Column(nullable=false)
	private String jornadaTrabalho;
	
	public FuncionarioLimpeza() {
		// TODO Auto-generated constructor stub
	}
	
	public FuncionarioLimpeza(Long id, String cargo, String jornadaTrabalho,  String nome, Endereco endereco, Integer sexo, LocalDate dataNascimento, Double salario,
			Departamento departamento) {
		super(id, nome, endereco, sexo, dataNascimento, salario, departamento);
		this.cargo = cargo;
		this.jornadaTrabalho = jornadaTrabalho;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getJornadaTrabalho() {
		return jornadaTrabalho;
	}

	public void setJornadaTrabalho(String jornadaTrabalho) {
		this.jornadaTrabalho = jornadaTrabalho;
	}
	
}
