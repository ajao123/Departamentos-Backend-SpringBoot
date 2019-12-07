package com.allissonjardel.departamentoBackend.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.allissonjardel.departamentoBackend.model.Projeto;
import com.allissonjardel.departamentoBackend.service.ProjetoService;
import com.allissonjardel.departamentoBackend.util.Views;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/projeto")
public class ProjetoController {

	@Autowired
	ProjetoService service;
	
	@GetMapping
	@JsonView(Views.Projeto.class)
	public ResponseEntity<List<Projeto>> getAll(){
		return ResponseEntity.ok().body(service.getAll());
	}
	
	@GetMapping("/{id}")
	@JsonView(Views.Projeto.class)
	public ResponseEntity<?> findById(@PathVariable Long id){
		Optional<Projeto> entity = service.getOptional(id);
		return entity.isPresent() ? ResponseEntity.ok().body(entity.get()) : 
									ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping
	@JsonView(Views.Projeto.class)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Projeto> insert(@RequestBody Projeto entity,  HttpServletResponse response){
		Projeto entitySave = service.insert(entity);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(entitySave.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(entitySave);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Projeto entity){
		service.update(id, entity);
		return ResponseEntity.ok().build();
	}
	
}


