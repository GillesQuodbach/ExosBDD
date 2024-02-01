package fr.fms.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Collectors;

import fr.fms.dao.ArticleDao;
import fr.fms.dao.UserDao;
import fr.fms.entities.Article;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

// EXERCICE 2
public class TestJdbc {

	/**
	 * Liste de base
	 */
	public static ArrayList<Article> articles = new ArrayList<Article>();
	public static ArrayList<User> users = new ArrayList<User>();
	
	
	/**
	 * Liste sans doublons
	 */
	public static ArrayList<Article> articlesWithOutDuplicate = (ArrayList<Article>) articles.stream()
			.collect(Collectors.toSet())
			.stream()
			.collect(Collectors.toList());	

	public static ArrayList<User> usersWithOutDuplicate = (ArrayList<User>) users.stream()
			.collect(Collectors.toSet())
			.stream()
			.collect(Collectors.toList());	

	/**
	 * Méthode de récupération des listes
	 */
	public static ArrayList<Article> getArticles() {
		return articles;
	}

	public static ArrayList<User> getUsers() {
		return users;
	}
	
	public static ArrayList<User> getUsersWithOutDuplicate(){
		return usersWithOutDuplicate;
	}
	public static ArrayList<Article> getArticlesWithOutDuplicate(){
		return articlesWithOutDuplicate;
	}
	public static void main(String[] args) throws Exception {
		

		ArticleDao articleDao = new ArticleDao();

		UserDao userDao = new UserDao();

//		showAllArticles();
//	articleDao.update(new Article(26, "PalWorld", "Chinois", 150, 3));
//	articleDao.read(30);
//		articleDao.delete(24);
//	userDao.create(new User("Pierre", "Loup"));
// userDao.update(new User(4, "Pierro", "Caniche"));
//userDao.read(4);
//userDao.delete(6);
userDao.readAll();


}
}
