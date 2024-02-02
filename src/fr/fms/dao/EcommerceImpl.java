package fr.fms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import fr.fms.entities.Article;
import fr.fms.entities.CreateConfigFile;
import fr.fms.entities.TestJdbc;

public class EcommerceImpl implements Ecommerce{
	
	private ArrayList<Article> cart;

	private static volatile EcommerceImpl instance;
	
	private EcommerceImpl() {
		this.cart = new ArrayList<>();
	};
	
public static EcommerceImpl getInstance() {
	if(instance == null) {
		synchronized(EcommerceImpl.class) {
			if(instance == null) {
				instance = new EcommerceImpl();		
				}
		}
	}
	return instance;
}
	
	@Override
	public void addItem(Article obj) {
		cart.add(obj);
	}

	@Override
	public void removeItem(Article obj) {
		cart.remove(obj);
	}

	@Override
	public ArrayList<Article> displayCart() {
		return cart;
	}

}
