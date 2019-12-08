package com.allissonjardel.departamentoBackend.service;

import java.util.List;
import java.util.Optional;

import com.allissonjardel.departamentoBackend.model.Trabalho;


public interface TrabalhoService {

	Trabalho findById(Long id);
	List<Trabalho> getAll();
	void deleteById(Long id);
	Trabalho insert(Trabalho entity);
	Trabalho update(Long id, Trabalho entity);
	Optional<Trabalho> getOptional(Long id);
}
