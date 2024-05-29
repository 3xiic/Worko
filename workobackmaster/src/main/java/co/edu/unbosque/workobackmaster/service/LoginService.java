package co.edu.unbosque.workobackmaster.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.workobackmaster.model.Login;
import co.edu.unbosque.workobackmaster.repository.LoginRepository;


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
	
	public Login findById(String id) {
		Optional<Login> found = loginRepository.findById(id);
		if (found.isPresent()) {
			return found.get();
		}
		return null;
	}
	
	public Login findByIduser(Long iduser) {
		Login aux = loginRepository.findByIduser(iduser).get(0);
		if (aux != null) {
			return aux;
		}
		return null;
	}
}
