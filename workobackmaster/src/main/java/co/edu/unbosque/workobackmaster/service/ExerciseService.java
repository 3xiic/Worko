package co.edu.unbosque.workobackmaster.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.workobackmaster.model.Exercise;
import co.edu.unbosque.workobackmaster.repository.ExerciseRepository;

@Service
public class ExerciseService {

	@Autowired
	public ExerciseRepository exerciseRepository;
	
	public void create(Exercise e) {
		exerciseRepository.save(e);
	}
	
	public int delete(String id) {
		Optional<Exercise> found = exerciseRepository.findById(id);
				
		if(found.isPresent()) {
			exerciseRepository.deleteById(id);
			return 1;
		}
		return 0;
	}
	
	public List<Exercise> getAll(){
		return exerciseRepository.findAll();
	}
	
	public List<Exercise> getByIdExercise(Long idexercise) {
		return exerciseRepository.findByIdexercise(idexercise);
	}
	
	public List<Exercise> getByRoutine(Long[] ids) {
		List<Exercise> aux = new ArrayList<>();
		for (int i = 0; i < ids.length; i++) {
			if (!getByIdExercise(ids[i]).isEmpty()) {				
				aux.add(getByIdExercise(ids[i]).get(0));
			}
		}
		return aux;
	}

}
