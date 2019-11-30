package com.allissonjardel.departamentoBackend.service;

import java.util.List;

import com.allissonjardel.departamentoBackend.model.Secretario;


public interface SecretarioService {

	Secretario findById(Long id);
	List<Secretario> getAll();
	void deleteById(Long id);
	void insert(Secretario entity);
	void update(Long id, Secretario entity);
	
}
