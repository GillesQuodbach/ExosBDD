package fr.fms.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import fr.fms.entities.TestJdbc;
import fr.fms.entities.User;
import fr.fms.entities.Article;
import fr.fms.entities.CreateConfigFile;


public class ArticleDao implements Dao<Article> {

	private static volatile ArticleDao instance;
	private ArrayList<Article> articlesList = new ArrayList<>();


	//Constructeur privé
	private ArticleDao() {
		retrieveArticlesListFromDataBase();
	}
	
	//Méthode pour obtenir l'instance privée
	public static ArticleDao getInstance() {
		if(instance == null) {
			synchronized(ArticleDao.class) {
				if(instance == null) {
					instance = new ArticleDao();
				}
			}
		}
		return instance;
	}
	
//	ArrayList<Article> articlesList = TestJdbc.getArticlesWithOutDuplicate();
	
	
	
	public void retrieveArticlesListFromDataBase() {
		try {
			Properties prop = CreateConfigFile.readPropertiesFile("src/credentials.properties");
			String url = prop.getProperty("url");
			String login = prop.getProperty("login");
			String password = prop.getProperty("password");

			try (Connection connection = DriverManager.getConnection(url, login, password)) { // connection de java.sql
				String strSql = "SELECT * FROM T_Articles"; // une fois connecté, réalisation d'une requête
				try (Statement statement = connection.createStatement()) {
					try (ResultSet resultSet = statement.executeQuery(strSql)) { // resultSet de java.sql
						while (resultSet.next()) {
							int rsIdUser = resultSet.getInt(1); // soit index de 1 a n soit le nom de la colonne
							String rsDescription = resultSet.getString(2);
							String rsMarque = resultSet.getString(3);
							double rsPrixUnitaire = resultSet.getDouble(4);
							int rsIdCategory = resultSet.getInt(5);
							articlesList.add((new Article(rsIdUser, rsDescription, rsMarque, rsPrixUnitaire, rsIdCategory)));
						}
					}
				}
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public ArrayList<Article> getArticlesList(){
		return articlesList;
	}
	
	@Override
	public void create(Article obj) {
		try {
		Properties prop = CreateConfigFile.readPropertiesFile("src/credentials.properties");
		String url = prop.getProperty("url");
		String login = prop.getProperty("login");
		String password = prop.getProperty("password");
		String str = "INSERT INTO T_Articles (Description, Brand, UnitaryPrice, IdCategory) VALUES (?,?,?,?);";
		try (Connection connection = DriverManager.getConnection(url, login, password)) {
			try (PreparedStatement ps = connection.prepareStatement(str)) {
				ps.setString(1, obj.getDescription());
				ps.setString(2, obj.getBrand());
				ps.setDouble(3, obj.getUnitaryPrice());
				ps.setInt(4, obj.getIdCategory());
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
		String strSql = "SELECT * FROM T_Articles where IdArticle=" + id; // une fois connecté, réalisation d'une requête
		try (Connection connection = DriverManager.getConnection(url, login, password)) { // connection de java.sql
			try (Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery(strSql)) { // resultSet de java.sql
					while (resultSet.next()) {
						int rsIdUser = resultSet.getInt(1); // soit index de 1 a n soit le nom de la colonne
						String rsDescription = resultSet.getString(2);
						String rsMarque = resultSet.getString(3);
						double rsPrixUnitaire = resultSet.getDouble(4);
						int rsIdCategory = resultSet.getInt(5);
						articlesList.add((new Article(rsIdUser, rsDescription, rsMarque, rsPrixUnitaire, rsIdCategory)));
					}
				}
			}
			for (Article a : articlesList) {
				System.out.println(a.getIdArticle() + " - " + a.getDescription() + " - " + a.getBrand() + " - "
						+ a.getUnitaryPrice() + " - " + a.getIdCategory());
			}
			
		}
		} catch (Exception e) {
			System.out.println("Oups, une erreur s'est produite !");
			e.printStackTrace();
		}

	}

	@Override
	public boolean update(Article obj) {
		try {
		Properties prop = CreateConfigFile.readPropertiesFile("src/credentials.properties");
		String url = prop.getProperty("url");
		String login = prop.getProperty("login");
		String password = prop.getProperty("password");
		String str = "UPDATE T_Articles SET Description=? ,Brand=?, UnitaryPrice=?, IdCategory=? where IdArticle=?";
		try (Connection connection = DriverManager.getConnection(url, login, password)) {
			try (PreparedStatement ps = connection.prepareStatement(str)) {
				ps.setString(1, obj.getDescription());
				ps.setString(2, obj.getBrand());
				ps.setDouble(3, obj.getUnitaryPrice());
				ps.setInt(4, obj.getIdCategory());
				ps.setInt(5, obj.getIdArticle());
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
		String str = "DELETE FROM T_Articles where IdArticle=?";

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
			String strSql = "SELECT * FROM T_Articles"; // une fois connecté, réalisation d'une requête
			try (Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery(strSql)) { // resultSet de java.sql
					while (resultSet.next()) {
						int rsIdUser = resultSet.getInt(1); // soit index de 1 a n soit le nom de la colonne
						String rsDescription = resultSet.getString(2);
						String rsMarque = resultSet.getString(3);
						double rsPrixUnitaire = resultSet.getDouble(4);
						int rsIdCategory = resultSet.getInt(5);
						articlesList.add((new Article(rsIdUser, rsDescription, rsMarque, rsPrixUnitaire, rsIdCategory)));
					}
				}
			}
			for (Article a : articlesList) {
				System.out.println(a.getIdArticle() + " - " + a.getDescription() + " - " + a.getBrand() + " - "
						+ a.getUnitaryPrice() + " - " + a.getIdCategory());
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
