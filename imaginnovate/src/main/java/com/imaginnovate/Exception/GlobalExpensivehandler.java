package com.imaginnovate.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExpensivehandler {
	@ExceptionHandler(EmployeeNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(EmployeeNotFoundException exception,
			WebRequest webRequest) {

		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setMessage(exception.getMessage());
		errorDetails.setErrorcode("RESOURCE_NOT_FOUND");
		errorDetails.setDetails(webRequest.getDescription(false));
		errorDetails.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(errorDetails, HttpStatus.OK);

	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorDetails> handleGenericeException(EmployeeNotFoundException exception,
			WebRequest webRequest) {

		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setMessage(exception.getMessage());
		errorDetails.setErrorcode("INTERNAL_SERVER_ERROR");
		errorDetails.setDetails(webRequest.getDescription(false));
		errorDetails.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(errorDetails, HttpStatus.OK);

	}

}
