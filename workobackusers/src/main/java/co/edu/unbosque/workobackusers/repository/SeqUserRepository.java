package co.edu.unbosque.workobackusers.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.edu.unbosque.workobackusers.model.SeqUser;

public interface SeqUserRepository extends MongoRepository<SeqUser, String>{
	
}
