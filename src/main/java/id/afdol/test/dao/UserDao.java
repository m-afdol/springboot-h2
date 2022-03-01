package id.afdol.test.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import id.afdol.test.model.db.Users;

@Repository
public interface UserDao extends CrudRepository<Users, Long> {
	String qU ="select * from users where social_security_number = :ssn and IS_DELETED = false ";
	
	@Query(value = qU, nativeQuery = true)
    List<Users> findBySsn(String ssn);
	
    String qUId ="select * from users where id = :id and IS_DELETED = false ";
	
	@Query(value = qUId, nativeQuery = true)
	Optional<Users> findById(Long id);
}
