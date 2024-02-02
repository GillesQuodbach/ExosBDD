package fr.fms.dao;

public interface Ecommerce<T> {

	public void addItem(T obj);
	public void removeItem(T obj);
	public void displayCart(T obj);
}
