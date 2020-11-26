package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.connect.MysqlAccess;
import domaine.Candidat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CandidatDao {

	public static ObservableList<Candidat> getAllCandidat(String date) {
		ObservableList<Candidat> dt=null;
		Connection conn=MysqlAccess.getConnection();
		String sql="select * from Candidat_Election where idElection=?";
		dt=FXCollections.observableArrayList();
		try {
			PreparedStatement preSmt1= conn.prepareStatement(sql);
			preSmt1.setString(1, date);
			ResultSet rs=preSmt1.executeQuery();
			while(rs.next()){
				dt.add(new Candidat(rs.getLong(0),rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dt;
	}
	
	public static Candidat findCandidat(long num) {
		Candidat c=null;
		Connection con=MysqlAccess.getConnection();
		String sql="select * from Personne where NumeroSocial=?";
		try {
			PreparedStatement pre=con.prepareStatement(sql);
			pre.setLong(1, num);
			ResultSet rs=pre.executeQuery();
			if(rs.next()) {
				c=new Candidat(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return c;
	}
}
