package co.edu.unbosque.workobackexercises.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Exercises")
public class Exercise {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_exercise;
private String name;
private String difficulty;
private String equipment;
private String focus;
private String url;


public Exercise() {
	
}


public Exercise(String name, String difficulty, String equipment, String focus, String url) {
	super();
	this.name = name;
	this.difficulty = difficulty;
	this.equipment = equipment;
	this.focus = focus;
	this.url = url;
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
