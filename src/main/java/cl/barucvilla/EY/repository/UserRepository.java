package cl.barucvilla.EY.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.barucvilla.EY.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	User findById(UUID id);

	User deleteById(UUID id);

}