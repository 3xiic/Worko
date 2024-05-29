package co.edu.unbosque.workobackexercises.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.workobackexercises.model.Exercise;

import java.util.List;


public interface ExerciseRepository extends CrudRepository<Exercise, Long> {
	List<Exercise> findByName(String name);
	void deleteByName(String name);
	
}
