package controller;

import dao.CandidatDao;
import domaine.Candidat;
import javafx.collections.ObservableList;

public class CandidatController {
	
public ObservableList<Candidat> getAllCandidat(String date){
		
	ObservableList<Candidat> cd= null;
		try {
			cd=CandidatDao.getAllCandidat(date);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return cd;
	}

	public Candidat findCandidat(long num) {
		Candidat c=null;
		try {
			c=CandidatDao.findCandidat(num);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return c;
	}

}
