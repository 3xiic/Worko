package co.edu.unbosque.workobackusers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import co.edu.unbosque.workobackusers.model.SeqUser;
import co.edu.unbosque.workobackusers.repository.SeqUserRepository;

@Service
public class SeqUserService {
	
	@Autowired
	private MongoOperations mg;
	@Autowired
	public SeqUserRepository seqUserRepository;
	
	public Long getCurrentValue() {
		List<SeqUser> aux = seqUserRepository.findAll();
		return aux.get(0).getValue();
	}
	
	public Long getNextValue() {
		List<SeqUser> aux = seqUserRepository.findAll();
		final SeqUser seq = mg.findAndModify(
				query(where("_id").is(aux.get(0).getId())),
				new Update().inc("value",1),
				options().returnNew(true).upsert(true),
				SeqUser.class);
		return seq.getValue();
	}
	
}
