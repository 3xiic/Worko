package co.edu.unbosque.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import co.edu.unbosque.model.Exercise;
import co.edu.unbosque.model.ExerciseRoutine;

public class ExerciseRoutineService {

	public static List<ExerciseRoutine> exercisesRoutines(String json) {
	    Gson gson = new Gson();
	    List<ExerciseRoutine> exerciseRoutineList = new ArrayList<>();
	    
	    try {
	        ExerciseRoutine[] exerciseRoutineArray = gson.fromJson(json, ExerciseRoutine[].class);
	        exerciseRoutineList = Arrays.asList(exerciseRoutineArray);
	    } catch (JsonSyntaxException e) {
	        try {
	            ExerciseRoutine exerciseRoutine = gson.fromJson(json, ExerciseRoutine.class);
	            exerciseRoutineList.add(exerciseRoutine);
	        } catch (JsonSyntaxException ex) {
	            ex.printStackTrace();
	        }
	    }

	    return exerciseRoutineList;
	}

}
