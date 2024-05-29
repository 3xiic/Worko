package co.edu.unbosque.model;

public class ExerciseRoutine {
	
	private String id;
	private Long idroutine;
	private Long idexercise;
	private Integer sets;
	private Integer repetitions;
	
	public ExerciseRoutine() {
		// TODO Auto-generated constructor stub
	}

	public ExerciseRoutine(Long idroutine, Long idexercise, Integer sets, Integer repetitions) {
		this.idroutine = idroutine;
		this.idexercise = idexercise;
		this.sets = sets;
		this.repetitions = repetitions;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getIdroutine() {
		return idroutine;
	}

	public void setIdroutine(Long idroutine) {
		this.idroutine = idroutine;
	}

	public Long getIdexercise() {
		return idexercise;
	}

	public void setIdexercise(Long idexercise) {
		this.idexercise = idexercise;
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
		return "ExerciseRoutine [id=" + id + ", id_routine=" + idroutine + ", id_exercise=" + idexercise + ", sets="
				+ sets + ", repetitions=" + repetitions + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

}
