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
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.transaction.TransactionSystemException;

@RestControllerAdvice
public class CustomErrorHandler {
	
	   @ResponseStatus(HttpStatus.BAD_REQUEST)  
	    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	    public ResponseEntity<?> handleContraintViolationException( SQLIntegrityConstraintViolationException e, WebRequest request) {
	        ResponseGenerico<?> response = new ResponseGenerico<>();
	        response.setCodigoRespuestaName(HttpStatus.BAD_REQUEST.name());
	        response.setCodigoRespuestaValue(HttpStatus.BAD_REQUEST.value());
	        response.setMensaje(EnumMessages.ERROR.getValor() + "Hay un problema con los dtos ingresados debe revisarlos o comuniquese con el administrador del sistema" );
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	   
	   

	   
//	    for (ConstraintViolation cv : e.getConstraintViolations()) {
//	        report.addConstraintViolation(new RestConstraintViolation(
//	            cv.getPropertyPath().toString(),
//	            cv.getMessage(),
//	            cv.getInvalidValue() == null ? "null" : cv.getInvalidValue().toString()));
//	      }
//	   
	   
	   @ResponseStatus(HttpStatus.BAD_REQUEST)  
	    @ExceptionHandler(ConstraintViolationException.class)
	    public ResponseEntity<?> ConstraintViolationException( ConstraintViolationException e, WebRequest request) {
	        StringBuilder violaciones= new StringBuilder(); 
		    for (ConstraintViolation cv : e.getConstraintViolations()) {
		    	violaciones.append(cv.getPropertyPath().toString() +"\n");
		    	violaciones.append(cv.getMessage()+"\n");
		    	violaciones.append(cv.getInvalidValue() == null ? "null" : cv.getInvalidValue().toString());
		      }
		   
		   ResponseGenerico<?> response = new ResponseGenerico<>();
	        response.setCodigoRespuestaName(HttpStatus.BAD_REQUEST.name());
	        response.setCodigoRespuestaValue(HttpStatus.BAD_REQUEST.value());
	        response.setMensaje(EnumMessages.ERROR.getValor() + " Los datos son incorrectos o no tienen el formato adecuado ");
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	   
	   @ResponseStatus(HttpStatus.BAD_REQUEST)  
	    @ExceptionHandler(CedulaEcException.class)
	    public ResponseEntity<?> handleCedulaEcException( CedulaEcException e, WebRequest request) {
	        ResponseGenerico<?> response = new ResponseGenerico<>();
	        response.setCodigoRespuestaName(HttpStatus.BAD_REQUEST.name());
	        response.setCodigoRespuestaValue(HttpStatus.BAD_REQUEST.value());
	        response.setMensaje(EnumMessages.ERROR.getValor() + " La Cédula no es correcta");
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	   
	   @ResponseStatus(HttpStatus.BAD_REQUEST)  
	    @ExceptionHandler(DataIntegrityViolationException.class)
	    public ResponseEntity<?> DataIntegrityViolationException( DataIntegrityViolationException e, WebRequest request) {
	        ResponseGenerico<?> response = new ResponseGenerico<>();
	        response.setCodigoRespuestaName(HttpStatus.BAD_REQUEST.name());
	        response.setCodigoRespuestaValue(HttpStatus.BAD_REQUEST.value());
	       // response.setMensaje(EnumMessages.ERROR.getValor() + " Registro Relacionado : " + e.getMessage());
	        response.setMensaje(EnumMessages.ERROR.getValor() + " Registro relacionado ");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	   
//	   @ExceptionHandler(Exception.class)
//	   @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//	   public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
//		    ResponseGenerico<?> response = new ResponseGenerico<>();
//	        response.setCodigoRespuestaName(HttpStatus.BAD_REQUEST.name());
//	        response.setCodigoRespuestaValue(HttpStatus.BAD_REQUEST.value());
//	        response.setMensaje(EnumMessages.ERROR.getValor() + ":" + ex.getMessage());
//	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);	   
//	   }
	}
