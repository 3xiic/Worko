package co.edu.unbosque.workobacktraining.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import co.edu.unbosque.workobacktraining.model.Sequence;
import co.edu.unbosque.workobacktraining.repository.SequenceRepository;

@Service
public class SequenceService {
	

	@Autowired
	private MongoOperations mg;
	@Autowired
	public SequenceRepository sequenceRepository;
	
	public Long getCurrentValue(String name) {
		List<Sequence> aux = sequenceRepository.findByName(name);
		return aux.get(0).getValue();
	}
	
	public Long getNextValue(String name) {
		List<Sequence> aux = sequenceRepository.findByName(name);
		final Sequence seq = mg.findAndModify(
				query(where("_id").is(aux.get(0).getId())),
				new Update().inc("value",1),
				options().returnNew(true).upsert(true),
				Sequence.class);
		return seq.getValue();
	}
	
}
