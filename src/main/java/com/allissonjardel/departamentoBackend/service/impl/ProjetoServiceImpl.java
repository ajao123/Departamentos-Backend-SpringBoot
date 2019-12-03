package com.allissonjardel.departamentoBackend.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allissonjardel.departamentoBackend.model.Projeto;
import com.allissonjardel.departamentoBackend.repository.ProjetoRepository;
import com.allissonjardel.departamentoBackend.service.ProjetoService;

@Service
public class ProjetoServiceImpl implements ProjetoService{

	@Autowired
	ProjetoRepository repository;
	
	@Override
	public Projeto findById(Long id) {
		
		return repository.findById(id).get();
	}

	@Override
	public List<Projeto> getAll() {
		return repository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
		
	}

	@Override
	public void insert(Projeto entity) {
		repository.save(entity);
	}

	@Override
	public void update(Long id, Projeto entity) {
		Projeto entity2 = repository.findById(id).get();
		BeanUtils.copyProperties(entity, entity2, "id");
		repository.save(entity2);
		
	}

}
