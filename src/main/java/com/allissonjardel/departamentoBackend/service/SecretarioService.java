package com.allissonjardel.departamentoBackend.service;

import java.util.List;
import java.util.Optional;

import com.allissonjardel.departamentoBackend.model.Secretario;


public interface SecretarioService {

	Secretario findById(Long id);
	List<Secretario> getAll();
	void deleteById(Long id);
	Secretario insert(Secretario entity);
	void update(Long id, Secretario entity);
	Optional<Secretario> getOptional(Long id);
}
