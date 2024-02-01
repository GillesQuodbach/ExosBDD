package fr.fms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import fr.fms.entities.Article;
import fr.fms.entities.CreateConfigFile;
import fr.fms.entities.TestJdbc;
import fr.fms.entities.User;

public class UserDao implements Dao<User>{
	
	private static volatile UserDao instance;


	//Constructeur privé
	private UserDao() {}
	
	//Méthode pour obtenir l'instance privée
	public static UserDao getInstance() {
		if(instance == null) {
			synchronized(UserDao.class) {
				if(instance == null) {
					instance = new UserDao();
				}
			}
		}
		return instance;
	}
	
	
	ArrayList<User> usersList = TestJdbc.getUsersWithOutDuplicate();
	@Override
	public void create(User obj) {
		try {
			Properties prop = CreateConfigFile.readPropertiesFile("src/credentials.properties");
			String url = prop.getProperty("url");
			String login = prop.getProperty("login");
			String password = prop.getProperty("password");
			String str = "INSERT INTO T_Users (Login, Password) VALUES (?,?);";
			try (Connection connection = DriverManager.getConnection(url, login, password)) {
				try (PreparedStatement ps = connection.prepareStatement(str)) {
					ps.setString(1, obj.getLogin());
					ps.setString(2, obj.getPassword());
					if (ps.executeUpdate() == 1) {
						System.out.println("insertion OK");
					}
				}
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}

	@Override
	public void read(int id) {
		try {
			Properties prop = CreateConfigFile.readPropertiesFile("src/credentials.properties");
			String url = prop.getProperty("url");
			String login = prop.getProperty("login");
			String password = prop.getProperty("password");
			String strSql = "SELECT * FROM T_Users where IdUser=" + id; // une fois connecté, réalisation d'une requête
			try (Connection connection = DriverManager.getConnection(url, login, password)) { // connection de java.sql
				try (Statement statement = connection.createStatement()) {
					try (ResultSet resultSet = statement.executeQuery(strSql)) { // resultSet de java.sql
						while (resultSet.next()) {
							int rsIdUser = resultSet.getInt(1); // soit index de 1 a n soit le nom de la colonne
							String rsLogin = resultSet.getString(2);
							String rsPassword = resultSet.getString(3);
							usersList.add((new User(rsIdUser, rsLogin, rsPassword)));
						}
					}
				}
				for (User a : usersList) {
					System.out.println(a.getIdUser() + " - " + a.getLogin() + " - " + a.getPassword());
				}
				
			}
			} catch (Exception e) {
				System.out.println("Oups, une erreur s'est produite !");
				e.printStackTrace();
			}
		
	}

	@Override
	public boolean update(User obj) {
		try {
			Properties prop = CreateConfigFile.readPropertiesFile("src/credentials.properties");
			String url = prop.getProperty("url");
			String login = prop.getProperty("login");
			String password = prop.getProperty("password");
			String str = "UPDATE T_Users SET Login=? ,Password=? where IdUser=?";
			try (Connection connection = DriverManager.getConnection(url, login, password)) {
				try (PreparedStatement ps = connection.prepareStatement(str)) {
					ps.setInt(3, obj.getIdUser());
					ps.setString(1, obj.getLogin());
					ps.setString(2, obj.getPassword());
					if (ps.executeUpdate() == 1) {
						System.out.println("update OK");
					}
				}
			}
			} catch (Exception e) {
				System.out.println("Une erreur s'est produite !");
				e.printStackTrace();
			}
			return true;
	}

	@Override
	public boolean delete(int id) {
		try {
			Properties prop = CreateConfigFile.readPropertiesFile("src/credentials.properties");
			String url = prop.getProperty("url");
			String login = prop.getProperty("login");
			String password = prop.getProperty("password");
			String str = "DELETE FROM T_Users where IdUser=?";

			try (Connection connection = DriverManager.getConnection(url, login, password)) {
				try (PreparedStatement ps = connection.prepareStatement(str)) {
					ps.setInt(1, id);
					if (ps.executeUpdate() == 1) {
						System.out.println("delete OK");
					}
				}
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
	}

	@Override
	public void readAll() {
		try {
			Properties prop = CreateConfigFile.readPropertiesFile("src/credentials.properties");
			String url = prop.getProperty("url");
			String login = prop.getProperty("login");
			String password = prop.getProperty("password");

			try (Connection connection = DriverManager.getConnection(url, login, password)) { // connection de java.sql
				String strSql = "SELECT * FROM T_Users"; // une fois connecté, réalisation d'une requête
				try (Statement statement = connection.createStatement()) {
					try (ResultSet resultSet = statement.executeQuery(strSql)) { // resultSet de java.sql
						while (resultSet.next()) {
							int rsIdUser = resultSet.getInt(1); // soit index de 1 a n soit le nom de la colonne
							String rsLogin = resultSet.getString(2);
							String rsPassword = resultSet.getString(3);

							System.out.println();
							usersList.add((new User(rsIdUser, rsLogin, rsPassword)));
						}
					}
				}
				for (User a : usersList) {
					System.out.println(a.getIdUser() + " - " + a.getLogin() + " - " + a.getPassword());
				}
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}

}
