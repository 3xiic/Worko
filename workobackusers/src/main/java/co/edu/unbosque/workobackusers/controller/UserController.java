package co.edu.unbosque.workobackusers.controller;

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

import co.edu.unbosque.workobackusers.model.User;
import co.edu.unbosque.workobackusers.service.SeqUserService;
import co.edu.unbosque.workobackusers.service.UserService;

@RestController
@RequestMapping(name = "/user")
@Transactional
@CrossOrigin(origins = {"*"})
public class UserController {
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
	
	@Autowired
	public UserService userService;
	@Autowired
	public SeqUserService seqUserService;
	
	@PostMapping("/createUser")
	public void create(@RequestParam String username, @RequestParam String name, @RequestParam String email, @RequestParam Integer age, @RequestParam String genre, @RequestParam Integer height, @RequestParam Integer weight, @RequestParam String registration_date) {
		Date date = null;
		try {
			date = formatter.parse(registration_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User aux = new User(seqUserService.getNextValue(), username, name, email, age, genre, height, weight, date);
		userService.create(aux);
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAll() {
		List<User> aux = userService.getAll();
		if (aux.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(aux);
	}
}
