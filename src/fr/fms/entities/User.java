package fr.fms.entities;

public class User {
	
	private int IdUser;
	private String Login;
	private String Password;

	
	public User(int IdUser, String Login, String Password) {
		this.IdUser = IdUser;
		this.Login = Login;
		this.Password = Password;
	}

	public User(String Login, String Password) {
		this.Login = Login;
		this.Password = Password;
	}

	public int getIdUser() {
		return IdUser;
	}

	public void setIdUser(int idUser) {
		IdUser = idUser;
	}

	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

}
