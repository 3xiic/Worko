package co.edu.unbosque.workobackusers.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.workobackusers.model.User;
import co.edu.unbosque.workobackusers.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	public UserRepository userRepository;
	
	public void create(User u) {
		userRepository.save(u);
	}
	
	public int delete(String id) {
		Optional<User> found = userRepository.findById(id);
		if (found.isPresent()) {
			userRepository.deleteById(id);
			return 1;
		}
		return 0;
	}
	
	public List<User> getAll() {
		return userRepository.findAll();
	}
}
