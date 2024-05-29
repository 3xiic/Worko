package co.edu.unbosque.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import co.edu.unbosque.model.Routine;

public class RoutineService {

	public static List<Routine> routines(String json){
		Gson g = new Gson();
		Type routineListType = new TypeToken<List<Routine>>(){}.getType();
		List<Routine> routines = g.fromJson(json, routineListType);
		return routines;
	}
}
