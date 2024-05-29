package co.edu.unbosque.workobacktraining.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "trainings")
public class Training {
	
	@Id
	private String id;
	@Indexed(unique = true)
	private Long id_training;
	@Indexed(unique = true)
	private Long id_user;
	@Indexed(unique = true)
	private Long id_routine;
	private Date date;
	private Integer duration;
	
	public Training() {
		// TODO Auto-generated constructor stub
	}

	public Training(Long id_training, Long id_user, Long id_routine, Date date, Integer duration) {
		this.id_training = id_training;
		this.id_user = id_user;
		this.id_routine = id_routine;
		this.date = date;
		this.duration = duration;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getId_training() {
		return id_training;
	}

	public void setId_training(Long id_training) {
		this.id_training = id_training;
	}

	public Long getId_user() {
		return id_user;
	}

	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

	public Long getId_routine() {
		return id_routine;
	}

	public void setId_routine(Long id_routine) {
		this.id_routine = id_routine;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Training [id=" + id + ", id_training=" + id_training + ", id_user=" + id_user + ", id_routine="
				+ id_routine + ", date=" + date + ", duration=" + duration + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
}
