package co.edu.unbosque.workobackusers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.workobackusers.model.Login;
import co.edu.unbosque.workobackusers.service.LoginService;
import co.edu.unbosque.workobackusers.service.SeqUserService;

@RestController
@RequestMapping(name = "/login")
@Transactional
@CrossOrigin(origins = {"*"})
public class LoginController {
	
	@Autowired
	public LoginService loginService;
	@Autowired
	public SeqUserService seqUserService;
	
	@PostMapping("/createLogin")
	public void create(@RequestParam String password) {
		Login aux = new Login(seqUserService.getCurrentValue(), password);
		loginService.create(aux);
	}
	
	@GetMapping("/getAllLogin")
	public ResponseEntity<List<Login>> getAll() {
		List<Login> aux = loginService.getAll();
		if (aux.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(aux);
	}
}
