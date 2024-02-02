package fr.fms.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;
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
			.collect(Collectors.toSet()).stream().collect(Collectors.toList());

	public static ArrayList<User> usersWithOutDuplicate = (ArrayList<User>) users.stream().collect(Collectors.toSet())
			.stream().collect(Collectors.toList());

	/**
	 * Méthode de récupération des listes
	 */
	public static ArrayList<Article> getArticles() {
		return articles;
	}

	public static ArrayList<User> getUsers() {
		return users;
	}

	public static ArrayList<User> getUsersWithOutDuplicate() {
		return usersWithOutDuplicate;
	}

	public static ArrayList<Article> getArticlesWithOutDuplicate() {
		return articlesWithOutDuplicate;
	}

	public static void main(String[] args) throws Exception {
		String userLogin = "";
		String userPassword = "";
		boolean userFounded = false;

		Scanner scan = new Scanner(System.in);

		ArticleDao articleDao = ArticleDao.getInstance();
		UserDao userDao = UserDao.getInstance();

		// Rempli la tableau de users
		userDao.readAll();
		
		// Rempli le tableau d'articles
		articleDao.readAll();
		ArrayList<User> allUsers = getUsersWithOutDuplicate();

System.out.println("Bienvenue sur mon App");
System.out.println("Saisissez votre login:");
userLogin = scan.nextLine();
//userLogin = "Pierro";
System.out.println("Saisissez votre password:");
userPassword = scan.nextLine();
//userPassword = "Caniche";
//Vérifier dans le tableau des utilisateurs si présent
for (User user : allUsers) {
	if((userLogin.equals(user.getLogin())&&(userPassword.equals(user.getPassword()))))
	userFounded = true;
}

if (userFounded) {
	System.out.println("*****LISTE ARTICLES*****");
	articleDao.readAll();
}else {
	System.out.println("Vos identifiants sont incorrects");
}

	}
}
