package co.edu.unbosque.workobacktraining.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.edu.unbosque.workobacktraining.model.Sequence;

public interface SequenceRepository extends MongoRepository<Sequence, String>{
	List<Sequence> findByName(String name);
}
