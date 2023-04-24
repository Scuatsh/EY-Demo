package cl.barucvilla.EY.service;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import cl.barucvilla.EY.entity.User;

public interface IUserService {
	ResponseEntity<Object> getUser(@PathVariable UUID id);

	ResponseEntity<Object> getAllUser();

	ResponseEntity<Object> createUser(@RequestBody User newUser);

	ResponseEntity<Object> updateUser(@PathVariable UUID id, @RequestBody User updatedUser);

	ResponseEntity<Object> deleteUser(UUID id);

}
