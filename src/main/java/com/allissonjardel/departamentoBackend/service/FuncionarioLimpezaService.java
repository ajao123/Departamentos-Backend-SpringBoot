package com.allissonjardel.departamentoBackend.service;

import java.util.List;

import com.allissonjardel.departamentoBackend.model.FuncionarioLimpeza;


public interface FuncionarioLimpezaService {

	FuncionarioLimpeza findById(Long id);
	List<FuncionarioLimpeza> getAll();
	void deleteById(Long id);
	void insert(FuncionarioLimpeza entity);
	void update(Long id, FuncionarioLimpeza entity);
	
}
