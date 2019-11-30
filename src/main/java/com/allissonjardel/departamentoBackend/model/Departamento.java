package com.allissonjardel.departamentoBackend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.allissonjardel.departamentoBackend.util.Views;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Departamento{

	@JsonView({Views.Departamento.class, Views.Pesquisador.class, Views.Secretario.class, Views.FuncionarioLimpeza.class})
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JsonView({Views.Departamento.class, Views.Pesquisador.class, Views.Secretario.class, Views.FuncionarioLimpeza.class})
	@Column(unique=true, nullable=false)
	private String name;
	
	@JsonView({Views.Departamento.class, Views.Pesquisador.class, Views.Secretario.class, Views.FuncionarioLimpeza.class})
	@Column(nullable=false)
	private String descricao;
	
	@JsonView({Views.Departamento.class})
	@OneToMany(mappedBy = "departamento", cascade=CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Pesquisador> pesquisadores;
	
	@JsonView({Views.Departamento.class})
	@OneToMany(mappedBy = "departamento", cascade=CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Secretario> secretarios;
	
	@JsonView({Views.Departamento.class})
	@OneToMany(mappedBy = "departamento", cascade=CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<FuncionarioLimpeza> funcionariosLimpeza;
	
	public Departamento() {
		pesquisadores = new ArrayList<>();
		secretarios = new ArrayList<>();
		// TODO Auto-generated constructor stub
	}

	public Departamento(Long id, String name, String descricao) {
		pesquisadores = new ArrayList<>();
		secretarios = new ArrayList<>();
		this.id = id;
		this.name = name;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Pesquisador> getPesquisadores() {
		return pesquisadores;
	}

	public void setPesquisadores(List<Pesquisador> pesquisadores) {
		this.pesquisadores = pesquisadores;
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
		Departamento other = (Departamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}