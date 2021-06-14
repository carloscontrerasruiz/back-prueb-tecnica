package com.pruebatecnica.cp.prueba.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.pruebatecnica.cp.prueba.dto.GeneralResponse;
import com.pruebatecnica.cp.prueba.dto.UserDto;
import com.pruebatecnica.cp.prueba.dto.UserLogin;
import com.pruebatecnica.cp.prueba.entity.User;
import com.pruebatecnica.cp.prueba.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository repository;

	@Override
	public GeneralResponse<User> registerUser(UserDto user) {
		try {
			User response = repository.save(new User(user.getNombreUsuario(),
						user.getCorreo(),
						user.getPassword() 
						)
					);
			return new GeneralResponse<User>(false,"",response);
		}catch(DataIntegrityViolationException e) {
			return new GeneralResponse<User>(true,"El nombre de usuario o email ya existen. Intenta hacer login",null);
		}
	}

	@Override
	public GeneralResponse<User> updateUser(UserDto user) {
		if(user.getId() == 0) {
			return new GeneralResponse<User>(true,"Id no proporcionado",null);
		}
		Optional<User> existingUser = repository.findById(user.getId());
		if(existingUser.isPresent()) {
			User updateUser = existingUser.get();
			updateUser.setNombreUsuario(user.getNombreUsuario());
			updateUser.setCorreo(user.getCorreo());
			updateUser.setPassword(user.getPassword());
			return new GeneralResponse<User>(false,"",repository.save(updateUser));
		}else {
			return new GeneralResponse<User>(true,"Usuario no existe",null);
		}
	}

	@Override
	public GeneralResponse<User> deleteUser(int id) {
		repository.deleteById(id);
		return new GeneralResponse<User>(false,"",null);
	}

	@Override
	public GeneralResponse<User> findUserByUsername(String username) {
		Optional<User> response = repository.findByNombreUsuario(username);
		if(response.isPresent()) {
			return new GeneralResponse<User>(false,"",
					response.get()
					);
		}else {
			return new GeneralResponse<User>(true,"Usuario no existe",
					null
					);
		}
		
	}

	@Override
	public GeneralResponse<User> loginUser(UserLogin request) {
		Optional<User> response = repository.findByNombreUsuario(request.getNombreUsuario());
		if(response.isPresent()) {
			User user = response.get();
			if(user.getPassword().equals(request.getPassword())) {
				return new GeneralResponse<User>(false,"",
						user
						);
			}
			
		}
		return new GeneralResponse<User>(true,"Usuario o password incorrectos",
				null
				);
	}

}
