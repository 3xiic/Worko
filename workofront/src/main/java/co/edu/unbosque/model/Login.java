package co.edu.unbosque.model;

public class Login {
	
	private String id;
	private Long iduser;
	private String password;
	
	public Login() {
		// TODO Auto-generated constructor stub
	}

	public Login(Long iduser, String password) {
		this.iduser = iduser;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getIduser() {
		return iduser;
	}

	public void setIduser(Long id_user) {
		this.iduser = id_user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Login [id=" + id + ", iduser=" + iduser + ", password=" + password + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
}
