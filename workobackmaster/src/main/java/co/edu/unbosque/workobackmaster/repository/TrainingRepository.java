package co.edu.unbosque.workobackmaster.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.edu.unbosque.workobackmaster.model.Training;
import java.util.List;



public interface TrainingRepository extends MongoRepository<Training, String>{
	List<Training> findByIduser(Long iduser);
}
