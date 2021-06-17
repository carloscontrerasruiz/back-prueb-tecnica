package com.pruebatecnica.cp.prueba.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseJwt {
	
	private String nombreUsuario;
	
	private String jwt;
}
