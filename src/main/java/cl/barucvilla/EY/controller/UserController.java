package cl.barucvilla.EY.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.barucvilla.EY.entity.User;
import cl.barucvilla.EY.service.IUserService;

@RestController
public class UserController {

	@Autowired
	IUserService userService;

	@GetMapping("/allUser")
	public ResponseEntity<Object> getAllUser() {
		return userService.getAllUser();
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<Object> getUser(@PathVariable UUID id) {
		return userService.getUser(id);
	}

	@PostMapping("/user")
	public ResponseEntity<Object> createUser(@RequestBody User newUser) {
		return userService.createUser(newUser);
	}

	@PutMapping("user/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable UUID id, @RequestBody User updatedUser) {
		return userService.updateUser(id, updatedUser);
	}

	@DeleteMapping("user/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable UUID id) {
		return userService.deleteUser(id);
	}

}