package com.allissonjardel.departamentoBackend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.allissonjardel.departamentoBackend.util.Views;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Projeto {
	
	@JsonView({Views.Departamento.class, Views.Projeto.class, Views.Trabalho.class, Views.Pesquisador.class})
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JsonView({Views.Departamento.class, Views.Projeto.class, Views.Trabalho.class, Views.Pesquisador.class})
	private String nome;
	
	@JsonView({Views.Departamento.class, Views.Projeto.class, Views.Trabalho.class, Views.Pesquisador.class})
	private String periodoTempo;
	
	@JsonView({Views.Projeto.class, Views.Trabalho.class, Views.Pesquisador.class})
	@ManyToOne
	@JoinColumn(name="departamento_id", nullable=false)
	private Departamento departamento;
	
	@JsonView({Views.Projeto.class, Views.Departamento.class})
	@OneToMany(mappedBy = "projeto", cascade=CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Trabalho> trabalhos;
	
	public Projeto() {
		// TODO Auto-generated constructor stub
		trabalhos = new ArrayList<>();
	}

	public Projeto(Long id, Departamento departamento, String nome, String periodoTempo) {
		trabalhos = new ArrayList<>();
		this.id = id;
		this.departamento = departamento;
		this.nome = nome;
		this.periodoTempo = periodoTempo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPeriodoTempo() {
		return periodoTempo;
	}

	public void setPeriodoTempo(String periodoTempo) {
		this.periodoTempo = periodoTempo;
	}

	public List<Trabalho> getTrabalhos() {
		return trabalhos;
	}

	public void setTrabalhos(List<Trabalho> trabalhos) {
		this.trabalhos = trabalhos;
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
		Projeto other = (Projeto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
