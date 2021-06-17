package com.pruebatecnica.cp.prueba.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pruebatecnica.cp.prueba.dto.GeneralResponse;
import com.pruebatecnica.cp.prueba.entity.UserEntity;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	//handle Method argument validation errors
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<GeneralResponse<UserEntity>> handleGlobalException(MethodArgumentNotValidException exception){
			GeneralResponse<UserEntity> response = new GeneralResponse<>(true,
																exception.getBindingResult().getFieldError() != null ? 
																		exception.getBindingResult().getFieldError().getDefaultMessage() : 
																		"Error de validacion",
																null);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		//handle Global exception
		@ExceptionHandler(Exception.class)
		public ResponseEntity<GeneralResponse<UserEntity>> handleGlobalException(Exception exception){
			GeneralResponse<UserEntity> response = new GeneralResponse<>(true,
																	exception.getMessage(),
																	null);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
}
