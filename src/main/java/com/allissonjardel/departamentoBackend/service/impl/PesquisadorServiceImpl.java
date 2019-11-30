package com.allissonjardel.departamentoBackend.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allissonjardel.departamentoBackend.model.Pesquisador;
import com.allissonjardel.departamentoBackend.repository.PesquisadorRepository;
import com.allissonjardel.departamentoBackend.service.PesquisadorService;

@Service
public class PesquisadorServiceImpl implements PesquisadorService{

	@Autowired
	PesquisadorRepository repository;
	
	@Override
	public Pesquisador findById(Long id) {
		
		return repository.findById(id).get();
	}

	@Override
	public List<Pesquisador> getAll() {
		return repository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
		
	}

	@Override
	public void insert(Pesquisador entity) {
		repository.save(entity);
	}

	@Override
	public void update(Long id, Pesquisador entity) {
		Pesquisador entity2 = repository.findById(id).get();
		BeanUtils.copyProperties(entity, entity2, "id");
		repository.save(entity2);
		
	}

}