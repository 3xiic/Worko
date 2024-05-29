package co.edu.unbosque.workobackmaster.controller;

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

import co.edu.unbosque.workobackmaster.model.ExerciseRoutine;
import co.edu.unbosque.workobackmaster.service.ExerciseRoutineService;


@RestController
@RequestMapping(name = "/exercise_routine")
@Transactional
@CrossOrigin(origins = {"*"})
public class ExerciseRoutineController {

	@Autowired
	public ExerciseRoutineService exerciseRoutineService;
	
	@PostMapping("/createExerciseRoutine")
	public void create(@RequestParam Long idroutine, @RequestParam Long idexercise, @RequestParam Integer sets, @RequestParam Integer repetitions) {
		ExerciseRoutine aux = new ExerciseRoutine(idroutine, idexercise, sets, repetitions);
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
	
	@GetMapping("/getByIdRoutine")
	public ResponseEntity<List<ExerciseRoutine>> getByIdRoutine(@RequestParam Long idroutine) {
		List<ExerciseRoutine> aux = exerciseRoutineService.getByIdRoutine(idroutine);
		if (aux.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(aux);
	}
	
	
}
