package co.edu.unbosque.workobackusers.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "login")
public class Login {
	@Id
	private String id;
	@Indexed(unique = true)
	private Long id_user;
	private String password;
	
	public Login() {
		// TODO Auto-generated constructor stub
	}

	public Login(Long id_user, String password) {
		this.id_user = id_user;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getId_user() {
		return id_user;
	}

	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Login [id=" + id + ", id_user=" + id_user + ", password=" + password + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
}
