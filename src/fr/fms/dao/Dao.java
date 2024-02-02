package fr.fms.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Properties;

import fr.fms.entities.*;

public interface Dao<T> {

	public void create(T obj);

	public void read(int id);

	public boolean update(T obj);

	public boolean delete(int id);

	public void readAll();

}
