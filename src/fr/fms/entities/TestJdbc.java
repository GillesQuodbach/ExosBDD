package fr.fms.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import fr.fms.dao.ArticleDao;
import fr.fms.dao.EcommerceImpl;
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

	public static void main(String[] args) throws Exception {

		String userLogin = "";
		String userPassword = "";
		boolean userFounded = false;

		Scanner scan = new Scanner(System.in);

		ArticleDao articleDao = ArticleDao.getInstance();
		UserDao userDao = UserDao.getInstance();
		EcommerceImpl ecommerce = EcommerceImpl.getInstance();

		ArrayList<Article> articlesInDb = articleDao.getArticlesList();
		ArrayList<User> usersInDb = userDao.getUsersList();

		ArrayList<Article> userCart = ecommerce.displayCart();
//System.out.println("*******" + userCart);

		System.out.println("Bienvenue sur mon App");
		while (true) {
			System.out.println("Saisissez votre login (Mickey) :");

//userLogin = scan.nextLine();
			userLogin = "Mickey";
			System.out.println("Saisissez votre password (souris):");
//userPassword = scan.nextLine();
			userPassword = "souris";
//Vérifier dans le tableau des utilisateurs si présent
			for (User user : usersInDb) {
				if ((userLogin.equals(user.getLogin()) && (userPassword.equals(user.getPassword())))) {

					userFounded = true;
				}

			}

			if (userFounded) {
				System.out.println("************************************");
				System.out.println("*****LISTE ARTICLES*****");
				System.out.println("************************************");
				for (Article a : articlesInDb) {
					System.out.println("Id: " + a.getIdArticle() + " - " + a.getDescription() + " - " + a.getBrand()
							+ " - " + a.getUnitaryPrice() + "€");
				}

			} else {
				System.out.println("Vos identifiants sont incorrects");
			}
			while (true) {
				try {
					System.out.println("************************************");
					System.out.println("1:ajouter article - 2: supprimer article - 3 voir panier - 4:payer - 5:sortir");
					int customerChoice = Integer.parseInt(scan.nextLine());

					switch (customerChoice) {

					case 1:
						System.out.println("Saisissez l'ID de l'article à ajouter à votre panier:");
						try {
							int id = Integer.parseInt(scan.nextLine());
							Article articleToAdd = articlesInDb.stream().filter(article -> article.getIdArticle() == id)
									.findFirst().orElse(null);

							userCart.add(articleToAdd);
						} catch (Exception e) {
							System.out.println("Aucun article ne correspond à votre choix");
						}

						break;
					case 2:
						System.out.println("Saisissez l'ID de l'article à retirer de votre panier:");
						try {
							int id = Integer.parseInt(scan.nextLine());
							Article articleToRemove = userCart.stream().filter(article -> article.getIdArticle() == id)
									.findFirst().orElse(null);

							userCart.remove(articleToRemove);
						} catch (Exception e) {
							System.out.println("Aucun article ne correspond à votre choix");
						}
						break;
					case 3:
						double total = 0;
						for (Article article : userCart) {
							System.out.println("Id: " + article.getIdArticle() + " - " + article.getDescription()
									+ " - " + article.getBrand() + " - " + article.getUnitaryPrice() + "€");
							total += article.getUnitaryPrice();
						}
						System.out.println("Montant total:" + total + "€");
						break;
					case 4:
						double bill = 0;
						System.out.println("---------------------------------------------");
						System.out.println("Récapitulatif de votre commande: ");
						System.out.println("---------------------------------------------");
						for (Article article : userCart) {
							System.out.println("Id: " + article.getIdArticle() + " - " + article.getDescription()
									+ " - " + article.getBrand() + " - " + article.getUnitaryPrice() + "€");
							bill += article.getUnitaryPrice();
						}
						System.out.println("---------------------------------------------");
						System.out.println("Montant total à payer : " + bill + "€");
						System.out.println("---------------------------------------------");
						break;
					case 5:
						System.out.println("A bientôt");
						break;
					default:

						System.out.println("Aucun choix ne correspond à votre demande");
						break;
					}
				} catch (Exception e) {
					System.out.println("Saisie invalide! Veuillez reessayer");
				}

			}
		}

	}
}
