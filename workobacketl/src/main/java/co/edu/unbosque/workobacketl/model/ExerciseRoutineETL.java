package co.edu.unbosque.workobacketl.model;


public class ExerciseRoutineETL {
	
	private String id;
	private Long id_routine;
	private Long id_exercise;
	private Integer sets;
	private Integer repetitions;
	
	public ExerciseRoutineETL() {
		// TODO Auto-generated constructor stub
	}

	public ExerciseRoutineETL(Long id_routine, Long id_exercise, Integer sets, Integer repetitions) {
		this.id_routine = id_routine;
		this.id_exercise = id_exercise;
		this.sets = sets;
		this.repetitions = repetitions;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getId_routine() {
		return id_routine;
	}

	public void setId_routine(Long id_routine) {
		this.id_routine = id_routine;
	}

	public Long getId_exercise() {
		return id_exercise;
	}

	public void setId_exercise(Long id_exercise) {
		this.id_exercise = id_exercise;
	}

	public Integer getSets() {
		return sets;
	}

	public void setSets(Integer sets) {
		this.sets = sets;
	}

	public Integer getRepetitions() {
		return repetitions;
	}

	public void setRepetitions(Integer repetitions) {
		this.repetitions = repetitions;
	}

	@Override
	public String toString() {
		return "ExerciseRoutine [id=" + id + ", id_routine=" + id_routine + ", id_exercise=" + id_exercise + ", sets="
				+ sets + ", repetitions=" + repetitions + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

}
