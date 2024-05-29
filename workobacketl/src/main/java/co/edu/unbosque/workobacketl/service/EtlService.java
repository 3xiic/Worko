package co.edu.unbosque.workobacketl.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import co.edu.unbosque.workobacketl.model.ExerciseETL;
import co.edu.unbosque.workobacketl.model.ExerciseRoutineETL;
import co.edu.unbosque.workobacketl.model.LoginETL;
import co.edu.unbosque.workobacketl.model.RoutineETL;
import co.edu.unbosque.workobacketl.model.TrainingETL;
import co.edu.unbosque.workobacketl.model.UserETL;


@Service
public class EtlService {

	public static ArrayList<ExerciseETL> exerciseETLs;
	public static ArrayList<ExerciseRoutineETL> exerciseRoutineETLs;
	public static ArrayList<LoginETL> loginETLs;
	public static ArrayList<RoutineETL> routineETLs;
	public static ArrayList<TrainingETL> trainingETLs;
	public static ArrayList<UserETL> userETLs;
	
	
	public void transform(String json, Object o) {
		Gson g = new Gson();
		if(ExerciseETL.class.equals(o)) {
			ExerciseETL[] temps = g.fromJson(json, ExerciseETL[].class);
			exerciseETLs = new ArrayList<>(Arrays.asList(temps));
		} else if (ExerciseRoutineETL.class.equals(o)) {
			ExerciseRoutineETL[] temps = g.fromJson(json, ExerciseRoutineETL[].class);
			exerciseRoutineETLs = new ArrayList<>(Arrays.asList(temps));
		} else if (LoginETL.class.equals(o)) {
			LoginETL[] temps = g.fromJson(json, LoginETL[].class);
			loginETLs = new ArrayList<>(Arrays.asList(temps));
		} else if (RoutineETL.class.equals(o)) {
			RoutineETL[] temps = g.fromJson(json, RoutineETL[].class);
			routineETLs = new ArrayList<>(Arrays.asList(temps));
		} else if (TrainingETL.class.equals(o)) {
			TrainingETL[] temps = g.fromJson(json, TrainingETL[].class);
			trainingETLs = new ArrayList<>(Arrays.asList(temps));
		} else if (UserETL.class.equals(o)) {
			UserETL[] temps = g.fromJson(json, UserETL[].class);
			userETLs = new ArrayList<>(Arrays.asList(temps));
		}
	}
}
