package id.afdol.test.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import id.afdol.test.dao.UserDao;
import id.afdol.test.model.db.Users;
import id.afdol.test.model.response.ErrResponse;
import id.afdol.test.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;

	private ErrResponse validateName(Users user) {
		ErrResponse errResp = null;
		if(user.getName() == null) {
			errResp = new ErrResponse();
			errResp.setCode("30001");
			errResp.setStatus(String.valueOf(HttpStatus.BAD_REQUEST.name()));
			errResp.setMessage("Invalid value for field " + "name" + ", rejected value: " + "null");
			
		}else if (user.getName().trim().length() < 2 || user.getName().trim().length() > 50) {
			errResp = new ErrResponse();
			errResp.setCode("30001");
			errResp.setStatus(String.valueOf(HttpStatus.BAD_REQUEST.name()));
			errResp.setMessage("Invalid value for field " + "name" + ", rejected value: " + user.getName());
			
		}
		
		return errResp;
	}
	
	private ErrResponse validateSsn(Users user) {
		ErrResponse errResp = null;
		if(user.getSocial_security_number() == null) {
			errResp = new ErrResponse();
			errResp.setCode("30001");
			errResp.setStatus(String.valueOf(HttpStatus.BAD_REQUEST.name()));
			errResp.setMessage("Invalid value for field " + "social_security_number" + ", rejected value: " + "null");
			
		}else if (user.getSocial_security_number().trim().length() < 1 || user.getSocial_security_number().trim().length() > 5) {
			errResp = new ErrResponse();
			errResp.setCode("30001");
			errResp.setStatus(String.valueOf(HttpStatus.BAD_REQUEST.name()));
			errResp.setMessage("Invalid value for field " + "social_security_number" + ", rejected value: " + user.getName());
			
		}
		
		return errResp;
	}
	
	private ErrResponse validateDob(Users user) {
		ErrResponse errResp = null;
		if(user.getDate_of_birth() == null) {
			errResp = new ErrResponse();
			errResp.setCode("30001");
			errResp.setStatus(String.valueOf(HttpStatus.BAD_REQUEST.name()));
			errResp.setMessage("Invalid value for field " + "date_of_birth" + ", rejected value: " + "null");
			
		}
		
		return errResp;
	}
	
	@Override
	public ResponseEntity<Object> add(Users user) {
		ErrResponse errResp = null;
		errResp = validateName(user);
		if  (errResp != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errResp);
		}
		
		errResp = validateSsn(user);
		if  (errResp != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errResp);
		}
		
		errResp = validateDob(user);
		if  (errResp != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errResp);
		}
		
		String ssn = "00000" + user.getSocial_security_number();
		ssn = ssn.substring(ssn.length()-5);
		
		user.setSocial_security_number(ssn);
		
		List<Users> checkSsns = userDao.findBySsn(user.getSocial_security_number());
		
		if (checkSsns.size() > 0) {
			errResp = new ErrResponse();
			errResp.setCode("30002");
			errResp.setStatus(String.valueOf(HttpStatus.CONFLICT.name()));
			errResp.setMessage("Record with SSN " + user.getSocial_security_number() +" already exists in the system");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(errResp);
		}
		
		
		user.setIs_deleted(false);
		user.setDate_created(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		user.setDate_updated(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		user.setCreated_by("SPRING_BOOT_TEST");
		user.setUpdated_by("SPRING_BOOT_TEST");
		
		user = userDao.save(user);
		return ResponseEntity.status(201).body(user);
	}

	@Override
	public List<Users> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Object> delete(Long id) {
		Optional<Users> checkId = userDao.findById(id);
		ErrResponse errResp = null;
		if (!checkId.isPresent()) {
			errResp = new ErrResponse();
			errResp.setCode("30000");
			errResp.setStatus(String.valueOf(HttpStatus.NOT_FOUND.name()));
			errResp.setMessage("No such resource with id " + id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errResp);
		}
		
		Users user2del = checkId.get();
		
		user2del.setIs_deleted(true);
		user2del.setDate_updated(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		user2del.setUpdated_by("SPRING_BOOT_TEST");
		
		userDao.save(user2del);
		
		return ResponseEntity.status(204).build();

	}

	@Override
	public ResponseEntity<Object> findById(Long id) {
		Optional<Users> checkId = userDao.findById(id);
		ErrResponse errResp = null;
		if (!checkId.isPresent()) {
			errResp = new ErrResponse();
			errResp.setCode("30000");
			errResp.setStatus(String.valueOf(HttpStatus.NOT_FOUND.name()));
			errResp.setMessage("No such resource with id " + id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errResp);
		}
		
		return ResponseEntity.status(201).body(checkId.get());
	}

	@Override
	public ResponseEntity<Object> update(Users user) {
		ErrResponse errResp = null;
		errResp = validateName(user);
		if  (errResp != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errResp);
		}
		
		errResp = validateDob(user);
		if  (errResp != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errResp);
		}
		
		Optional<Users> checkId = userDao.findById(user.getId());
		
		if (!checkId.isPresent()) {
			errResp = new ErrResponse();
			errResp.setCode("30000");
			errResp.setStatus(String.valueOf(HttpStatus.NOT_FOUND.name()));
			errResp.setMessage("No such resource with id " + user.getId());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errResp);
		}
		
		Users user2upd = checkId.get();
		
		user2upd.setName(user.getName());
		user2upd.setDate_of_birth(user.getDate_of_birth());
		user2upd.setDate_updated(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		user2upd.setUpdated_by("SPRING_BOOT_TEST");
		
		user2upd = userDao.save(user2upd);
		
		return ResponseEntity.status(201).body(user2upd);
	}

}
