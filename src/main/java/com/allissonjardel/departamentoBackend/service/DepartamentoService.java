package com.allissonjardel.departamentoBackend.service;

import java.util.List;

import com.allissonjardel.departamentoBackend.model.Departamento;


public interface DepartamentoService {

	Departamento findById(Long id);
	List<Departamento> getAll();
	void deleteById(Long id);
	void insert(Departamento entity);
	void update(Long id, Departamento entity);
	
}
