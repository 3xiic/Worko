package co.edu.unbosque.workobacketl.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import co.edu.unbosque.workobacketl.model.ExerciseETL;
import co.edu.unbosque.workobacketl.model.ExerciseRoutineETL;
import co.edu.unbosque.workobacketl.model.LoginETL;
import co.edu.unbosque.workobacketl.model.RoutineETL;
import co.edu.unbosque.workobacketl.model.TrainingETL;
import co.edu.unbosque.workobacketl.model.UserETL;
import co.edu.unbosque.workobacketl.service.EtlService;

@RestController
@CrossOrigin(origins = {"*"})
@Transactional
@RequestMapping("/execute")
public class EtlController {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
	
	private static final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1)
			.connectTimeout(Duration.ofSeconds(10)).build();

	@Autowired
	public EtlService etlService;

	@GetMapping("/get")
	public  ResponseEntity<String> getExercisesAndParse(String url) {
		Object o = null;
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
				.setHeader("User-Agent", "Java 11 HttpClient Bot").build();

		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("status code -> " + response.statusCode());

		String uglyJson = response.body();
		
		if (url.equals("http://localhost:8081/getAll")) o = ExerciseETL.class;
		else if (url.equals("http://localhost:8082/getAllUsers")) o = UserETL.class;
		else if (url.equals("http://localhost:8082/getAllLogin")) o = LoginETL.class;
		else if (url.equals("http://localhost:8083/getAllTrainings")) o = TrainingETL.class;
		else if (url.equals("http://localhost:8083/getAllRoutines")) o = RoutineETL.class;
		else if (url.equals("http://localhost:8083/getAllExerciseRoutine")) o = ExerciseRoutineETL.class;


		etlService.transform(prettyPrintUsingGson(uglyJson),o);
		return ResponseEntity.ok(uglyJson);
	}

	@PostMapping("/loadExercises")
	public ResponseEntity<String> postExercises() {
		List<ExerciseETL> exercises = EtlService.exerciseETLs; 

		for (ExerciseETL exercise : exercises) {
			String formParams = convertToFormParams(exercise);
			HttpRequest request = HttpRequest.newBuilder()
					.POST(HttpRequest.BodyPublishers.ofString(formParams))
					.uri(URI.create("http://localhost:8086/createExercise"))
					.setHeader("User-Agent", "Java 11 HttpClient Bot")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.build();

			HttpResponse<String> response = null;
			try {
				response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
				System.out.println("status code -> " + response.statusCode());
				if (response.statusCode() != HttpStatus.OK.value()) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to post exercise: " + response.body());
				}
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error posting exercise: " + e.getMessage());
			}
		}
		return ResponseEntity.ok("All exercises posted successfully.");
	}
	
	@PostMapping("/loadRoutines")
	public ResponseEntity<String> postRoutines() {
		List<RoutineETL> routines = EtlService.routineETLs; 

		for (RoutineETL routine : routines) {
			String formParams = convertToFormParams(routine);
			HttpRequest request = HttpRequest.newBuilder()
					.POST(HttpRequest.BodyPublishers.ofString(formParams))
					.uri(URI.create("http://localhost:8086/createRoutine"))
					.setHeader("User-Agent", "Java 11 HttpClient Bot")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.build();

			HttpResponse<String> response = null;
			try {
				response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
				System.out.println("status code -> " + response.statusCode());
				if (response.statusCode() != HttpStatus.OK.value()) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to post exercise: " + response.body());
				}
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error posting exercise: " + e.getMessage());
			}
		}
		return ResponseEntity.ok("All routines posted successfully.");
	}
	
	@PostMapping("/loadTraining")
	public ResponseEntity<String> postTraining() {
		List<TrainingETL> trainings = EtlService.trainingETLs; 

		for (TrainingETL training : trainings) {
			String formParams = convertToFormParams(training);
			HttpRequest request = HttpRequest.newBuilder()
					.POST(HttpRequest.BodyPublishers.ofString(formParams))
					.uri(URI.create("http://localhost:8086/createTraining"))
					.setHeader("User-Agent", "Java 11 HttpClient Bot")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.build();

			HttpResponse<String> response = null;
			try {
				response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
				System.out.println("status code -> " + response.statusCode());
				if (response.statusCode() != HttpStatus.OK.value()) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to post exercise: " + response.body());
				}
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error posting exercise: " + e.getMessage());
			}
		}
		return ResponseEntity.ok("All trainings posted successfully.");
	}
	
	@PostMapping("/loadUser")
	public ResponseEntity<String> postUsers() {
		List<UserETL> users = EtlService.userETLs;
		List<LoginETL> logins = EtlService.loginETLs; 
		
		for (int i = 0; i < users.size(); i++) {
			String formParams = convertToFormParams(users.get(i));
			String formParamsLog = convertToFormParams(logins.get(i));
			HttpRequest request = HttpRequest.newBuilder()
					.POST(HttpRequest.BodyPublishers.ofString(formParams+formParamsLog))
					.uri(URI.create("http://localhost:8086/createUser"))
					.setHeader("User-Agent", "Java 11 HttpClient Bot")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.build();

			HttpResponse<String> response = null;
			try {
				response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
				System.out.println("status code -> " + response.statusCode());
				if (response.statusCode() != HttpStatus.OK.value()) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to post exercise: " + response.body());
				}
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error posting exercise: " + e.getMessage());
			}
		}
		return ResponseEntity.ok("All users posted successfully.");
	}
	
	@PostMapping("/loadExerciseRoutine")
	public ResponseEntity<String> postExerciseRoutine() {
		List<ExerciseRoutineETL> exercisesRoutines = EtlService.exerciseRoutineETLs; 

		for (ExerciseRoutineETL exerciseRoutine : exercisesRoutines) {
			String formParams = convertToFormParams(exerciseRoutine);
			HttpRequest request = HttpRequest.newBuilder()
					.POST(HttpRequest.BodyPublishers.ofString(formParams))
					.uri(URI.create("http://localhost:8086/createExerciseRoutine"))
					.setHeader("User-Agent", "Java 11 HttpClient Bot")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.build();

			HttpResponse<String> response = null;
			try {
				response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
				System.out.println("status code -> " + response.statusCode());
				if (response.statusCode() != HttpStatus.OK.value()) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to post exercise: " + response.body());
				}
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error posting exercise: " + e.getMessage());
			}
		}
		return ResponseEntity.ok("All exercises routines posted successfully.");
	}

	private String convertToFormParams(Object o) {
		if (o instanceof ExerciseETL){
			ExerciseETL exercise = (ExerciseETL) o;
			return "name=" + urlEncode(exercise.getName()) +
					"&difficulty=" + urlEncode(exercise.getDifficulty()) +
					"&equipment=" + urlEncode(exercise.getEquipment()) +
					"&focus=" + urlEncode(exercise.getFocus()) +
					"&url=" + urlEncode(exercise.getUrl());
		} else if (o instanceof UserETL){
			UserETL user = (UserETL) o;
			return 	"username=" + urlEncode(user.getUsername()) +
					"&name=" + urlEncode(user.getName()) +
					"&email=" + urlEncode(user.getEmail()) +
					"&age=" + urlEncode(user.getAge()+"") +
					"&genre=" + urlEncode(user.getGenre()) +
					"&height=" + urlEncode(user.getHeight()+"") +
					"&weight=" + urlEncode(user.getWeight()+"") +
					"&registrationdate=" + urlEncode(dateFormat.format(user.getRegistration_date()));
			
		} else if (o instanceof LoginETL){
			
			LoginETL login = (LoginETL) o;
			
			return 	"&password=" + urlEncode(login.getPassword());
			
		} else if (o instanceof TrainingETL){
			TrainingETL training = (TrainingETL) o;
			return "iduser=" + urlEncode(training.getId_user()+"") +
					"&idroutine=" + urlEncode(training.getId_routine()+"") +
					"&date=" + urlEncode(dateFormat.format(training.getDate())) +
					"&duration=" + urlEncode(training.getDuration()+"");
			
		} else if (o instanceof RoutineETL){
			
			RoutineETL routine = (RoutineETL) o;
			return "name=" + urlEncode(routine.getName()) +
					"&description=" + urlEncode(routine.getDescription()) +
					"&difficulty=" + urlEncode(routine.getDifficulty());
			
		} else if (o instanceof ExerciseRoutineETL){
			
			ExerciseRoutineETL exerciseRoutine = (ExerciseRoutineETL) o;
			return "idroutine=" + urlEncode(exerciseRoutine.getId_routine()+"") +
					"&idexercise=" + urlEncode(exerciseRoutine.getId_exercise()+"") +
					"&sets=" + urlEncode(exerciseRoutine.getSets()+"") +
					"&repetitions=" + urlEncode(exerciseRoutine.getRepetitions()+"");
		}
		return "";
	}

	private String urlEncode(String value) {
		return value == null ? "" : URLEncoder.encode(value, StandardCharsets.UTF_8);
	}


	public static String prettyPrintUsingGson(String uglyJson) {
		Gson gson = new GsonBuilder().setLenient().setPrettyPrinting().create();
		JsonElement jsonElement = JsonParser.parseString(uglyJson);
		String prettyJsonString = gson.toJson(jsonElement);
		return prettyJsonString;
	}

}
