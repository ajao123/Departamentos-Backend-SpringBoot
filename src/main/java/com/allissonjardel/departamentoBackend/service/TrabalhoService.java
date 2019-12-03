package com.allissonjardel.departamentoBackend.service;

import java.util.List;

import com.allissonjardel.departamentoBackend.model.Trabalho;


public interface TrabalhoService {

	Trabalho findById(Long id);
	List<Trabalho> getAll();
	void deleteById(Long id);
	void insert(Trabalho entity);
	void update(Long id, Trabalho entity);
	
}
