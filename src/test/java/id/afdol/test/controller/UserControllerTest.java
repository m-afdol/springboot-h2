package id.afdol.test.controller;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.Calendar;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.afdol.test.TestApplication;
import id.afdol.test.model.db.Users;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplication.class)
@AutoConfigureMockMvc
public class UserControllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserController userController;

	@Test
	private void testAddUser() throws Exception {
		Users user = getUser();
		doNothing().when(userController.addUser(user));
		mvc.perform(post("/v1/users").content(asJson(user)).contentType(APPLICATION_JSON)).andExpect(status().is(201))
				.andReturn();
	}

	private static String asJson(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Users getUser() {
		Users user = new Users();

		user.setName("test nama");
		user.setSocial_security_number("1");

		Calendar dobCal = Calendar.getInstance();
		dobCal.set(1990, 0, 1);
		user.setDate_of_birth(new Date(dobCal.getTimeInMillis()));

		return user;
	}

}
