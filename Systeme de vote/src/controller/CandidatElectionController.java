package controller;

import dao.CandidatElectionDao;
import dao.ElectionDao;
import domaine.Candidat_Election;
import domaine.Election;
import javafx.collections.ObservableList;

public class CandidatElectionController {
	
	public ObservableList<Candidat_Election> getAllCandidat(String date){
		
		ObservableList<Candidat_Election> cd= null;
			try {
				cd=CandidatElectionDao.getAllCandidat(date);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return cd;
		}
	
	public int Enregistrer(Candidat_Election el){
		int result=0;
		try {
			result=CandidatElectionDao.Enregistrer(el);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	public Candidat_Election findCandidatElection(long num) {
		Candidat_Election result=null;
		try {
			result=CandidatElectionDao.findCandidatElection(num);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	public ObservableList<String> getPartiCandidat(String date) {
		ObservableList<String> dt=null;
		try {
			dt=CandidatElectionDao.getPartiCandidat(date);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dt;
	}
	
}
