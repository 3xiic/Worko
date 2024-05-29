package co.edu.unbosque.workobacktraining.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.workobacktraining.model.ExerciseRoutine;
import co.edu.unbosque.workobacktraining.service.ExerciseRoutineService;

@RestController
@RequestMapping(name = "/exercise_routine")
@Transactional
@CrossOrigin(origins = {"*"})
public class ExerciseRoutineController {

	@Autowired
	public ExerciseRoutineService exerciseRoutineService;
	
	@PostMapping("/createExerciseRoutine")
	public void create(@RequestParam Long id_routine, @RequestParam Long id_exercise, @RequestParam Integer sets, @RequestParam Integer repetitions) {
		ExerciseRoutine aux = new ExerciseRoutine(id_routine, id_exercise, sets, repetitions);
		exerciseRoutineService.create(aux);
	}
	
	@GetMapping("/getAllExerciseRoutine")
	public ResponseEntity<List<ExerciseRoutine>> getAll() {
		List<ExerciseRoutine> aux = exerciseRoutineService.getAll();
		if (aux.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(aux);
	}
	
}
