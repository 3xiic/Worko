package co.edu.unbosque.workobackexercises.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.workobackexercises.model.Exercise;
import co.edu.unbosque.workobackexercises.repository.ExerciseRepository;

@Service
public class ExerciseService {

	@Autowired
	public ExerciseRepository exerciseRepository;
	
	public void create(Exercise e) {
		exerciseRepository.save(e);
	}
	
	public int delete(Long id) {
		Optional<Exercise> found = exerciseRepository.findById(id);
				
		if(found.isPresent()) {
			exerciseRepository.deleteById(id);
			return 1;
		}
		return 0;
	}
	
	public List<Exercise> getAll(){
		return (List<Exercise>) exerciseRepository.findAll();
	}
}
