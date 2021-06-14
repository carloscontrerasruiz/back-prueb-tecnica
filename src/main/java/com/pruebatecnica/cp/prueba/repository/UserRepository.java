package com.pruebatecnica.cp.prueba.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pruebatecnica.cp.prueba.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public Optional<User> findByNombreUsuario(String username);
}
