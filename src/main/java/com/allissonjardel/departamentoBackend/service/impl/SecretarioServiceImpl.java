package com.allissonjardel.departamentoBackend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allissonjardel.departamentoBackend.model.Secretario;
import com.allissonjardel.departamentoBackend.repository.SecretarioRepository;
import com.allissonjardel.departamentoBackend.service.SecretarioService;

@Service
public class SecretarioServiceImpl implements SecretarioService{

	@Autowired
	SecretarioRepository repository;
	
	@Override
	public Secretario findById(Long id) {
		
		return repository.findById(id).get();
	}

	@Override
	public List<Secretario> getAll() {
		return repository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
		
	}

	@Override
	public Secretario insert(Secretario entity) {
		return repository.save(entity);
	}

	@Override
	public void update(Long id, Secretario entity) {
		Secretario entity2 = repository.findById(id).get();
		BeanUtils.copyProperties(entity, entity2, "id");
		repository.save(entity2);
		
	}

	@Override
	public Optional<Secretario> getOptional(Long id) {
		return repository.findById(id);
	}
	
}
