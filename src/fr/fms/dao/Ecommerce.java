package fr.fms.dao;

import java.util.ArrayList;
import java.util.Set;

import fr.fms.entities.Article;

public interface Ecommerce<T> {

	public void addItem(Article obj);

	public void removeItem(Article obj);

	public ArrayList<Article> displayCart();

}
