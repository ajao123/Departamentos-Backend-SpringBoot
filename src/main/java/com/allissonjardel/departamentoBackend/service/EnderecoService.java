package com.allissonjardel.departamentoBackend.service;

import java.util.List;
import java.util.Optional;

import com.allissonjardel.departamentoBackend.model.Endereco;


public interface EnderecoService {

	Endereco findById(Long id);
	List<Endereco> getAll();
	void deleteById(Long id);
	Endereco insert(Endereco entity);
	Endereco update(Long id, Endereco entity);
	Optional<Endereco> getOptional(Long id);
}
