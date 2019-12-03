package com.allissonjardel.departamentoBackend.service;

import java.util.List;

import com.allissonjardel.departamentoBackend.model.Dependente;


public interface DependenteService {

	Dependente findById(Long id);
	List<Dependente> getAll();
	void deleteById(Long id);
	void insert(Long id, Dependente entity);
	void update(Long id, Dependente entity);
	
}
