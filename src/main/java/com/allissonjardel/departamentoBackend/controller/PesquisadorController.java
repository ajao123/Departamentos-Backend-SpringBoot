package com.allissonjardel.departamentoBackend.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.allissonjardel.departamentoBackend.model.Pesquisador;
import com.allissonjardel.departamentoBackend.service.PesquisadorService;
import com.allissonjardel.departamentoBackend.util.Views;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/pesquisador")
public class PesquisadorController {

	@Autowired
	PesquisadorService service;
	
	@GetMapping
	@JsonView(Views.Pesquisador.class)
	public ResponseEntity<List<Pesquisador>> getAll(){
		return ResponseEntity.ok().body(service.getAll());
	}
	
	@GetMapping("/{id}")
	@JsonView(Views.Pesquisador.class)
	public ResponseEntity<?> findById(@PathVariable Long id){
		Optional<Pesquisador> entity = service.getOptional(id);
		return entity.isPresent() ? ResponseEntity.ok().body(entity.get()) : 
									ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping
	@JsonView(Views.Pesquisador.class)
	public ResponseEntity<Pesquisador> insert(@Valid @RequestBody Pesquisador entity,  HttpServletResponse response){
		Pesquisador entitySave = service.insert(entity);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(entitySave.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(entitySave);
	}
	
	@PutMapping("/{id}")
	@JsonView(Views.Pesquisador.class)
	public ResponseEntity<Pesquisador> update(@PathVariable Long id, @Valid @RequestBody Pesquisador entity){
		
		Optional<Pesquisador> optional = service.getOptional(id);
		
		if(!optional.isPresent()) 
			throw new EmptyResultDataAccessException(1);
		
		return ResponseEntity.ok().body(service.update(id, entity));
	}
	
}


