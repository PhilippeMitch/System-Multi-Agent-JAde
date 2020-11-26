package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.connect.MysqlAccess;
import domaine.Election;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ElectionDao {
	
	public static int Enregistrer(Election e) {
		Connection connect = null;
		PreparedStatement preSmt=null;
		
		int result=0;
		connect=MysqlAccess.getConnection();
		String query="insert into Election (id,date,type,status) VALUES (?,?,?,?)";
		
		try {
			connect.setAutoCommit(false);
			
			preSmt = connect.prepareStatement(query);
			preSmt.setInt(1,e.getId());
			preSmt.setString(2,e.getDateElection());
			preSmt.setString(3,e.getTypeElection());
			preSmt.setString(4,e.getStatusElection());
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

	public static ObservableList<Election> getElection(){
		
		ObservableList<Election> dt;
		Connection conn=MysqlAccess.getConnection();
		dt=FXCollections.observableArrayList();
		try {
			ResultSet rs= conn.createStatement().executeQuery("select * from Election");
			while(rs.next()){
				dt.add(new Election(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dt;
	}
	
	public static ObservableList<String> getDateElection(){
		
		ObservableList<String> dt;
		Connection conn=MysqlAccess.getConnection();
		dt=FXCollections.observableArrayList();
		try {
			ResultSet rs= conn.createStatement().executeQuery("select * from Election");
			while(rs.next()){
				dt.addAll(rs.getString(2));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dt;
	}
	
}
