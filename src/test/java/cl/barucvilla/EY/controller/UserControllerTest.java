package cl.barucvilla.EY.controller;

import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.barucvilla.EY.entity.User;
import cl.barucvilla.EY.service.IUserService;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {

	@MockBean
	IUserService userService;

	@Autowired
	private MockMvc mockMvc;

	private User user;
	private ResponseEntity<Object> expectedResponse;

	@BeforeEach
	public void setUp() {
		user = new User();
		user.setId(UUID.randomUUID());
		user.setName("Baruc");
		user.setEmail("barucvilla@gmail.com");
		user.setPassword("HolaMundo22");
		expectedResponse = ResponseEntity.ok(user);
	}

	@Test
	@Order(1)
	public void createUser() throws Exception {
		Mockito.when(userService.createUser(user)).thenReturn(expectedResponse);
		mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(user))).andExpect(status().isOk());
		Mockito.verify(userService, times(1)).createUser(Mockito.any(User.class));
	}

	@Test
	@Order(2)
	public void allUsers() throws Exception {
		Mockito.when(userService.getAllUser()).thenReturn(expectedResponse);
		mockMvc.perform(get("/allUser")).andExpect(status().isOk());
		Mockito.verify(userService, times(1)).getAllUser();
	}

	@Test
	@Order(3)
	public void updateUser() throws Exception {
		Mockito.when(userService.updateUser(user.getId(), user)).thenReturn(expectedResponse);
		mockMvc.perform(put("/user/{id}", user.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().setSerializationInclusion(Include.NON_NULL).writeValueAsString(user)))
				.andExpect(status().isOk());
	}
}
