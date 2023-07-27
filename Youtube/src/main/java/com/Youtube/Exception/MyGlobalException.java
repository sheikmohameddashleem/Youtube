package com.Youtube.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class MyGlobalException {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> generalExceptionHandler(Exception ex, WebRequest request) {
		log.error("An unexpected error occurred", ex);
		return new ResponseEntity<>(
				new MyErrorDetails(ex.getMessage(), request.getDescription(false), LocalDateTime.now()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(YoutubeException.class)
	public ResponseEntity<MyErrorDetails> youtubeExHandler(YoutubeException ex, WebRequest we) {
		log.error("An unexpected error occurred", ex);
		return new ResponseEntity<>(new MyErrorDetails(ex.getMessage(), we.getDescription(false), LocalDateTime.now()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> noHandlerExceptionHandler(NoHandlerFoundException ex, WebRequest request) {
		log.warn("No handler found for the requested endpoint", ex);
		return new ResponseEntity<>(
				new MyErrorDetails(ex.getMessage(), request.getDescription(false), LocalDateTime.now()),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> notValidExceptionHandler(MethodArgumentNotValidException ex,
			WebRequest request) {
		log.warn("Invalid request arguments", ex);
		return new ResponseEntity<>(
				new MyErrorDetails(ex.getMessage(), request.getDescription(false), LocalDateTime.now()),
				HttpStatus.BAD_REQUEST);
	}

	
}
