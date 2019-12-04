package com.allissonjardel.departamentoBackend.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allissonjardel.departamentoBackend.model.Dependente;
import com.allissonjardel.departamentoBackend.model.Funcionario;
import com.allissonjardel.departamentoBackend.repository.DependenteRepository;
import com.allissonjardel.departamentoBackend.repository.FuncionarioLimpezaRepository;
import com.allissonjardel.departamentoBackend.repository.PesquisadorRepository;
import com.allissonjardel.departamentoBackend.repository.SecretarioRepository;
import com.allissonjardel.departamentoBackend.service.DependenteService;

@Service
public class DependenteServiceImpl implements DependenteService{

	@Autowired
	DependenteRepository repository;
	
	@Autowired
	PesquisadorRepository pRepository;
	
	@Autowired
	SecretarioRepository sRepository;
	
	@Autowired
	FuncionarioLimpezaRepository fRepository;
	
	@Override
	public Dependente findById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}

	@Override
	public List<Dependente> getAll() {
		return repository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
		
	}

	@Override
	public void insert(Dependente entity) {
		repository.save(entity);
	}

	@Override
	public void update(Long id, Dependente entity) {
		Dependente entity2 = repository.findById(id).get();
		BeanUtils.copyProperties(entity, entity2, "id","funcionario");
		repository.save(entity2);
		
	}

}
