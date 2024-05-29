package co.edu.unbosque.workobackmaster.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.edu.unbosque.workobackmaster.model.Routine;


public interface RoutineRepository extends MongoRepository<Routine, String>{
	
}
