package com.allissonjardel.departamentoBackend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.allissonjardel.departamentoBackend.util.Views;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Endereco {
	
	@JsonView({Views.Departamento.class, Views.Pesquisador.class, Views.Secretario.class, Views.FuncionarioLimpeza.class, Views.Dependente.class})
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@JsonView({Views.Departamento.class, Views.Pesquisador.class, Views.Secretario.class, Views.FuncionarioLimpeza.class, Views.Dependente.class})
	@NotNull
	private String rua;
	
	@JsonView({Views.Departamento.class, Views.Pesquisador.class, Views.Secretario.class, Views.FuncionarioLimpeza.class, Views.Dependente.class})
	@NotNull
	private Integer numero;
	
	@JsonView({Views.Departamento.class, Views.Pesquisador.class, Views.Secretario.class, Views.FuncionarioLimpeza.class, Views.Dependente.class})
	@NotNull
	private String complemento;
	
	@JsonView({Views.Departamento.class, Views.Pesquisador.class, Views.Secretario.class, Views.FuncionarioLimpeza.class, Views.Dependente.class})
	@NotNull
	private String bairro;
	
	@JsonView({Views.Departamento.class, Views.Pesquisador.class, Views.Secretario.class, Views.FuncionarioLimpeza.class, Views.Dependente.class})
	@NotNull
	private String cidade;
	
	@JsonView({Views.Departamento.class, Views.Pesquisador.class, Views.Secretario.class, Views.FuncionarioLimpeza.class, Views.Dependente.class})
	@NotNull
	private String estado;
	
	@JsonView({Views.Departamento.class, Views.Pesquisador.class, Views.Secretario.class, Views.FuncionarioLimpeza.class, Views.Dependente.class})
	@NotNull
	private String pais;
	
	public Endereco() {
		// TODO Auto-generated constructor stub
	}

	public Endereco(Long id, String rua, Integer numero, String complemento, String bairro,
			String cidade, String estado, String pais) {
		this.id = id;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
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
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
