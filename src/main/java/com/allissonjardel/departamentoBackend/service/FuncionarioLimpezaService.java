package com.allissonjardel.departamentoBackend.service;

import java.util.List;
import java.util.Optional;

import com.allissonjardel.departamentoBackend.model.FuncionarioLimpeza;


public interface FuncionarioLimpezaService {

	FuncionarioLimpeza findById(Long id);
	List<FuncionarioLimpeza> getAll();
	void deleteById(Long id);
	FuncionarioLimpeza insert(FuncionarioLimpeza entity);
	void update(Long id, FuncionarioLimpeza entity);
	Optional<FuncionarioLimpeza> getOptional(Long id);
}
