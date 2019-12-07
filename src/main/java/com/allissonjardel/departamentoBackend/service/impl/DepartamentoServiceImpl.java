package com.allissonjardel.departamentoBackend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allissonjardel.departamentoBackend.model.Departamento;
import com.allissonjardel.departamentoBackend.repository.DepartamentoRepository;
import com.allissonjardel.departamentoBackend.service.DepartamentoService;

@Service
public class DepartamentoServiceImpl implements DepartamentoService{

	@Autowired
	DepartamentoRepository repository;
	
	@Override
	public Departamento findById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}

	@Override
	public List<Departamento> getAll() {
		return repository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
		
	}

	@Override
	public Departamento insert(Departamento entity) {
		return repository.save(entity);
	}

	@Override
	public void update(Long id, Departamento entity) {
		Departamento entity2 = repository.findById(id).get();
		BeanUtils.copyProperties(entity, entity2, "id");
		repository.save(entity2);
		
	}

	@Override
	public Optional<Departamento> getOptional(Long id) {
		return repository.findById(id);
	}
	
	

}
