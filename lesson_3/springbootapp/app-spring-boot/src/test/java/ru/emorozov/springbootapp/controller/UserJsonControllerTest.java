package ru.emorozov.springbootapp.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.emorozov.springbootapp.entity.User;
import ru.emorozov.springbootapp.repository.UserRepository;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(SpringRunner.class)
@WebMvcTest(UserJsonController.class)
public class UserJsonControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserRepository userRepository;

	@Test
	public void test() throws Exception {
		User testUser = new User();
		testUser.setFirstName("user1");
		testUser.setEmail("111@111.111");
		given(this.userRepository.findByFirstName("user1")).willReturn(testUser);
		this.mvc.perform(
			get("/api/user/info?id={id}", "user1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}