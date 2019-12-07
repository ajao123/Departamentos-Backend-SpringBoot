package com.allissonjardel.departamentoBackend.service;

import java.util.List;
import java.util.Optional;

import com.allissonjardel.departamentoBackend.model.Projeto;


public interface ProjetoService {

	Projeto findById(Long id);
	List<Projeto> getAll();
	void deleteById(Long id);
	Projeto insert(Projeto entity);
	void update(Long id, Projeto entity);
	Optional<Projeto> getOptional(Long id);
}
