package co.edu.unbosque.workobacktraining.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import co.edu.unbosque.workobacktraining.model.Training;
import co.edu.unbosque.workobacktraining.service.SequenceService;
import co.edu.unbosque.workobacktraining.service.TrainingService;

@RestController
@RequestMapping(name = "/trainings")
@Transactional
@CrossOrigin(origins = {"*"})
public class TrainingController {

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
	
	@Autowired
	public TrainingService trainingService;
	@Autowired
	public SequenceService sequenceService;
	
	@PostMapping("/createTraining")
	public void create(@RequestParam Long id_user, @RequestParam Long id_routine, @RequestParam String date, @RequestParam Integer duration) {
		Date dateaux = null;
		try {
			dateaux = formatter.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Training aux = new Training(sequenceService.getNextValue("seqtraining"), id_user, id_routine, dateaux, duration);
		trainingService.create(aux);
	}
	
	@GetMapping("/getAllTrainings")
	public ResponseEntity<List<Training>> getAll() {
		List<Training> aux = trainingService.getAll();
		if (aux.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(aux);
	}
	
}
