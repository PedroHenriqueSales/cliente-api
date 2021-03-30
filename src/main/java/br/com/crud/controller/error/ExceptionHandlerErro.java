package br.com.crud.controller.error;

import br.com.crud.exception.ClienteParamsException;
import br.com.crud.exception.PersistenceException;
import br.com.crud.exception.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.List;
@ControllerAdvice
public class ExceptionHandlerErro extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ClienteParamsException.class})
	public ResponseEntity<Object> errorClienteParamResponse(ClienteParamsException ex, WebRequest request) {
		return handleExceptionInternal(ex, getError(ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ResourceNotFoundException.class})
	public ResponseEntity<Object> errorResourceNotFoundResponse(ResourceNotFoundException ex, WebRequest request) {
		return handleExceptionInternal(ex, getError(ex.getMessage()), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({PersistenceException.class})
	public ResponseEntity<Object> errorPersistenceResponse(PersistenceException ex, WebRequest request) {
		return handleExceptionInternal(ex, getError(ex.getMessage()), new HttpHeaders(), HttpStatus.NOT_MODIFIED, request);
	}

	private List<String> getError(String message) {
		return Collections.singletonList(message);
	}
}
