package id.afdol.test.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import id.afdol.test.model.db.Users;


public interface UserService {
	ResponseEntity<Object> add(Users user);
    List<Users> findAll();
    ResponseEntity<Object> delete(Long id);

    ResponseEntity<Object> findById(Long id);

    ResponseEntity<Object> update(Users user);
    
}
