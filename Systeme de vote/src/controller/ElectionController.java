package controller;

import dao.ElectionDao;
import domaine.Election;
import javafx.collections.ObservableList;

public class ElectionController {
	
	public int EnregistrerElection(Election el){
		int result=0;
		try {
			result=ElectionDao.Enregistrer(el);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	public ObservableList<Election> getAllElection(){
		
		ObservableList<Election>  dt= null;
		try {
			dt=ElectionDao.getElection();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return dt;
	}
	public ObservableList<String> getDateElection(){
		
		ObservableList<String>  dt= null;
		try {
			dt=ElectionDao.getDateElection();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return dt;
	}
}
