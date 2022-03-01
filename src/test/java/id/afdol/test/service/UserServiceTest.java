package id.afdol.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.BDDMockito.given;

import java.sql.Date;
import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import id.afdol.test.TestApplication;
import id.afdol.test.model.db.Users;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplication.class) 
public class UserServiceTest {
	
	@MockBean
	private UserService userService;
	
	@Test
	public void testAddUser() throws Exception {
		Users user = getUser();
		ResponseEntity<Object> value = null;
		given(userService.add(user)).willReturn(value);
		value =  userService.add(user);
		assertEquals(value.getStatusCodeValue(), 201);
	}
	
	private Users getUser() {
		Users user = new Users();
		
		user.setName("test nama");
		user.setSocial_security_number("1");
		
		Calendar dobCal =  Calendar.getInstance();
		dobCal.set(1990,0,1);		
		user.setDate_of_birth(new Date(dobCal.getTimeInMillis()));
		
		return user;
	}

}
