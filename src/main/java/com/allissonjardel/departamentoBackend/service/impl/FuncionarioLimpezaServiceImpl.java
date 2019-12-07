package com.allissonjardel.departamentoBackend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allissonjardel.departamentoBackend.model.FuncionarioLimpeza;
import com.allissonjardel.departamentoBackend.repository.FuncionarioLimpezaRepository;
import com.allissonjardel.departamentoBackend.service.FuncionarioLimpezaService;

@Service
public class FuncionarioLimpezaServiceImpl implements FuncionarioLimpezaService{

	@Autowired
	FuncionarioLimpezaRepository repository;
	
	@Override
	public FuncionarioLimpeza findById(Long id) {
		
		return repository.findById(id).get();
	}

	@Override
	public List<FuncionarioLimpeza> getAll() {
		return repository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
		
	}

	@Override
	public FuncionarioLimpeza insert(FuncionarioLimpeza entity) {
		return repository.save(entity);
	}

	@Override
	public void update(Long id, FuncionarioLimpeza entity) {
		FuncionarioLimpeza entity2 = repository.findById(id).get();
		BeanUtils.copyProperties(entity, entity2, "id");
		repository.save(entity2);
		
	}

	@Override
	public Optional<FuncionarioLimpeza> getOptional(Long id) {
		return repository.findById(id);
	}
	
}
