package co.edu.unbosque.workobackmaster.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.edu.unbosque.workobackmaster.model.User;
import java.util.List;

public interface UserRepository extends MongoRepository<User, String>{
	List<User> findByUsername(String username);
	List<User> findByIdusr(Long idusr);
}
