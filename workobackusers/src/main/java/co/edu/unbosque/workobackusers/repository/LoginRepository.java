package co.edu.unbosque.workobackusers.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.edu.unbosque.workobackusers.model.Login;

public interface LoginRepository extends MongoRepository<Login, String>{

}
