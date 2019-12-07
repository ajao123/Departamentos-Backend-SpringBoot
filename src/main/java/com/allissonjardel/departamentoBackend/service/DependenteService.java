package com.allissonjardel.departamentoBackend.service;

import java.util.List;
import java.util.Optional;

import com.allissonjardel.departamentoBackend.model.Dependente;


public interface DependenteService {

	Dependente findById(Long id);
	List<Dependente> getAll();
	void deleteById(Long id);
	Dependente insert(Dependente entity);
	void update(Long id, Dependente entity);
	Optional<Dependente> getOptional(Long id);
}
