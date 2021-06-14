package com.pruebatecnica.cp.prueba.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name="USER")
public class User {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "username" ,unique=true,nullable = false)
	private String nombreUsuario;
	
	@Column(name = "email" ,unique=true,nullable = false)
	private String correo;
	
	private String password;

	public User(String nombreUsuario, String correo, String password) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.correo = correo;
		this.password = password;
	}
	
	
}

