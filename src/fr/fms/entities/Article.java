package fr.fms.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Article {

	// Mise en place Singleton
	private int IdArticle;
	private String Description;
	private String Brand;
	private double UnitaryPrice;
	private int IdCategory;

	public Article(int IdArticle, String Description, String Brand, double UnitaryPrice, int IdCategory) {
		this.IdArticle = IdArticle;
		this.Description = Description;
		this.Brand = Brand;
		this.UnitaryPrice = UnitaryPrice;
		this.IdCategory = IdCategory;
	}

	public Article(String Description, String Brand, double UnitaryPrice, int IdCategory) {
		this.Description = Description;
		this.Brand = Brand;
		this.UnitaryPrice = UnitaryPrice;
		this.IdCategory = IdCategory;
	}

	public int getIdArticle() {
		return IdArticle;
	}

	public void setIdArticle(int idArticle) {
		IdArticle = idArticle;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getBrand() {
		return Brand;
	}

	public void setBrand(String brand) {
		Brand = brand;
	}

	public double getUnitaryPrice() {
		return UnitaryPrice;
	}

	public void setUnitaryPrice(double unitaryPrice) {
		UnitaryPrice = unitaryPrice;
	}

	public int getIdCategory() {
		return IdCategory;
	}

	public void setIdCategory(int IdCategory) {
		this.IdCategory = IdCategory;
	}

	@Override
	public String toString() {
		return "Article [IdArticle=" + IdArticle + ", Description=" + Description + ", Brand=" + Brand
				+ ", UnitaryPrice=" + UnitaryPrice + ", IdCategory=" + IdCategory + "]";
	}

}
