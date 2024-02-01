import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

// EXERCICE 2
public class TestJdbc {

	public static ArrayList<Article> articles = new ArrayList<Article>();

	// Méthode d'insertion
	public static void createArticle(Article obj) throws IOException {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	createArticle(new Article("Encore Souris", "Apple", 150.0, 1));
	// Méthode d'update
	public static void updateArticle(int articleId, String Description, String Brand, double UnitaryPrice,
			int IdCategory) throws IOException {
		Properties prop = CreateConfigFile.readPropertiesFile("src/credentials.properties");
		String url = prop.getProperty("url");
		String login = prop.getProperty("login");
		String password = prop.getProperty("password");
		String str = "UPDATE T_Articles SET Description=? ,Brand=?, UnitaryPrice=?, IdCategory=? where IdArticle=?";
//		for(Article article : articles) {
//			if (article.getIdArticle() == articleId) {
//				System.out.println("****************"+article);
//			}
//		}
		try (Connection connection = DriverManager.getConnection(url, login, password)) {
			try (PreparedStatement ps = connection.prepareStatement(str)) {
				ps.setString(1, Description);
				ps.setString(2, Brand);
				ps.setDouble(3, UnitaryPrice);
				ps.setInt(4, IdCategory);
				ps.setInt(5, articleId);
				if (ps.executeUpdate() == 1) {
					System.out.println("update OK");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Méthode de suppression
	public static void deleteArticle(int articleId) throws IOException {
		Properties prop = CreateConfigFile.readPropertiesFile("src/credentials.properties");
		String url = prop.getProperty("url");
		String login = prop.getProperty("login");
		String password = prop.getProperty("password");
		String str = "DELETE FROM T_Articles where IdArticle=?";

		try (Connection connection = DriverManager.getConnection(url, login, password)) {
			try (PreparedStatement ps = connection.prepareStatement(str)) {
				ps.setInt(1, articleId);
				if (ps.executeUpdate() == 1) {
					System.out.println("delete OK");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Méthode de visualisation de tout les articles
	public static void showAllArticles() throws IOException {
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
						System.out.println();
						articles.add((new Article(rsIdUser, rsDescription, rsMarque, rsPrixUnitaire, rsIdCategory)));
					}
				}
			}
			for (Article a : articles) {
				System.out.println(a.getIdArticle() + " - " + a.getDescription() + " - " + a.getBrand() + " - "
						+ a.getUnitaryPrice() + " - " + a.getIdCategory());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Méthode de visualisation de tout les articles
	public static void showArticles(int idArticle) throws IOException {
		Properties prop = CreateConfigFile.readPropertiesFile("src/credentials.properties");
		String url = prop.getProperty("url");
		String login = prop.getProperty("login");
		String password = prop.getProperty("password");
		String strSql = "SELECT * FROM T_Articles where IdArticle=" + idArticle; // une fois connecté, réalisation d'une requête
		try (Connection connection = DriverManager.getConnection(url, login, password)) { // connection de java.sql
			try (Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery(strSql)) { // resultSet de java.sql
					while (resultSet.next()) {
						int rsIdUser = resultSet.getInt(1); // soit index de 1 a n soit le nom de la colonne
						String rsDescription = resultSet.getString(2);
						String rsMarque = resultSet.getString(3);
						double rsPrixUnitaire = resultSet.getDouble(4);
						int rsIdCategory = resultSet.getInt(5);
						articles.add((new Article(rsIdUser, rsDescription, rsMarque, rsPrixUnitaire, rsIdCategory)));
					}
				}
			}
			for (Article a : articles) {
				System.out.println(a.getIdArticle() + " - " + a.getDescription() + " - " + a.getBrand() + " - "
						+ a.getUnitaryPrice() + " - " + a.getIdCategory());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {

		try {
			// Enregistre la class auprès du driver manager = charge le pilote
			Properties prop = CreateConfigFile.readPropertiesFile("src/credentials.properties");
			String driver =  prop.getProperty("driverClass");
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
//		showAllArticles();
		showArticles(12);

	}
}
