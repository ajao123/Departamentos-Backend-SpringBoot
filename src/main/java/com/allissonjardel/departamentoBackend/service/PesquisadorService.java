package com.allissonjardel.departamentoBackend.service;

import java.util.List;
import java.util.Optional;

import com.allissonjardel.departamentoBackend.model.Pesquisador;


public interface PesquisadorService {

	Pesquisador findById(Long id);
	List<Pesquisador> getAll();
	void deleteById(Long id);
	Pesquisador insert(Pesquisador entity);
	void update(Long id, Pesquisador entity);
	Optional<Pesquisador> getOptional(Long id);
}
