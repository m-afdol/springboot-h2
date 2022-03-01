package id.afdol.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.afdol.test.model.db.Users;
import id.afdol.test.service.UserService;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/users")
public class UserController {
	
	@Autowired
    private UserService userService;
	
	@PostMapping
	public ResponseEntity<Object> addUser(@RequestBody Users user){
		return userService.add(user);
		
//		return ResponseEntity.ok().body(savedUser) ;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getUser(@PathVariable long id){
		return userService.findById(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delUser(@PathVariable long id){
		return userService.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable long id, @RequestBody Users user){
		user.setId(id);
		
		return userService.update(user);
		
	}
}
