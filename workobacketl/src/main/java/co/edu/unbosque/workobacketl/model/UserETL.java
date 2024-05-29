package co.edu.unbosque.workobacketl.model;

import java.util.Date;



public class UserETL {
	private String id;
	private Long id_usr;
	private String username;
	private String name;
	private String email;
	private Integer age;
	private String genre;
	private Integer height;
	private Integer weight;
	private Date registration_date;
	
	public UserETL() {
		// TODO Auto-generated constructor stub
	}

	public UserETL(Long id_usr, String username, String name, String email, Integer age, String genre, Integer height,
			Integer weight, Date registration_date) {
		this.id_usr = id_usr;
		this.username = username;
		this.name = name;
		this.email = email;
		this.age = age;
		this.genre = genre;
		this.height = height;
		this.weight = weight;
		this.registration_date = registration_date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId_usr() {
		return id_usr;
	}

	public void setId_usr(Long id_usr) {
		this.id_usr = id_usr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Date getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", id_usr=" + id_usr + ", username=" + username + ", name=" + name + ", email="
				+ email + ", age=" + age + ", genre=" + genre + ", height=" + height + ", weight=" + weight
				+ ", registration_date=" + registration_date + "]";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
}
