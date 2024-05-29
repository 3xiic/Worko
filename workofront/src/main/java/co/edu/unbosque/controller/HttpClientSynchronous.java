package co.edu.unbosque.controller;

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
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import co.edu.unbosque.model.Exercise;
import co.edu.unbosque.model.ExerciseRoutine;
import co.edu.unbosque.model.Login;
import co.edu.unbosque.model.Routine;
import co.edu.unbosque.model.Training;
import co.edu.unbosque.model.User;
import co.edu.unbosque.service.ExerciseRoutineService;
import co.edu.unbosque.service.ExerciseService;
import co.edu.unbosque.service.RoutineService;

public class HttpClientSynchronous {
	
	private static final SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy/MM/dd");
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
	
	private static final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1)
			.connectTimeout(Duration.ofSeconds(10)).build();
	
	public static String doGetAndParse(String url) {
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
		return uglyJson;
	}
	
	public String doPostUser(User u, Login log) {
		String formParams = convertToFormParams(u);
		String formParamsLog = convertToFormParams(log);
		HttpRequest request = HttpRequest.newBuilder()
				.POST(HttpRequest.BodyPublishers.ofString(formParams+formParamsLog))
				.uri(URI.create("http://localhost:8085/createUser"))
				.setHeader("User-Agent", "Java 11 HttpClient Bot")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.build();

		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("status code -> " + response.statusCode());

		return prettyPrintUsingGson(response.body());
	}
	
	public static String doPost(String url, Object o) {
		String formParams = convertToFormParams(o);
		HttpRequest request = HttpRequest.newBuilder()
				.POST(HttpRequest.BodyPublishers.ofString(formParams))
				.uri(URI.create("http://localhost:8085/"+url))
				.setHeader("User-Agent", "Java 11 HttpClient Bot")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.build();

		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("status code -> " + response.statusCode());

		return response.body();
	}
	
	private static String convertToFormParams(Object o) {
		if (o instanceof Exercise){
			Exercise exercise = (Exercise) o;
			return "name=" + urlEncode(exercise.getName()) +
					"&difficulty=" + urlEncode(exercise.getDifficulty()) +
					"&equipment=" + urlEncode(exercise.getEquipment()) +
					"&focus=" + urlEncode(exercise.getFocus()) +
					"&url=" + urlEncode(exercise.getUrl());
		} else if (o instanceof User){
			User user = (User) o;
			String formattedDate = "";
			try {
	            Date date = inputDateFormat.parse(user.getRegistrationdate()+"");
	            formattedDate = dateFormat.format(date);
	        } catch (ParseException e) {
	            System.err.println("Error al parsear la fecha: " + e.getMessage());
	        }
			return 	"username=" + urlEncode(user.getUsername()) +
					"&name=" + urlEncode(user.getName()) +
					"&email=" + urlEncode(user.getEmail()) +
					"&age=" + urlEncode(user.getAge()+"") +
					"&genre=" + urlEncode(user.getGenre()) +
					"&height=" + urlEncode(user.getHeight()+"") +
					"&weight=" + urlEncode(user.getWeight()+"") +
					"&registrationdate=" + urlEncode(formattedDate);
			
		} else if (o instanceof Login){
			
			Login login = (Login) o;
			
			return 	"&password=" + urlEncode(login.getPassword());
			
		} else if (o instanceof Training){
			Training training = (Training) o;
			String formattedDate = "";
			try {
	            formattedDate = dateFormat.format(training.getDate());
	        } catch (Exception e) {
	            System.err.println("Error al parsear la fecha: " + e.getMessage());
	        }
			return "iduser=" + urlEncode(training.getIduser()+"") +
					"&idroutine=" + urlEncode(training.getIdroutine()+"") +
					"&date=" + urlEncode(formattedDate) +
					"&duration=" + urlEncode(training.getDuration()+"");
			
		} else if (o instanceof Routine){
			
			Routine routine = (Routine) o;
			return "name=" + urlEncode(routine.getName()) +
					"&description=" + urlEncode(routine.getDescription()) +
					"&difficulty=" + urlEncode(routine.getDifficulty());
			
		} else if (o instanceof ExerciseRoutine){
			
			ExerciseRoutine exerciseRoutine = (ExerciseRoutine) o;
			return "idroutine=" + urlEncode(exerciseRoutine.getIdroutine()+"") +
					"&idexercise=" + urlEncode(exerciseRoutine.getIdexercise()+"") +
					"&sets=" + urlEncode(exerciseRoutine.getSets()+"") +
					"&repetitions=" + urlEncode(exerciseRoutine.getRepetitions()+"");
		}
		return "";
	}
	
	public static List<Routine> routines(String url){
		return RoutineService.routines(doGetAndParse(url));
	}
	public static List<Exercise> exercise(String url){
		return ExerciseService.exercises(doGetAndParse(url));
	}
	public static List<ExerciseRoutine> exerciseRoutine(String url){
		return ExerciseRoutineService.exercisesRoutines(doGetAndParse(url));
	}
	private static String urlEncode(String value) {
		return value == null ? "" : URLEncoder.encode(value, StandardCharsets.UTF_8);
	}
	
	public static String prettyPrintUsingGson(String uglyJson) {
		Gson gson = new GsonBuilder().setLenient().setPrettyPrinting().create();
			JsonElement jsonElement = JsonParser.parseString(uglyJson);
			String prettyJsonString = gson.toJson(jsonElement);
			return prettyJsonString;
		}
}
