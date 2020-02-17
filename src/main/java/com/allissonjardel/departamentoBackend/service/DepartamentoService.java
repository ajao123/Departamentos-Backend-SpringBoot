package com.allissonjardel.departamentoBackend.service;

import java.util.List;
import java.util.Optional;

import com.allissonjardel.departamentoBackend.model.Departamento;


public interface DepartamentoService {

	Departamento findById(Long id);
	List<Departamento> getAll();
	void deleteById(Long id);
	Departamento insert(Departamento entity);
	Departamento update(Long id, Departamento entity);
	Optional<Departamento> getOptional(Long id);
	Integer existByName(String name);
	
}
