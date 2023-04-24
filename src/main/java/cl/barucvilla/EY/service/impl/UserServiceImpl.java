package cl.barucvilla.EY.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cl.barucvilla.EY.entity.User;
import cl.barucvilla.EY.repository.UserRepository;
import cl.barucvilla.EY.responses.ErrorMessage;
import cl.barucvilla.EY.responses.createUserResponse;
import cl.barucvilla.EY.service.IUserService;
import cl.barucvilla.EY.util.Toolbox;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private Toolbox toolbox;

	@Override
	public ResponseEntity<Object> getUser(UUID id) {
		try {
			User existingUser = userRepository.findById(id);
			if (existingUser.getId() == null) {
				ErrorMessage error = new ErrorMessage("Usuario no existe");
				return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
			}
			return ResponseEntity.ok(existingUser);
		} catch (Exception e) {
			System.out.println(e);
			ErrorMessage error = new ErrorMessage("Error al obtener el usuario");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
		}
	}

	@Override
	public ResponseEntity<Object> createUser(User newUser) {
		try {

			if (!toolbox.validateEmail(newUser.getEmail())) {
				ErrorMessage error = new ErrorMessage("Email invalido");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
			}
			if (!toolbox.validatePassword(newUser.getPassword())) {
				ErrorMessage error = new ErrorMessage("La password no cumple los requisitos");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
			}

			User searchUser = userRepository.findByEmail(newUser.getEmail());
			if (searchUser != null) {
				ErrorMessage error = new ErrorMessage("Usuario ya existe");
				return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
			}

			newUser.init();
			User _user = userRepository.save(newUser);
			createUserResponse userReturn = new createUserResponse(_user);
			System.out.println(userReturn.getId());
			return ResponseEntity.ok(userReturn);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(e.getMessage()));
		}
	}

	@Override
	public ResponseEntity<Object> updateUser(UUID id, User updatedUser) {
		try {
			User existingUser = userRepository.findById(id);

			System.out.println(existingUser);
			if (existingUser == null) {
				ErrorMessage error = new ErrorMessage("Usuario no existe: " + id);
				return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
			}
			if (!toolbox.validatePassword(updatedUser.getPassword())) {
				ErrorMessage error = new ErrorMessage("La password no cumple los requisitos");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
			}

			if (updatedUser.getName() != null)
				existingUser.setName(updatedUser.getName());
			if (updatedUser.getEmail() != null)
				existingUser.setEmail(updatedUser.getEmail());
			if (updatedUser.getPassword() != null)
				existingUser.setPassword(updatedUser.getPassword());

			existingUser.setModified(new Date());

			User savedUser = userRepository.save(existingUser);
			createUserResponse userReturn = new createUserResponse(savedUser);
			return new ResponseEntity<>(userReturn, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			ErrorMessage error = new ErrorMessage("Error al obtener el usuario");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
		}
	}

	@Override
	public ResponseEntity<Object> getAllUser() {
		try {
			List<User> existingUsers = userRepository.findAll();
			System.out.println(existingUsers.size());
			return ResponseEntity.ok(existingUsers);
		} catch (Exception e) {
			System.out.println(e);
			ErrorMessage error = new ErrorMessage("Error al obtener el usuario");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
		}
	}

	@Override
	public ResponseEntity<Object> deleteUser(UUID id) {
		try {
			User existingUser = userRepository.deleteById(id);
			return ResponseEntity.ok(existingUser);
		} catch (Exception e) {
			System.out.println(e);
			ErrorMessage error = new ErrorMessage("Error al obtener el usuario");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
		}
	}

}
