package com.pruebatecnica.cp.prueba.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pruebatecnica.cp.prueba.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	public Optional<UserEntity> findByNombreUsuario(String username);
}
