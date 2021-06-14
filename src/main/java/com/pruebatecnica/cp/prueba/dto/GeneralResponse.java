package com.pruebatecnica.cp.prueba.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeneralResponse <T>{
	
	private boolean error;
	private String errorMessage;
	private T body;
	
}
