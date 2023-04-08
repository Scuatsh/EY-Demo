package cl.barucvilla.EY.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.barucvilla.EY.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

}