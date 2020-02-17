package com.allissonjardel.departamentoBackend.controller;

import java.net.URI;
//import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.allissonjardel.departamentoBackend.model.Departamento;
import com.allissonjardel.departamentoBackend.model.dto.DepartamentoDTO;
import com.allissonjardel.departamentoBackend.service.DepartamentoService;
import com.allissonjardel.departamentoBackend.util.Views;
import com.allissonjardel.departamentoBackend.validator.DepartamentoValidator;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/departamento")
public class DepartamentoController {

	@Autowired
	DepartamentoService service;

	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new DepartamentoValidator(service));
	}
	
	@GetMapping
	@JsonView(Views.Departamento.class)
	public ResponseEntity<List<DepartamentoDTO>> getAll(){
		List<Departamento> list = service.getAll();
		List<DepartamentoDTO> listDTO = list.stream().map(x -> new DepartamentoDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
		
	@GetMapping("/{id}")
	@JsonView(Views.Departamento.class)
	public ResponseEntity<?> findById(@PathVariable Long id){
		Optional<Departamento> entity = service.getOptional(id);
		return entity.isPresent() ? ResponseEntity.ok().body(new DepartamentoDTO(entity.get())) : 
									ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	@JsonView(Views.Departamento.class)
	public ResponseEntity<DepartamentoDTO> insert(@Valid @RequestBody Departamento entity){
		Departamento entitySave = service.insert(entity);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(entitySave.getId()).toUri();
		
		//response.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.created(uri).body(new DepartamentoDTO(entitySave));
	}

	@PutMapping
	@JsonView(Views.Departamento.class)
	public ResponseEntity<DepartamentoDTO> update(@Valid @RequestBody Departamento entity){
		
		Optional<Departamento> optional = service.getOptional(entity.getId());
		
		if(!optional.isPresent()) 
			throw new EmptyResultDataAccessException(1);
		
		return ResponseEntity.ok().body(new DepartamentoDTO(service.update(entity.getId(), entity)));
	}
	
}


