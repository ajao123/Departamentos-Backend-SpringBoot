package com.allissonjardel.departamentoBackend.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DepartamentosExceptionHandler extends ResponseEntityExceptionHandler{

	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request){
			
		String messageUser = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String messageDeveloper = ex.getCause().toString();
		return handleExceptionInternal(ex, new Error(messageUser, messageDeveloper), headers, HttpStatus.BAD_REQUEST, request);
	}
	
	public static class Error{
		private String messageUser;
		private String messageDeveloper;
		
		public Error(String messageUser, String messageDeveloper) {
			this.messageUser = messageUser;
			this.messageDeveloper = messageDeveloper;
		}

		public String getMessageUser() {
			return messageUser;
		}

		public String getMessageDeveloper() {
			return messageDeveloper;
		}		
		
	}
	
}
