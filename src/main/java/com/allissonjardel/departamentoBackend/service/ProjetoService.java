package com.allissonjardel.departamentoBackend.service;

import java.util.List;

import com.allissonjardel.departamentoBackend.model.Projeto;


public interface ProjetoService {

	Projeto findById(Long id);
	List<Projeto> getAll();
	void deleteById(Long id);
	void insert(Projeto entity);
	void update(Long id, Projeto entity);
	
}
