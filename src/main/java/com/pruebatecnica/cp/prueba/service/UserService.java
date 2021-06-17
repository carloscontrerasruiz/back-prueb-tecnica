package com.pruebatecnica.cp.prueba.service;

import javax.validation.Valid;

import com.pruebatecnica.cp.prueba.dto.GeneralResponse;
import com.pruebatecnica.cp.prueba.dto.UserDto;
import com.pruebatecnica.cp.prueba.dto.UserLogin;
import com.pruebatecnica.cp.prueba.dto.UserResponseJwt;
import com.pruebatecnica.cp.prueba.entity.UserEntity;

public interface UserService {
	public GeneralResponse<UserEntity> registerUser(UserDto user);
	public GeneralResponse<UserEntity> updateUser(UserDto user);
	public GeneralResponse<UserEntity> deleteUser(int id);
	public GeneralResponse<UserEntity> findUserByUsername(String username);
	public GeneralResponse<UserResponseJwt> loginUser(@Valid UserLogin request);
}
