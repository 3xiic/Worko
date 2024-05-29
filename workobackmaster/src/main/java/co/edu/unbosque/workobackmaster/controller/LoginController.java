package co.edu.unbosque.workobackmaster.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.workobackmaster.model.Login;
import co.edu.unbosque.workobackmaster.service.LoginService;
import co.edu.unbosque.workobackmaster.service.SequenceService;


@RestController
@RequestMapping(name = "/login")
@Transactional
@CrossOrigin(origins = {"*"})
public class LoginController {
	
	@Autowired
	public LoginService loginService;
	@Autowired
	public SequenceService seqUserService;
	
	@GetMapping("/getAllLogin")
	public ResponseEntity<List<Login>> getAll() {
		List<Login> aux = loginService.getAll();
		if (aux.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(aux);
	}
}
