package com.pruebatecnica.cp.prueba.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.pruebatecnica.cp.prueba.dto.GeneralResponse;
import com.pruebatecnica.cp.prueba.dto.UserDto;
import com.pruebatecnica.cp.prueba.dto.UserLogin;
import com.pruebatecnica.cp.prueba.dto.UserResponseJwt;
import com.pruebatecnica.cp.prueba.entity.UserEntity;
import com.pruebatecnica.cp.prueba.repository.UserRepository;
import com.pruebatecnica.cp.prueba.utils.JwtUtil;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public GeneralResponse<UserEntity> registerUser(UserDto user) {
		try {
			UserEntity response = repository.save(new UserEntity(user.getNombreUsuario(),
						user.getCorreo(),
						user.getPassword() 
						)
					);
			return new GeneralResponse<UserEntity>(false,"",response);
		}catch(DataIntegrityViolationException e) {
			return new GeneralResponse<UserEntity>(true,"El nombre de usuario o email ya existen. Intenta hacer login",null);
		}
	}

	@Override
	public GeneralResponse<UserEntity> updateUser(UserDto user) {
		if(user.getNombreUsuario().equals("")) {
			return new GeneralResponse<UserEntity>(true,"Username no proporcionado",null);
		}
		Optional<UserEntity> existingUser = repository.findByNombreUsuario(user.getNombreUsuario());
		if(existingUser.isPresent()) {
			UserEntity updateUser = existingUser.get();
			updateUser.setNombreUsuario(user.getNombreUsuario());
			updateUser.setCorreo(user.getCorreo());
			updateUser.setPassword(user.getPassword());
			return new GeneralResponse<UserEntity>(false,"",repository.save(updateUser));
		}else {
			return new GeneralResponse<UserEntity>(true,"Usuario no existe",null);
		}
	}

	@Override
	public GeneralResponse<UserEntity> deleteUser(int id) {
		repository.deleteById(id);
		return new GeneralResponse<UserEntity>(false,"",null);
	}

	@Override
	public GeneralResponse<UserEntity> findUserByUsername(String username) {
		Optional<UserEntity> response = repository.findByNombreUsuario(username);
		if(response.isPresent()) {
			return new GeneralResponse<UserEntity>(false,"",
					response.get()
					);
		}else {
			return new GeneralResponse<UserEntity>(true,"Usuario no existe",
					null
					);
		}
		
	}

	@Override
	public GeneralResponse<UserResponseJwt> loginUser(UserLogin request) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getNombreUsuario(), 
							request.getPassword()));
			
		} catch (BadCredentialsException e) {
			return new GeneralResponse<UserResponseJwt>(true,"Usuario o password incorrectos",
					null
					);
		}
		
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(request.getNombreUsuario());
		
		final String jwt = jwtUtil.generateToken(userDetails);
		
		return new GeneralResponse<UserResponseJwt>(false,"",
				new UserResponseJwt(request.getNombreUsuario(),jwt)
				);
	}

}
