package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.connect.MysqlAccess;
import domaine.Candidat;
import domaine.Candidat_Election;
import domaine.Election;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CandidatElectionDao {
	
	public static int Enregistrer(Candidat_Election e) {
		Connection connect = null;
		PreparedStatement preSmt=null;
		
		int result=0;
		connect=MysqlAccess.getConnection();
		String query="insert into Candidat_Election (NumeroSocialCandidat,dateElection,nombreVoix,PartiCandidat,PosteCandidat) VALUES (?,?,?,?,?)";
		
		try {
			connect.setAutoCommit(false);
			
			preSmt = connect.prepareStatement(query);
			preSmt.setLong(1,e.getNumSocial());
			preSmt.setString(2,e.getDateElection());
			preSmt.setLong(3,e.getNombreVoix());
			preSmt.setString(4,e.getPartiPolitique());
			preSmt.setString(5,e.getPosteCandidat());
			preSmt.executeUpdate();
			
			connect.commit();
			result=1;
			
		} catch (SQLException es) {
			// TODO Auto-generated catch block
			try {
				connect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}
			System.out.println(es.getMessage());
		}
		
		finally{
			try {
				if (preSmt != null) {
					preSmt.close();
				}

				if (connect != null) {
					connect.close();
				}

			} catch (Exception es) {
				// TODO Auto-generated catch block
				es.printStackTrace();
			}
		}
		
		return result;
	
	}
	
	public static ObservableList<Candidat_Election> getAllCandidat(String date) {
		// TODO Auto-generated method stub

		ObservableList<Candidat_Election> dt=null;
		Connection conn=MysqlAccess.getConnection();
		String sql="select * from Candidat_Election where dateElection=?";
		dt=FXCollections.observableArrayList();
		try {
			PreparedStatement preSmt1= conn.prepareStatement(sql);
			preSmt1.setString(1, date);
			ResultSet rs=preSmt1.executeQuery();
			while(rs.next()){
				dt.add(new Candidat_Election(rs.getLong(0),rs.getString(3),rs.getString(4)));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dt;
	}

	public static ObservableList<String> getPartiCandidat(String date) {
		// TODO Auto-generated method stub

		ObservableList<String> dt=null;
		Connection conn=MysqlAccess.getConnection();
		String sql="select * from Candidat_Election where dateElection=?";
		dt=FXCollections.observableArrayList();
		try {
			PreparedStatement preSmt1= conn.prepareStatement(sql);
			preSmt1.setString(1, date);
			ResultSet rs=preSmt1.executeQuery();
			while(rs.next()){
				dt.addAll(rs.getString(4));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dt;
	}
	
	public static Candidat_Election findCandidatElection(long num) {
		Candidat_Election c=null;
		Connection con=MysqlAccess.getConnection();
		String sql="select * from Candidat_Election where NumeroSocialCandidat=?";
		try {
			PreparedStatement pre=con.prepareStatement(sql);
			pre.setLong(1, num);
			ResultSet rs=pre.executeQuery();
			if(rs.next()) {
				c=new Candidat_Election(rs.getLong(1),rs.getString(4),rs.getString(5));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return c;
	}
}
