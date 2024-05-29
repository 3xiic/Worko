package co.edu.unbosque.workobackmaster.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
	@Id
	private String id;
	@Indexed(unique = true)
	private Long idusr;
	@Indexed(unique = true)
	private String username;
	private String name;
	private String email;
	private Integer age;
	private String genre;
	private Integer height;
	private Integer weight;
	private Date registrationdate;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(Long id_usr, String username, String name, String email, Integer age, String genre, Integer height,
			Integer weight, Date registrationdate) {
		this.idusr = id_usr;
		this.username = username;
		this.name = name;
		this.email = email;
		this.age = age;
		this.genre = genre;
		this.height = height;
		this.weight = weight;
		this.registrationdate = registrationdate;
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

	public Long getIdusr() {
		return idusr;
	}

	public void setIdusr(Long idusr) {
		this.idusr = idusr;
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

	public Date getRegistrationdate() {
		return registrationdate;
	}

	public void setRegistrationdate(Date registrationdate) {
		this.registrationdate = registrationdate;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", id_usr=" + idusr + ", username=" + username + ", name=" + name + ", email="
				+ email + ", age=" + age + ", genre=" + genre + ", height=" + height + ", weight=" + weight
				+ ", registration_date=" + registrationdate + "]";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
}
