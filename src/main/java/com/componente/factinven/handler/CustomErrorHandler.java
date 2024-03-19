package com.componente.factinven.handler;

import com.componente.factinven.emuns.EnumMessages;
import com.componente.factinven.responses.ResponseGenerico;

import java.sql.SQLIntegrityConstraintViolationException;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import com.componente.factinven.exceptions.CedulaEcException;

@RestControllerAdvice
public class CustomErrorHandler {
	
	   @ResponseStatus(HttpStatus.BAD_REQUEST)  
	    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	    public ResponseEntity<?> handleContraintViolationException( SQLIntegrityConstraintViolationException e, WebRequest request) {
	        ResponseGenerico<?> response = new ResponseGenerico<>();
	        response.setCodigoRespuestaName(HttpStatus.BAD_REQUEST.name());
	        response.setCodigoRespuestaValue(HttpStatus.BAD_REQUEST.value());
	        response.setMensaje(EnumMessages.ERROR.name() + ":" + e.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	   
	   
	   @ResponseStatus(HttpStatus.BAD_REQUEST)  
	    @ExceptionHandler(CedulaEcException.class)
	    public ResponseEntity<?> handleCedulaEcException( CedulaEcException e, WebRequest request) {
	        ResponseGenerico<?> response = new ResponseGenerico<>();
	        response.setCodigoRespuestaName(HttpStatus.BAD_REQUEST.name());
	        response.setCodigoRespuestaValue(HttpStatus.BAD_REQUEST.value());
	        response.setMensaje(EnumMessages.ERROR.name() + ":" + e.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	   
//	   @ExceptionHandler(Exception.class)
//	   @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//	   public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
//		    ResponseGenerico<?> response = new ResponseGenerico<>();
//	        response.setCodigoRespuestaName(HttpStatus.BAD_REQUEST.name());
//	        response.setCodigoRespuestaValue(HttpStatus.BAD_REQUEST.value());
//	        response.setMensaje(EnumMessages.ERROR.name() + ":" + ex.getMessage());
//	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);	   
//	   }
	}
