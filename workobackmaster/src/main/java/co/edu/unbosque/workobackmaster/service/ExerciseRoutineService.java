package co.edu.unbosque.workobackmaster.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.workobackmaster.model.ExerciseRoutine;
import co.edu.unbosque.workobackmaster.repository.ExerciseRoutineRepository;


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
	
	public List<ExerciseRoutine> getByIdRoutine(Long idroutine) {
		return exerciseRoutineRepository.findByIdroutine(idroutine);
	}
	
}
