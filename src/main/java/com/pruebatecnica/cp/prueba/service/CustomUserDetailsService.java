package com.pruebatecnica.cp.prueba.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pruebatecnica.cp.prueba.entity.UserEntity;
import com.pruebatecnica.cp.prueba.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> response = repository.findByNombreUsuario(username);
		if(response.isPresent()) {
			UserEntity user = response.get();
			return new User(user.getNombreUsuario(),user.getPassword(),new ArrayList<>());
		}else {
			throw new UsernameNotFoundException("User not found");
		}
	}

}
