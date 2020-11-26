package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.connect.MysqlAccess;
import domaine.Personne;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PersonneDao {
	
	public static ObservableList<Personne> getAllPersonne(long num) {
		ObservableList<Personne> dt=null;
		Connection conn=MysqlAccess.getConnection();
		String sql="select * from Personne where NumeroSocial=?";
		dt=FXCollections.observableArrayList();
		try {
			PreparedStatement preSmt1= conn.prepareStatement(sql);
			preSmt1.setLong(1, num);
			ResultSet rs=preSmt1.executeQuery();
			while(rs.next()){
				dt.add(new Personne(rs.getString(2),rs.getString(3)));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dt;
	}
}
