package co.edu.unbosque.workobacktraining.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.workobacktraining.model.ExerciseRoutine;
import co.edu.unbosque.workobacktraining.repository.ExerciseRoutineRepository;

@Service
public class ExerciseRoutineService {

	@Autowired
	public ExerciseRoutineRepository exerciseRoutineRepository;
	
	public void create(ExerciseRoutine er) {
		exerciseRoutineRepository.save(er);
	}
	
	public int delete(String id) {
		Optional<ExerciseRoutine> found = exerciseRoutineRepository.findById(id);
		if (found.isPresent()) {
			exerciseRoutineRepository.deleteById(id);
			return 1;
		}
		return 0;
	}
	
	public List<ExerciseRoutine> getAll() {
		return exerciseRoutineRepository.findAll();
	}
	
}
