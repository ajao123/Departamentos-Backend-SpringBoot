package com.allissonjardel.departamentoBackend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allissonjardel.departamentoBackend.model.Trabalho;
import com.allissonjardel.departamentoBackend.repository.TrabalhoRepository;
import com.allissonjardel.departamentoBackend.service.TrabalhoService;

@Service
public class TrabalhoServiceImpl implements TrabalhoService{

	@Autowired
	TrabalhoRepository repository;
	
	@Override
	public Trabalho findById(Long id) {
		
		return repository.findById(id).get();
	}

	@Override
	public List<Trabalho> getAll() {
		return repository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
		
	}

	@Override
	public Trabalho insert(Trabalho entity) {
		return repository.save(entity);
	}

	@Override
	public Trabalho update(Long id, Trabalho entity) {
		Trabalho entity2 = repository.findById(id).get();
		BeanUtils.copyProperties(entity, entity2, "id");
		return repository.save(entity2);
		
	}

	@Override
	public Optional<Trabalho> getOptional(Long id) {
		return repository.findById(id);
	}
	
}
