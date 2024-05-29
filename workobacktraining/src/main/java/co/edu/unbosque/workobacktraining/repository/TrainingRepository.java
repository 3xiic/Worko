package co.edu.unbosque.workobacktraining.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.edu.unbosque.workobacktraining.model.Training;

public interface TrainingRepository extends MongoRepository<Training, String>{
	
}
