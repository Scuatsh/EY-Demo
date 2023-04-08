package cl.barucvilla.EY.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.barucvilla.EY.entity.User;
import cl.barucvilla.EY.repository.UserRepository;
import cl.barucvilla.EY.responses.ErrorMessage;
import cl.barucvilla.EY.responses.createUserResponse;
import cl.barucvilla.EY.util.Toolbox;

@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;
	private Toolbox toolbox = new Toolbox();

	@GetMapping("/users")
	public String getUsers() {
		return "hello word";
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUser(@PathVariable Long id) {
		Optional<User> existingUser = userRepository.findById(id);
		if (!existingUser.isPresent()) {
			ErrorMessage error = new ErrorMessage("Usuario no existe");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
		}
		return ResponseEntity.ok(existingUser.get());
	}

	@PostMapping("/user")
	public ResponseEntity<?> createUser(@RequestBody User newUser) {
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
			return ResponseEntity.ok(userReturn);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("user/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
		Optional<User> existingUser = userRepository.findById(id);
		if (!existingUser.isPresent()) {
			ErrorMessage error = new ErrorMessage("Usuario no existe");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
		}
		if (updatedUser.getName() != null)
			existingUser.get().setName(updatedUser.getName());
		if (updatedUser.getEmail() != null)
			existingUser.get().setEmail(updatedUser.getEmail());
		if (updatedUser.getPassword() != null) {
			if (toolbox.validatePassword(updatedUser.getPassword())) {
				ErrorMessage error = new ErrorMessage("La password no cumple los requisitos");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
			}
			existingUser.get().setPassword(updatedUser.getPassword());
		}
		existingUser.get().setModified(new Date());

		User savedUser = userRepository.save(existingUser.get());
		createUserResponse userReturn = new createUserResponse(savedUser);
		return new ResponseEntity<>(userReturn, HttpStatus.OK);
	}

}