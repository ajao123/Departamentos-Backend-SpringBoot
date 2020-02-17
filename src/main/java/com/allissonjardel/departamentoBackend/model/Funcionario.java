package com.allissonjardel.departamentoBackend.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.allissonjardel.departamentoBackend.model.enums.Sexo;
import com.allissonjardel.departamentoBackend.util.Views;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "dtype")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Pesquisador.class, name = "pesquisador"),
    @JsonSubTypes.Type(value = Secretario.class, name = "secretario"),
    @JsonSubTypes.Type(value = FuncionarioLimpeza.class, name = "funcionarioLimpeza")
})
public abstract class Funcionario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonView({Views.Departamento.class, Views.Trabalho.class, Views.Pesquisador.class, Views.Secretario.class, Views.FuncionarioLimpeza.class, Views.Dependente.class, Views.Projeto.class})
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonView({Views.Departamento.class, Views.Trabalho.class, Views.Pesquisador.class, Views.Secretario.class, Views.FuncionarioLimpeza.class, Views.Dependente.class, Views.Projeto.class})
	@NotNull
	private String nome;
	
	//Testar sem OnDelete o relacionamento unidirecional
	@Valid
	@JsonView({Views.Departamento.class, Views.Trabalho.class, Views.Pesquisador.class, Views.Secretario.class, Views.FuncionarioLimpeza.class, Views.Dependente.class, Views.Projeto.class})
	@OneToOne(cascade=CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Endereco endereco;
	
	@JsonView({Views.Departamento.class, Views.Pesquisador.class, Views.Trabalho.class, Views.Secretario.class, Views.FuncionarioLimpeza.class, Views.Dependente.class, Views.Projeto.class})
	@NotNull
	private Integer sexo;
	
	@JsonView({Views.Departamento.class, Views.Pesquisador.class, Views.Trabalho.class, Views.Secretario.class, Views.FuncionarioLimpeza.class, Views.Dependente.class, Views.Projeto.class})
	@NotNull
	private LocalDate dataNascimento;
	
	@JsonView({Views.Departamento.class, Views.Pesquisador.class, Views.Trabalho.class, Views.Secretario.class, Views.FuncionarioLimpeza.class, Views.Dependente.class, Views.Projeto.class})
	@NotNull
	private Double salario;
	
	@JsonView({Views.Pesquisador.class, Views.Secretario.class, Views.Trabalho.class, Views.FuncionarioLimpeza.class, Views.Projeto.class})
	@ManyToOne
	@JoinColumn(name="departamento_id")
	@NotNull
	private Departamento departamento;

	@JsonView({Views.Departamento.class, Views.Pesquisador.class, Views.Secretario.class, Views.FuncionarioLimpeza.class})
	@OneToMany(mappedBy = "funcionario", cascade=CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Dependente> dependentes;
	
	public Funcionario() {
		dependentes = new ArrayList<>();
		// TODO Auto-generated constructor stub
	}
	
	public Funcionario(Long id, String nome, Endereco endereco, Integer sexo, LocalDate dataNascimento, Double salario,
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	
	public Sexo getSexo() {
		return (sexo == null) ? null : Sexo.valueOf(sexo);
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo.getCode();
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

	public List<Dependente> getDependentes() {
		return dependentes;
	}
	
	public void setDependentes(List<Dependente> dependentes) {
		this.dependentes = dependentes;
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
