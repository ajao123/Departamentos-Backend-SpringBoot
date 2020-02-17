package com.allissonjardel.departamentoBackend.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.allissonjardel.departamentoBackend.model.Departamento;
import com.allissonjardel.departamentoBackend.model.Funcionario;
import com.allissonjardel.departamentoBackend.model.FuncionarioLimpeza;
import com.allissonjardel.departamentoBackend.model.Pesquisador;
import com.allissonjardel.departamentoBackend.model.Projeto;
import com.allissonjardel.departamentoBackend.model.Secretario;
import com.allissonjardel.departamentoBackend.util.Views;
import com.fasterxml.jackson.annotation.JsonView;

public class DepartamentoDTO{
	
	@JsonView({Views.Departamento.class})
	private Long id;

	@JsonView({Views.Departamento.class})
	private String name;
	
	@JsonView({Views.Departamento.class})
	private String descricao;
	
	@JsonView({Views.Departamento.class})
	private List<Pesquisador> pesquisadores;
	
	@JsonView({Views.Departamento.class})
	private List<Secretario> secretarios;
	
	@JsonView({Views.Departamento.class})
	private List<FuncionarioLimpeza> funcionariosLimpeza;
	
	@JsonView({Views.Departamento.class})
	private List<Projeto> projetos;
	
	public DepartamentoDTO() {
		// TODO Auto-generated constructor stub
	}

	public DepartamentoDTO(Departamento departamento) {
		this.id = departamento.getId();
		this.name = departamento.getNome();
		this.descricao = departamento.getDescricao();
		pesquisadores = new ArrayList<>();
		secretarios = new ArrayList<>();
		funcionariosLimpeza = new ArrayList<>();
		projetos =  departamento.getProjetos();
		
		if(projetos == null)
			projetos =  new ArrayList<>();
		
		if(departamento.getFuncionarios() != null) {
			for(Funcionario funcionario : departamento.getFuncionarios()) {
				if(funcionario instanceof Pesquisador) {
					pesquisadores.add((Pesquisador) (funcionario));			
				}else if(funcionario instanceof Secretario) {
					secretarios.add((Secretario) (funcionario));		
				}else if(funcionario instanceof FuncionarioLimpeza) {
					funcionariosLimpeza.add((FuncionarioLimpeza) (funcionario));		
				}
			}
		}
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

	public List<Secretario> getSecretarios() {
		return secretarios;
	}

	public void setSecretarios(List<Secretario> secretarios) {
		this.secretarios = secretarios;
	}

	public List<FuncionarioLimpeza> getFuncionariosLimpeza() {
		return funcionariosLimpeza;
	}

	public void setFuncionariosLimpeza(List<FuncionarioLimpeza> funcionariosLimpeza) {
		this.funcionariosLimpeza = funcionariosLimpeza;
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	@Override
	public String toString() {
		return "DepartamentoDTO [id=" + id + ", name=" + name + ", descricao=" + descricao + ", pesquisadores="
				+ pesquisadores + ", secretarios=" + secretarios + ", funcionariosLimpeza=" + funcionariosLimpeza
				+ ", projetos=" + projetos + "]";
	}
	
}
