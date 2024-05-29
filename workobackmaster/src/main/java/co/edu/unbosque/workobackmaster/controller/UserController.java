package co.edu.unbosque.workobackmaster.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import co.edu.unbosque.workobackmaster.model.Login;
import co.edu.unbosque.workobackmaster.model.User;
import co.edu.unbosque.workobackmaster.service.LoginService;
import co.edu.unbosque.workobackmaster.service.SequenceService;
import co.edu.unbosque.workobackmaster.service.UserService;

@RestController
@RequestMapping(name = "/user")
@Transactional
@CrossOrigin(origins = {"*"})
public class UserController {
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
	
	@Autowired
	public UserService userService;
	@Autowired
	public LoginService loginService;
	@Autowired
	public SequenceService sequenceService;
	
	@PostMapping("/createUser")
	public void create(@RequestParam String username, @RequestParam String name, @RequestParam String email, @RequestParam Integer age, @RequestParam String genre, @RequestParam Integer height, @RequestParam Integer weight, @RequestParam String registrationdate, @RequestParam String password) {
		Date date = null;
		try {
			date = formatter.parse(registrationdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User aux = new User(sequenceService.getNextValue("sequser"), username, name, email, age, genre, height, weight, date);
		userService.create(aux);
		Login logaux = new Login(sequenceService.getCurrentValue("sequser"), password);
		loginService.create(logaux);
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAll() {
		List<User> aux = userService.getAll();
		if (aux.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(aux);
	}
	
	@GetMapping("/userlogin")
	public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
		User usr = userService.findByUsername(username);
		if (usr == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario no encontrado!");
		}
		Login log = loginService.findByIduser(usr.getIdusr());
		if (password.equals(log.getPassword())) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(usr.getId());
		} else {			
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales erroneas!");
		}
	}
	
}
