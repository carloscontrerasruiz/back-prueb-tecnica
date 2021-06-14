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
	@Pattern(regexp = "^\\S+\\w{8,10}\\S{1,}$", message="Formato de nombre de usuario incorrecto")
	private String nombreUsuario;
	
	@NotNull(message="Correo requerido")
	@NotBlank(message="Correo requerido")
	@Pattern(regexp = "([\\w\\.\\-]+)@([\\w\\-]+)((\\.(\\w){2,3})+)$", message="Formato de correo incorrecto")
	private String correo;
	
	private String password;
}