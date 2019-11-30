package com.allissonjardel.departamentoBackend.service;

import java.util.List;



import com.allissonjardel.departamentoBackend.model.Endereco;


public interface EnderecoService {

	Endereco findById(Long id);
	List<Endereco> getAll();
	void deleteById(Long id);
	void insert(Endereco entity);
	void update(Long id, Endereco entity);
	
}
