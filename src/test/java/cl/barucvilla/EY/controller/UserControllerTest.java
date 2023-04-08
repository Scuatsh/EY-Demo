package cl.barucvilla.EY.controller;

import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.barucvilla.EY.entity.User;
import cl.barucvilla.EY.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	@MockBean
	UserRepository userRepository;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void createUser() throws Exception {
		User user = new User();
		user.setId(UUID.randomUUID());
		user.setName("Baruc");
		user.setEmail("barucvilla@gmail.com");
		user.setPassword("HolaMundo22");

		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

		mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(user))).andExpect(status().isOk());

		Mockito.verify(userRepository, times(1)).save(Mockito.any(User.class));

	}

}
