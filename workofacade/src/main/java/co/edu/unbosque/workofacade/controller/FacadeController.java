package co.edu.unbosque.workofacade.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@RestController
@CrossOrigin(origins = {"*"})
@Transactional
@RequestMapping("/execute")
public class FacadeController {
	
	private static final SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy/MM/dd");
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
	
	private static final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1)
			.connectTimeout(Duration.ofSeconds(10)).build();
	
	@GetMapping("/get")
	public  ResponseEntity<String> get(@RequestParam String path) {
		String url = "http://localhost:8086/"+path;
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
		return ResponseEntity.ok(uglyJson);
	}
	
	@PostMapping("/createUser")
	public ResponseEntity<String> postUsers(@RequestParam String username, @RequestParam String name, @RequestParam String email, @RequestParam Integer age, @RequestParam String genre, @RequestParam Integer height, @RequestParam Integer weight, @RequestParam String registrationdate, @RequestParam String password) {
		boolean master = false;
		boolean other = false;
		String formattedDate = "";
		try {
            Date date = inputDateFormat.parse(registrationdate);
            formattedDate = dateFormat.format(date);
        } catch (ParseException e) {
            System.err.println("Error al parsear la fecha: " + e.getMessage());
        }
		
		String params = "username=" + urlEncode(username) +
				"&name=" + urlEncode(name) +
				"&email=" + urlEncode(email) +
				"&age=" + urlEncode(age.toString()) +
				"&genre=" + urlEncode(genre) +
				"&height=" + urlEncode(height.toString()) +
				"&weight=" + urlEncode(weight.toString()) +
				"&registrationdate=" + urlEncode(formattedDate) +
				"&password=" + urlEncode(password);
		
		//Post to master
		HttpRequest request = HttpRequest.newBuilder()
				.POST(HttpRequest.BodyPublishers.ofString(params))
				.uri(URI.create("http://localhost:8086/createUser"))
				.setHeader("User-Agent", "Java 11 HttpClient Bot")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.build();
		
		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("status code -> " + response.statusCode());
			if (response.statusCode() == HttpStatus.OK.value()) {
				master = true;
			}
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
		
		//Post to user table
		params = "username=" + urlEncode(username) +
				"&name=" + urlEncode(name) +
				"&email=" + urlEncode(email) +
				"&age=" + urlEncode(age.toString()) +
				"&genre=" + urlEncode(genre) +
				"&height=" + urlEncode(height.toString()) +
				"&weight=" + urlEncode(weight.toString()) +
				"&registration_date=" + urlEncode(formattedDate);
		
		request = HttpRequest.newBuilder()
				.POST(HttpRequest.BodyPublishers.ofString(params))
				.uri(URI.create("http://localhost:8082/createUser"))
				.setHeader("User-Agent", "Java 11 HttpClient Bot")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.build();
		
		response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("status code -> " + response.statusCode());
			if (response.statusCode() == HttpStatus.OK.value()) {
				other = true;
			}
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
		
		//Post to login table
		params = "password=" + urlEncode(password);
		
		request = HttpRequest.newBuilder()
				.POST(HttpRequest.BodyPublishers.ofString(params))
				.uri(URI.create("http://localhost:8082/createLogin"))
				.setHeader("User-Agent", "Java 11 HttpClient Bot")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.build();
		
		response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("status code -> " + response.statusCode());
			if (response.statusCode() == HttpStatus.OK.value()) {
				other = true;
			}
		} catch (InterruptedException | IOException e) {
			other = false;
			e.printStackTrace();
		}
		
		if (master == true && other == true) {			
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Datos agregados correctamente");
		} else if (master == false) {			
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Error al subir al master");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Error al subir a las otras bases de datos");			
		}
	}
	
	@PostMapping("/createTraining")
	public ResponseEntity<String> postTraining(@RequestParam Long iduser, @RequestParam Long idroutine, @RequestParam String date, @RequestParam Integer duration) {
		boolean master = false;
		boolean other = false;
		
		String formattedDate = "";
		try {
            Date dateinput = inputDateFormat.parse(date);
            formattedDate = dateFormat.format(dateinput);
        } catch (ParseException e) {
            System.err.println("Error al parsear la fecha: " + e.getMessage());
        }
		
		String params = "iduser=" + urlEncode(iduser.toString()) +
				"&idroutine=" + urlEncode(idroutine.toString()) +
				"&date=" + urlEncode(formattedDate) +
				"&duration=" + urlEncode(duration.toString());
		
		//Post to master
		HttpRequest request = HttpRequest.newBuilder()
				.POST(HttpRequest.BodyPublishers.ofString(params))
				.uri(URI.create("http://localhost:8086/createTraining"))
				.setHeader("User-Agent", "Java 11 HttpClient Bot")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.build();
		
		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("status code -> " + response.statusCode());
			if (response.statusCode() == HttpStatus.OK.value()) {
				master = true;
			}
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
		
		//Post to training table
		params = "id_user=" + urlEncode(iduser.toString()) +
				"&id_routine=" + urlEncode(idroutine.toString()) +
				"&date=" + urlEncode(formattedDate) +
				"&duration=" + urlEncode(duration.toString());
		
		request = HttpRequest.newBuilder()
				.POST(HttpRequest.BodyPublishers.ofString(params))
				.uri(URI.create("http://localhost:8083/createTraining"))
				.setHeader("User-Agent", "Java 11 HttpClient Bot")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.build();
		
		response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("status code -> " + response.statusCode());
			if (response.statusCode() == HttpStatus.OK.value()) {
				other = true;
			}
		} catch (InterruptedException | IOException e) {
			other = false;
			e.printStackTrace();
		}
		
		if (master == true && other == true) {			
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Datos agregados correctamente");
		} else if (master == false) {			
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Error al subir al master");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Error al subir a las otras bases de datos");			
		}
	}
	
	@PostMapping("/createRoutine")
	public ResponseEntity<String> postRoutine(@RequestParam String name, @RequestParam String description, @RequestParam String difficulty) {
		boolean master = false;
		boolean other = false;
		
		String params = "name=" + urlEncode(name) +
				"&description=" + urlEncode(description) +
				"&difficulty=" + urlEncode(difficulty);
		
		//Post to master
		HttpRequest request = HttpRequest.newBuilder()
				.POST(HttpRequest.BodyPublishers.ofString(params))
				.uri(URI.create("http://localhost:8086/createRoutine"))
				.setHeader("User-Agent", "Java 11 HttpClient Bot")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.build();
		
		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("status code -> " + response.statusCode());
			if (response.statusCode() == HttpStatus.OK.value()) {
				master = true;
			}
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
		
		//Post to training table
		request = HttpRequest.newBuilder()
				.POST(HttpRequest.BodyPublishers.ofString(params))
				.uri(URI.create("http://localhost:8083/createRoutine"))
				.setHeader("User-Agent", "Java 11 HttpClient Bot")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.build();
		
		response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("status code -> " + response.statusCode());
			if (response.statusCode() == HttpStatus.OK.value()) {
				other = true;
			}
		} catch (InterruptedException | IOException e) {
			other = false;
			e.printStackTrace();
		}
		
		if (master == true && other == true) {			
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Datos agregados correctamente");
		} else if (master == false) {			
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Error al subir al master");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Error al subir a las otras bases de datos");			
		}
	}
	
	@PostMapping("/createExerciseRoutine")
	public ResponseEntity<String> postExerciseRoutine(@RequestParam Long idroutine, @RequestParam Long idexercise, @RequestParam Integer sets, @RequestParam Integer repetitions) {
		boolean master = false;
		boolean other = false;
		
		String params = "idroutine=" + urlEncode(idroutine.toString()) +
				"&idexercise=" + urlEncode(idexercise.toString()) +
				"&sets=" + urlEncode(sets.toString()) +
				"&repetitions=" + urlEncode(repetitions.toString());
		
		//Post to master
		HttpRequest request = HttpRequest.newBuilder()
				.POST(HttpRequest.BodyPublishers.ofString(params))
				.uri(URI.create("http://localhost:8086/createExerciseRoutine"))
				.setHeader("User-Agent", "Java 11 HttpClient Bot")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.build();
		
		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("status code -> " + response.statusCode());
			if (response.statusCode() == HttpStatus.OK.value()) {
				master = true;
			}
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
		
		//Post to training table
		params = "id_routine=" + urlEncode(idroutine.toString()) +
				"&id_exercise=" + urlEncode(idexercise.toString()) +
				"&sets=" + urlEncode(sets.toString()) +
				"&repetitions=" + urlEncode(repetitions.toString());
		
		request = HttpRequest.newBuilder()
				.POST(HttpRequest.BodyPublishers.ofString(params))
				.uri(URI.create("http://localhost:8083/createExerciseRoutine"))
				.setHeader("User-Agent", "Java 11 HttpClient Bot")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.build();
		
		response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("status code -> " + response.statusCode());
			if (response.statusCode() == HttpStatus.OK.value()) {
				other = true;
			}
		} catch (InterruptedException | IOException e) {
			other = false;
			e.printStackTrace();
		}
		
		if (master == true && other == true) {			
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Datos agregados correctamente");
		} else if (master == false) {			
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Error al subir al master");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Error al subir a las otras bases de datos");			
		}
	}
	
	@PostMapping("/createExercise")
	public ResponseEntity<String> postExercise(@RequestParam String name, @RequestParam String difficulty, @RequestParam String equipment, @RequestParam String focus, @RequestParam String url) {
		boolean master = false;
		boolean other = false;
		
		String params = "name=" + urlEncode(name) +
				"&difficulty=" + urlEncode(difficulty) +
				"&equipment=" + urlEncode(equipment) +
				"&focus=" + urlEncode(focus) +
				"&url=" + urlEncode(url);
		
		//Post to master
		HttpRequest request = HttpRequest.newBuilder()
				.POST(HttpRequest.BodyPublishers.ofString(params))
				.uri(URI.create("http://localhost:8086/createExercise"))
				.setHeader("User-Agent", "Java 11 HttpClient Bot")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.build();
		
		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("status code -> " + response.statusCode());
			if (response.statusCode() == HttpStatus.OK.value()) {
				master = true;
			}
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
		
		//Post to training table
		request = HttpRequest.newBuilder()
				.POST(HttpRequest.BodyPublishers.ofString(params))
				.uri(URI.create("http://localhost:8081/create"))
				.setHeader("User-Agent", "Java 11 HttpClient Bot")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.build();
		
		response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("status code -> " + response.statusCode());
			if (response.statusCode() == HttpStatus.OK.value()) {
				other = true;
			}
		} catch (InterruptedException | IOException e) {
			other = false;
			e.printStackTrace();
		}
		
		if (master == true && other == true) {			
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Datos agregados correctamente: " + response.body());
		} else if (master == false) {			
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Error al subir al master: " + response.body());
		} else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Error al subir a las otras bases de datos: " + response.body());			
		}
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
