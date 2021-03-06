package com.pruebatecnica.cp.prueba.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UserDto {
	
	private int id;
	
	@NotNull(message="Nombre de usuario requerido")
	@NotBlank(message="Nombre de usuario requerido")
	@Pattern(regexp = "^[a-zA-Z0-9]{5,10}$", message="Formato de username incorrecto")
	private String nombreUsuario;
	
	@NotNull(message="Correo requerido")
	@NotBlank(message="Correo requerido")
	@Pattern(regexp = "([\\w\\.\\-]+)@([\\w\\-]+)((\\.(\\w){2,3})+)$", message="Formato de correo incorrecto")
	private String correo;
	
	@NotNull(message="Password requerido")
	@NotBlank(message="Password requerido")
	@Pattern(regexp = "^[a-zA-Z0-9!$&?¡¿]{8,15}$", message="Formato de password incorrecto")
	private String password;
}