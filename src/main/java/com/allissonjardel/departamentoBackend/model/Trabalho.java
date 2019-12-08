package com.allissonjardel.departamentoBackend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.allissonjardel.departamentoBackend.util.Views;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Trabalho {

	@JsonView({Views.Trabalho.class, Views.Projeto.class})
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JsonView({Views.Trabalho.class, Views.Pesquisador.class, Views.Projeto.class, Views.Departamento.class})
	@NotNull
	private Integer horasSemanais;
	
	@JsonView({Views.Trabalho.class, Views.Pesquisador.class})
	@ManyToOne
	@JoinColumn(name="projeto_id")
	@NotNull
	private Projeto projeto;
	
	@JsonView({Views.Trabalho.class, Views.Projeto.class, Views.Departamento.class})
	@ManyToOne
	@JoinColumn(name="pesquisador_id")
	@NotNull
	private Pesquisador pesquisador;
	
	public Trabalho() {
		// TODO Auto-generated constructor stub
	}

	public Trabalho(Long id, Projeto projeto, Pesquisador pesquisador, Integer horasSemanais) {
		this.id = id;
		this.projeto = projeto;
		this.pesquisador = pesquisador;
		this.horasSemanais = horasSemanais;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Pesquisador getPesquisador() {
		return pesquisador;
	}

	public void setPesquisador(Pesquisador pesquisador) {
		this.pesquisador = pesquisador;
	}

	public Integer getHorasSemanais() {
		return horasSemanais;
	}

	public void setHorasSemanais(Integer horasSemanais) {
		this.horasSemanais = horasSemanais;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trabalho other = (Trabalho) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
