package co.edu.unbosque.workobackexercises.controller;

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

import co.edu.unbosque.workobackexercises.model.Exercise;
import co.edu.unbosque.workobackexercises.service.ExerciseService;

@RestController
@RequestMapping(name = "/exercise")
@Transactional
@CrossOrigin(origins = {"*"})
public class ExerciseController {
	
	@Autowired
	public ExerciseService exerciseService;
	
	@PostMapping("/create")
	public void createExercise(@RequestParam String name, @RequestParam String difficulty, @RequestParam String equipment, @RequestParam String focus, @RequestParam String url ) {
		Exercise aux = new Exercise( name, difficulty, equipment, focus, url);
		exerciseService.create(aux);
	}
	@GetMapping("/getAll")
	public ResponseEntity<List<Exercise>> showAll(){
		List<Exercise> aux = exerciseService.getAll();
		if(aux.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(aux);
	}
}
