package com.muriloguerreiro.fooddelivery.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.muriloguerreiro.fooddelivery.domain.exception.DomainException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(DomainException.class)
	public ResponseEntity<Object> handleDomainException(DomainException ex, WebRequest request) {
		var status = HttpStatus.BAD_REQUEST;
		var response = new Response();
		response.setStatus(status.value());
		response.setTitle(ex.getMessage());
		response.setDateTime(LocalDateTime.now());
		
		return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		var fields = new ArrayList<Response.Field>();
		
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String name = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			
			fields.add(new Response.Field(name, message));
		}
		
		var response = new Response(); 
		response.setStatus(status.value());
		response.setTitle("One or more invalid fields.");
		response.setDateTime(LocalDateTime.now());
		response.setFields(fields);
		
		return super.handleExceptionInternal(ex, response, headers, status, request);
	}

}
