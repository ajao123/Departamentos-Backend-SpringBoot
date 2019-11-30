package com.allissonjardel.departamentoBackend.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allissonjardel.departamentoBackend.model.Endereco;
import com.allissonjardel.departamentoBackend.repository.EnderecoRepository;
import com.allissonjardel.departamentoBackend.service.EnderecoService;

@Service
public class EnderecoServiceImpl implements EnderecoService{

	@Autowired
	EnderecoRepository repository;
	
	@Override
	public Endereco findById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}

	@Override
	public List<Endereco> getAll() {
		return repository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
		
	}

	@Override
	public void insert(Endereco entity) {
		repository.save(entity);
	}

	@Override
	public void update(Long id, Endereco entity) {
		Endereco entity2 = repository.findById(id).get();
		BeanUtils.copyProperties(entity, entity2, "id");
		repository.save(entity2);
		
	}

}