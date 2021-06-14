package com.pruebatecnica.cp.prueba.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.pruebatecnica.cp.prueba.entity.User;
import com.pruebatecnica.cp.prueba.service.UserService;

@RestController
@RequestMapping(value="api/users/v1")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping(value = "/user" ,consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<GeneralResponse<User>> registrarUsuario(@Valid @RequestBody UserDto request){
		GeneralResponse<User> response = service.registerUser(request);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/user" ,consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<GeneralResponse<User>> updateUser(@Valid @RequestBody UserDto request){
		GeneralResponse<User> response = service.updateUser(request);
		return new ResponseEntity<>(response, response.isError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/user/{id}" , produces = "application/json")
	@ResponseBody
	public ResponseEntity<GeneralResponse<User>> updateUser(@PathVariable int id){
		GeneralResponse<User> response = service.deleteUser(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "/user/{name}" , produces = "application/json")
	@ResponseBody
	public ResponseEntity<GeneralResponse<User>> updateUser(@PathVariable String name){
		GeneralResponse<User> response = service.findUserByUsername(name);
		return new ResponseEntity<>(response, response.isError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
	}
}
