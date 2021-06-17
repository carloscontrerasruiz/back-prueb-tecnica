package com.pruebatecnica.cp.prueba.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pruebatecnica.cp.prueba.dto.GeneralResponse;
import com.pruebatecnica.cp.prueba.dto.UserDto;
import com.pruebatecnica.cp.prueba.dto.UserLogin;
import com.pruebatecnica.cp.prueba.dto.UserResponseJwt;
import com.pruebatecnica.cp.prueba.entity.UserEntity;
import com.pruebatecnica.cp.prueba.service.UserService;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value="api/users/v1")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping(value = "/userCreate" ,consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<GeneralResponse<UserEntity>> registrarUsuario(@Valid @RequestBody UserDto request){
		GeneralResponse<UserEntity> response = service.registerUser(request);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/userUpdate" ,consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<GeneralResponse<UserEntity>> updateUser(@Valid @RequestBody UserDto request){
		GeneralResponse<UserEntity> response = service.updateUser(request);
		return new ResponseEntity<>(response, response.isError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/user/{id}" , produces = "application/json")
	@ResponseBody
	public ResponseEntity<GeneralResponse<UserEntity>> deleteUser(@PathVariable int id){
		GeneralResponse<UserEntity> response = service.deleteUser(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "/user/{name}" , produces = "application/json")
	@ResponseBody
	public ResponseEntity<GeneralResponse<UserEntity>> findUserByName(@PathVariable String name){
		GeneralResponse<UserEntity> response = service.findUserByUsername(name);
		return new ResponseEntity<>(response, response.isError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
	}
	
	@PostMapping(value = "/login" , produces = "application/json")
	@ResponseBody
	public ResponseEntity<GeneralResponse<UserResponseJwt>> loginUser(@Valid @RequestBody UserLogin request){
		GeneralResponse<UserResponseJwt> response = service.loginUser(request);
		return new ResponseEntity<>(response, response.isError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
	}
}
