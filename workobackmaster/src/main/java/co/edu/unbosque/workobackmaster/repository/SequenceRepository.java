package co.edu.unbosque.workobackmaster.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.edu.unbosque.workobackmaster.model.Sequence;


public interface SequenceRepository extends MongoRepository<Sequence, String>{
	List<Sequence> findByName(String name);
}
