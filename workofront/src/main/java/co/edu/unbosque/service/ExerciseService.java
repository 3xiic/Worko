package co.edu.unbosque.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import co.edu.unbosque.model.Exercise;

public class ExerciseService {


	public static List<Exercise> exercises(String json) {
	    Gson gson = new Gson();
	    List<Exercise> exerciseList = new ArrayList<>();
	    
	    try {
	        Exercise[] exerciseArray = gson.fromJson(json, Exercise[].class);
	        exerciseList = Arrays.asList(exerciseArray);
	    } catch (JsonSyntaxException e) {
	        try {
	            Exercise exercise = gson.fromJson(json, Exercise.class);
	            exerciseList.add(exercise);
	        } catch (JsonSyntaxException ex) {
	            ex.printStackTrace();
	        }
	    }

	    return exerciseList;
	}
}
