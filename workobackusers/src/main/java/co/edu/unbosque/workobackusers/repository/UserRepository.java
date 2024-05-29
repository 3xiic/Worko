package co.edu.unbosque.workobackusers.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.edu.unbosque.workobackusers.model.User;

public interface UserRepository extends MongoRepository<User, String>{

}
