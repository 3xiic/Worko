package co.edu.unbosque.workobackmaster.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.edu.unbosque.workobackmaster.model.Exercise;
import java.util.List;


public interface ExerciseRepository extends MongoRepository<Exercise, String>{
	List<Exercise> findByIdexercise(Long idexercise);
}
