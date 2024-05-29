package co.edu.unbosque.workobackmaster.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.workobackmaster.model.User;
import co.edu.unbosque.workobackmaster.repository.UserRepository;


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
	
	public User findById(String id) {
		Optional<User> found = userRepository.findById(id);
		if (found.isPresent()) {
			return found.get();
		}
		return null;
	}
	
	public User findByUsername(String username) {
		if (userRepository.findByUsername(username).isEmpty()) {
			return null;
		}
		User aux = userRepository.findByUsername(username).get(0);
		if (aux != null) {
			return aux;
		}
		return null;
	}
	
	public User findByIdUser(Long iduser) {
		User aux = userRepository.findByIdusr(iduser).get(0);
		if (aux != null) {
			return aux;
		}
		return null;
	}
}
