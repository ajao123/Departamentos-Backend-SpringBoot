package com.allissonjardel.departamentoBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allissonjardel.departamentoBackend.model.Departamento;
import com.allissonjardel.departamentoBackend.service.DepartamentoService;
import com.allissonjardel.departamentoBackend.util.Views;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/departamento")
public class DepartamentoController {

	@Autowired
	DepartamentoService service;
	
	@GetMapping
	@JsonView(Views.Departamento.class)
	public ResponseEntity<List<Departamento>> getAll(){
		return ResponseEntity.ok().body(service.getAll());
	}
	
	@GetMapping("/{id}")
	@JsonView(Views.Departamento.class)
	public ResponseEntity<Departamento> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(service.findById(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Departamento entity){
		service.insert(entity);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Departamento entity){
		service.update(id, entity);
		return ResponseEntity.ok().build();
	}
	
}


