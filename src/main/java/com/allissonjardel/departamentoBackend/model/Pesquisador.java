package com.allissonjardel.departamentoBackend.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.allissonjardel.departamentoBackend.util.Views;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@DiscriminatorValue(value = "pesquisador")
public class Pesquisador extends Funcionario{
	

	private static final long serialVersionUID = 1L;
	
	@JsonView({Views.Departamento.class, Views.Pesquisador.class, Views.Dependente.class, Views.Trabalho.class, Views.Projeto.class})
	private String areaAtuacao;
	
	@JsonView({Views.Pesquisador.class})
	@OneToMany(mappedBy = "pesquisador", cascade=CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Trabalho> trabalhos;
	
	public Pesquisador() {
		trabalhos = new ArrayList<>();
		// TODO Auto-generated constructor stub
	}

	public Pesquisador(Long id, String areaAtuacao,  String nome, Endereco endereco, Integer sexo, LocalDate dataNascimento, Double salario,
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

	public List<Trabalho> getTrabalhos() {
		return trabalhos;
	}

	public void setTrabalhos(List<Trabalho> trabalhos) {
		this.trabalhos = trabalhos;
	}

}














