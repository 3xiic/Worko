package co.edu.unbosque.workobacktraining.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.edu.unbosque.workobacktraining.model.ExerciseRoutine;

public interface ExerciseRoutineRepository extends MongoRepository<ExerciseRoutine, String>{

}
