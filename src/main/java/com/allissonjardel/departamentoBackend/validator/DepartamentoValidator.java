package com.allissonjardel.departamentoBackend.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.allissonjardel.departamentoBackend.model.Departamento;
import com.allissonjardel.departamentoBackend.service.DepartamentoService;



public class DepartamentoValidator implements Validator{

	DepartamentoService service;
	
	public DepartamentoValidator(DepartamentoService service) {
		this.service = service;	
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Departamento.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Departamento entity = (Departamento) object;
		
		if(entity.getId() == null) {
			if(service.existByName(entity.getNome()) > 0) {     
//				System.out.println(service.existByName(entity.getNome()));
				errors.rejectValue("nome", "departamento.existe.nome");
			}	
		}
	
	}

}
