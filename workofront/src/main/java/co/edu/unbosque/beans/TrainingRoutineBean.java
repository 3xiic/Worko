package co.edu.unbosque.beans;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.primefaces.PrimeFaces;

import co.edu.unbosque.controller.HttpClientSynchronous;
import co.edu.unbosque.model.Exercise;
import co.edu.unbosque.model.ExerciseRoutine;
import co.edu.unbosque.model.Routine;
import co.edu.unbosque.model.Training;
import co.edu.unbosque.service.ExerciseRoutineService;
import co.edu.unbosque.service.ExerciseService;
import co.edu.unbosque.service.RoutineService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("TrainingRoutineBean")
@RequestScoped
public class TrainingRoutineBean {

	private static List<Routine> routines;
	private static List<Exercise> exercises;
	private static List<ExerciseRoutine> exercisesRoutines;
	private static Routine selectedRoutine;
	private static Exercise selectedExercise;
	private int currentIndex;
	private Long iduser = (long) 3;
	
	
    private int seconds = 0;
    private Timer timer;
    private boolean running = false;

    public String create() {
    	System.out.println("ENTRO");
		Training t = new Training(iduser, selectedRoutine.getIdroutine(), Calendar.getInstance().getTime(), seconds);
		String response = HttpClientSynchronous.doPost("execute/createTraining", t);
		System.out.println("Crear training "+response);
    	stopTimer();
    	System.out.println(seconds);
    	return "";
    }
    public int getSeconds() {
        return seconds;
    }

    public boolean isRunning() {
        return running;
    }

    public void startTimer() {
    	System.out.println("corriendo");
        if (timer != null) {
            timer.cancel();
        }
        seconds = 0;
        timer = new Timer();
        running = true;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seconds++;
            }
        }, 1000, 1000);
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        running = false;
    }
	public String chooseRoutine(Routine routine) {
		TrainingRoutineBean.selectedRoutine = routine;
		this.currentIndex = 0;
		TrainingRoutineBean.exercises = getExercises(routine.getIdroutine()+"");
		TrainingRoutineBean.exercisesRoutines = getExercisesRoutines();
		TrainingRoutineBean.selectedExercise = exercises.get(currentIndex);
		return "exercise.xhtml?faces-redirect=true";
	}
	
	public String getSetsAndReps() {
		if (exercisesRoutines != null && selectedExercise != null) {
			for (ExerciseRoutine er : exercisesRoutines) {
				if (er.getIdexercise() == selectedExercise.getIdexercise()) {
					return er.getSets() + " sets de " + er.getRepetitions() + " repeticiones";
				}
			}
		}
		return "Información no disponible";
	}

	public List<Routine> getRoutines() {
		if (routines == null) {
			String json = HttpClientSynchronous.doGetAndParse("http://localhost:8085/execute/get?path=getAllRoutines");
			routines = RoutineService.routines(json);
		}
		return routines;
	}
	public void setRoutines(List<Routine> routines) {
		TrainingRoutineBean.routines = routines;
	}
	public List<Exercise> getExercises(String id) {
	    if (exercises == null) {
	        String json = HttpClientSynchronous.doGetAndParse("http://localhost:8085/execute/get?path=getByRoutine%3Fidroutine%3D"+id);
	        exercises = ExerciseService.exercises(json);
	    }
	    return exercises;
	}

	public void setExercises(List<Exercise> exercises) {
		TrainingRoutineBean.exercises = exercises;
	}
	public List<ExerciseRoutine> getExercisesRoutines() {
		if (exercisesRoutines == null) {
			String json = HttpClientSynchronous.doGetAndParse("http://localhost:8085/execute/get?path=getAllExerciseRoutine");
			exercisesRoutines = ExerciseRoutineService.exercisesRoutines(json);
		}
		return exercisesRoutines;
	}
	
	public void setExercisesRoutines(List<ExerciseRoutine> exercisesRoutines) {
		TrainingRoutineBean.exercisesRoutines = exercisesRoutines;
	}

	public Routine getSelectedRoutine() {
		return selectedRoutine;
	}
	public void setSelectedRoutine(Routine selectedRoutine) {
		TrainingRoutineBean.selectedRoutine = selectedRoutine;
	}
	
	public Exercise getSelectedExercise() {
		return selectedExercise;
	}
	public void setSelectedExercise(Exercise selectedExercise) {
		TrainingRoutineBean.selectedExercise = selectedExercise;
	}
	public int getCurrentIndex() {
		return currentIndex;
	}
	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}
	
	public List<Exercise> getExercises() {
		return exercises;
	}

}
