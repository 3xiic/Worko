package co.edu.unbosque.workobackusers.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.workobackusers.model.Login;
import co.edu.unbosque.workobackusers.repository.LoginRepository;

@Service
public class LoginService {
	@Autowired
	public LoginRepository loginRepository;
	
	public void create(Login log) {
		loginRepository.save(log);
	}
	
	public int delete(String id) {
		Optional<Login> found = loginRepository.findById(id);
		if (found.isPresent()) {
			loginRepository.deleteById(id);
			return 1;
		}
		return 0;
	}
	
	public List<Login> getAll() {
		return loginRepository.findAll();
	}
}
