package co.edu.unbosque.workobacketl.model;

public class ExerciseETL {
	
	private String id;
	private Long id_exercise;
	private String name;
	private String difficulty;
	private String equipment;
	private String focus;
	private String url;


	public ExerciseETL() {
		// TODO Auto-generated constructor stub
	}


	public ExerciseETL(Long id_exercise, String name, String difficulty, String equipment, String focus, String url) {
		super();
		this.id_exercise = id_exercise;
		this.name = name;
		this.difficulty = difficulty;
		this.equipment = equipment;
		this.focus = focus;
		this.url = url;
	}



	@Override
	public String toString() {
		return "Exercise [id=" + id + ", name=" + name + ", difficulty=" + difficulty + ", equipment=" + equipment
				+ ", focus=" + focus + ", url=" + url + "]";
	}


	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Long getId_exercise() {
		return id_exercise;
	}


	public void setId_exercise(Long id_exercise) {
		this.id_exercise = id_exercise;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDifficulty() {
		return difficulty;
	}


	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}


	public String getEquipment() {
		return equipment;
	}


	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}


	public String getFocus() {
		return focus;
	}


	public void setFocus(String focus) {
		this.focus = focus;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}

}
