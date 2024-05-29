package co.edu.unbosque.model;

public class Exercise {
	
	private String id;
	private Long idexercise;
	private String name;
	private String difficulty;
	private String equipment;
	private String focus;
	private String url;

	public Exercise() {
		// TODO Auto-generated constructor stub
	}

	public Exercise(Long idexercise, String name, String difficulty, String equipment, String focus, String url) {
		super();
		this.idexercise = idexercise;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getIdexercise() {
		return idexercise;
	}

	public void setIdexercise(Long id_exercise) {
		this.idexercise = id_exercise;
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

