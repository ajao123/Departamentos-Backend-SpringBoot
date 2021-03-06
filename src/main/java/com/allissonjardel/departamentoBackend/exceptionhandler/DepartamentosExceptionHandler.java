package com.allissonjardel.departamentoBackend.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.allissonjardel.departamentoBackend.util.Erro;

@ControllerAdvice
public class DepartamentosExceptionHandler extends ResponseEntityExceptionHandler{

	@Autowired
	private MessageSource messageSource;
	
	
	//Atributos que não podem ser lidos
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request){
		
		String messageUser = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String messageDeveloper = ex.toString();

        List<Erro> errors = Arrays.asList(new Erro(messageUser, messageDeveloper));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		
	}
	
	//Atributos inválidos
	//PS: O validator envia para cá
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
		List<Erro> errors = createListErrors(ex.getBindingResult());
		return super.handleExceptionInternal(ex, errors, headers, status, request);
	}
	
	//Integridade dos dados nao foi mantida, ex: Adicionar 2 objetos com atributo UNIQUE iguais
	@ExceptionHandler({DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        String userMessage = messageSource.getMessage("mensagem.integracao", null, LocaleContextHolder.getLocale());
        String developerMessage = ex.toString();

        List<Erro> errors = Arrays.asList(new Erro(userMessage, developerMessage));

        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
	
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
		
		String messageUser = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
		String messageDeveloper = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(messageUser, messageDeveloper));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	private List<Erro> createListErrors(BindingResult bindingResult){
		List<Erro> errors = new ArrayList<>();
		
		for(FieldError fieldError: bindingResult.getFieldErrors()) {
			String messageUser = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String messageDeveloper = fieldError.toString();
			errors.add(new Erro(messageUser, messageDeveloper));
		}
		
		return errors;
	}
	
	
	
}
