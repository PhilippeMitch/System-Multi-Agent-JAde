package controller;

import dao.PersonneDao;
import domaine.Personne;
import javafx.collections.ObservableList;

public class PersonneController {
	public static ObservableList<Personne> getAllPersonne(long num) {
		ObservableList<Personne> dt=null;
		try {
			dt=PersonneDao.getAllPersonne(num);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dt;
	}
}
