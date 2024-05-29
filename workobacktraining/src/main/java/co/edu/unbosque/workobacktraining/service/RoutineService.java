package co.edu.unbosque.workobacktraining.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.workobacktraining.model.Routine;
import co.edu.unbosque.workobacktraining.repository.RoutineRepository;

@Service
public class RoutineService {
	
	@Autowired
	public RoutineRepository routineRepository;
	
	public void create(Routine r) {
		routineRepository.save(r);
	}
	
	public int delete(String id) {
		Optional<Routine> found = routineRepository.findById(id);
		if (found.isPresent()) {
			routineRepository.deleteById(id);
			return 1;
		}
		return 0;
	}
	
	public List<Routine> getAll() {
		return routineRepository.findAll();
	}
	
}
