package com.allissonjardel.departamentoBackend.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.allissonjardel.departamentoBackend.util.Views;
import com.fasterxml.jackson.annotation.JsonView;

@MappedSuperclass
public abstract class Funcionario {

	@JsonView({Views.Departamento.class, Views.Pesquisador.class, Views.Secretario.class, Views.FuncionarioLimpeza.class})
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonView({Views.Departamento.class, Views.Pesquisador.class, Views.Secretario.class, Views.FuncionarioLimpeza.class})
	@Column(nullable=false)
	private String nome;
	
	@JsonView({Views.Departamento.class, Views.Pesquisador.class, Views.Secretario.class, Views.FuncionarioLimpeza.class})
	@Column(nullable=false)
	private String endereco;
	
	@JsonView({Views.Departamento.class, Views.Pesquisador.class, Views.Secretario.class, Views.FuncionarioLimpeza.class})
	@Column(nullable=false)
	private String sexo;
	
	@JsonView({Views.Departamento.class, Views.Pesquisador.class, Views.Secretario.class, Views.FuncionarioLimpeza.class})
	@Column(nullable=false)
	private LocalDate dataNascimento;
	
	@JsonView({Views.Departamento.class, Views.Pesquisador.class, Views.Secretario.class, Views.FuncionarioLimpeza.class})
	@Column(nullable=false)
	private Double salario;
	
	@JsonView({Views.Pesquisador.class, Views.Secretario.class, Views.FuncionarioLimpeza.class})
	@ManyToOne
	@JoinColumn(name="departamento_id", nullable=false)
	private Departamento departamento;
	
	public Funcionario() {
		// TODO Auto-generated constructor stub
	}
	
	public Funcionario(Long id, String nome, String endereco, String sexo, LocalDate dataNascimento, Double salario,
			Departamento departamento) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.salario = salario;
		this.departamento = departamento;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	
	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
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
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
