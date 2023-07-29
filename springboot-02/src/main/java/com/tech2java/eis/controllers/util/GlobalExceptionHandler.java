package com.tech2java.eis.controllers.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tech2java.eis.domain.Response;

@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	//Used for validating input data
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		Response response=new Response();
		response.setStatusCode(status.toString());
		response.setStatusMessage(ex.getBindingResult().toString());
		
		return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({Exception.class})
	public ResponseEntity<Response> exceptionHandler(Exception exception){
		
		Response response=new Response();
		response.setStatusCode("500");
		response.setStatusMessage(exception.getMessage());
		
		
		return new ResponseEntity<Response>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
