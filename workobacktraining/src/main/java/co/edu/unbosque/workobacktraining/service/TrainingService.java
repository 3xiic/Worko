package co.edu.unbosque.workobacktraining.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.workobacktraining.model.Training;
import co.edu.unbosque.workobacktraining.repository.TrainingRepository;

@Service
public class TrainingService {

	@Autowired
	public TrainingRepository trainingRepository;
	
	public void create(Training t) {
		trainingRepository.save(t);
	}
	
	public int delete(String id) {
		Optional<Training> found = trainingRepository.findById(id);
		if (found.isPresent()) {
			trainingRepository.deleteById(id);
			return 1;
		}
		return 0;
	}
	
	public List<Training> getAll() {
		return trainingRepository.findAll();
	}
	
}
