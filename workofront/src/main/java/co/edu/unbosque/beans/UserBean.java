package co.edu.unbosque.beans;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import co.edu.unbosque.controller.HttpClientSynchronous;
import co.edu.unbosque.model.User;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("UserBean")
@RequestScoped
public class UserBean {
	private String username = "";
	private String password = "";
	public static String iduser = "";
	
	public UserBean() {
		// TODO Auto-generated constructor stub
	}
	
	private User usrloged = new User();
	
	public String login() {
		String parameters = "userlogin?username="+username+"&password="+password;
		String response = HttpClientSynchronous.doGetAndParse("http://localhost:8085/execute/get?path="+urlEncode(parameters));
		System.out.println(response);
		if (response.equals("Usuario no encontrado!") || response.equals("Credenciales erroneas!")) {
			showStickyLogin("406", "Error al iniciar sesion");
			return "";
		} else {
			showStickyLogin("201", "Inicio de sesion realizado con exito!");
			iduser = response;
			return "home.xhtml";
		}
	}
	
	public void showStickyLogin(String code, String content) {
		if (code.equals("201")) {
			FacesContext.getCurrentInstance().addMessage("sticky-key",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Hecho", content));
		} else if (code.equals("406")) {
			FacesContext.getCurrentInstance().addMessage("sticky-key",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", content));
		}
	}
	
	private String urlEncode(String value) {
		return value == null ? "" : URLEncoder.encode(value, StandardCharsets.UTF_8);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIduser() {
		return iduser;
	}

	public void setIduser(String iduser) {
		UserBean.iduser = iduser;
	}

	public User getUsrloged() {
		return usrloged;
	}

	public void setUsrloged(User usrloged) {
		this.usrloged = usrloged;
	}
	
	
}
