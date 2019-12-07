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

import com.allissonjardel.departamentoBackend.model.Secretario;
import com.allissonjardel.departamentoBackend.service.SecretarioService;
import com.allissonjardel.departamentoBackend.util.Views;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/secretario")
public class SecretarioController {

	@Autowired
	SecretarioService service;
	
	@GetMapping
	@JsonView(Views.Secretario.class)
	public ResponseEntity<List<Secretario>> getAll(){
		return ResponseEntity.ok().body(service.getAll());
	}
	
	@GetMapping("/{id}")
	@JsonView(Views.Secretario.class)
	public ResponseEntity<?> findById(@PathVariable Long id){
		Optional<Secretario> entity = service.getOptional(id);
		return entity.isPresent() ? ResponseEntity.ok().body(entity.get()) : 
									ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping
	@JsonView(Views.Secretario.class)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Secretario> insert(@RequestBody Secretario entity,  HttpServletResponse response){
		Secretario entitySave = service.insert(entity);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(entitySave.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(entitySave);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Secretario entity){
		service.update(id, entity);
		return ResponseEntity.ok().build();
	}
	
}


