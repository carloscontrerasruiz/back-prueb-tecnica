package com.pruebatecnica.cp.prueba.service;

import com.pruebatecnica.cp.prueba.dto.GeneralResponse;
import com.pruebatecnica.cp.prueba.dto.UserDto;
import com.pruebatecnica.cp.prueba.entity.User;

public interface UserService {
	public GeneralResponse<User> registerUser(UserDto user);
	public GeneralResponse<User> updateUser(UserDto user);
	public GeneralResponse<User> deleteUser(int id);
	public GeneralResponse<User> findUserByUsername(String username);
}
