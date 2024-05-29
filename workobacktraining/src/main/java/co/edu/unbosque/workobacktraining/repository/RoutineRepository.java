package co.edu.unbosque.workobacktraining.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.edu.unbosque.workobacktraining.model.Routine;


public interface RoutineRepository extends MongoRepository<Routine, String>{
	
}
