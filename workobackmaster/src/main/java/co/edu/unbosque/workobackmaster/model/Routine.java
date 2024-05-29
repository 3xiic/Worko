package co.edu.unbosque.workobackmaster.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "routines")
public class Routine {
	
	@Id
	private String id;
	@Indexed(unique = true)
	private Long idroutine;
	private String name;
	private String description;
	private String difficulty;
	
	public Routine() {
		// TODO Auto-generated constructor stub
	}

	public Routine(Long idroutine, String name, String description, String difficulty) {
		this.idroutine = idroutine;
		this.name = name;
		this.description = description;
		this.difficulty = difficulty;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	@Override
	public String toString() {
		return "Routine [id=" + id + ", id_routine=" + idroutine + ", name=" + name + ", description=" + description
				+ ", difficulty=" + difficulty + "]";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
}
