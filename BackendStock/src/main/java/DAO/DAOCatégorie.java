package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entites.Catégorie;
import utilitaire.Singleton;


public class DAOCatégorie {
	
	
	private static final String INSERT_CATEGORIE_SQL = "INSERT INTO `catégorie` (`id`, `description`) VALUES (NULL, ?)";
	private static final String SELECT_CATEGORIE_BY_ID = "SELECT `id`,`description` FROM `catégorie` WHERE `id` =?";
	private static final String SELECT_ALL_CATEGORIES = "SELECT `id`,`description` FROM `catégorie` ";
	private static final String DELETE_CATEGORIE_SQL = "delete from `catégorie` where id = ? ";
	private static final String UPDATE_CATEGORIE_SQL = "UPDATE `catégorie` SET `description` = ? WHERE `catégorie`.`id` = ?";
	
	
	public boolean updateCatégorie(Catégorie c) {
		Connection con=Singleton.seConnecter();
		boolean res = false;
		
		try
		{
			PreparedStatement ps= con.prepareStatement(UPDATE_CATEGORIE_SQL);
			
			ps.setString(1, c.getDescription());
			ps.setInt(2, c.getId());
			
			res = ps.executeUpdate() == 1;
			
		} catch (SQLException e) {
       	 e.printStackTrace();
        }
		
		return res;
	}
	
	
	public boolean deleteCatégorie(int idCatégorie) {
		Connection con=Singleton.seConnecter();
		boolean res = false;
		
		try
		{
			PreparedStatement ps= con.prepareStatement(DELETE_CATEGORIE_SQL);
			
			ps.setInt(1, idCatégorie);
			
			res = ps.executeUpdate() == 1;
			
			
		} catch (SQLException e) {
	        
       	 e.printStackTrace();
        }
		
		return res;
	}
	
	public boolean addCatégorie(Catégorie c) {
		Connection con=Singleton.seConnecter();
		boolean res = false;
		
		try
		{
			PreparedStatement ps= con.prepareStatement(INSERT_CATEGORIE_SQL);
			
			ps.setString(1, c.getDescription());
			
			res = ps.executeUpdate() == 1;
			

		} catch (SQLException e) {
	        
       	 e.printStackTrace();
        }
		
		return res;
	}
	
	
	public List<Catégorie> listerCatégories() {
		Connection con=Singleton.seConnecter();
		List<Catégorie> l=new ArrayList<Catégorie>();
		
		Catégorie c= null;
		try
		{
			PreparedStatement ps= con.prepareStatement(SELECT_ALL_CATEGORIES);
			ResultSet R=ps.executeQuery();
			while (R.next()) {
				c = new Catégorie();
				
	            c.setId(R.getInt("id"));
	            c.setDescription(R.getString("description"));
	            
	            l.add(c);
	        }

		} catch (SQLException e) {
	        
       	 e.printStackTrace();
        }
		
		return l;
	}
	
	
	
	public Catégorie getCatégorie(int idCatégorie) {
		Connection con=Singleton.seConnecter();
		Catégorie c = null;
		
		try
		{
			PreparedStatement ps= con.prepareStatement(SELECT_CATEGORIE_BY_ID);
			
			ps.setInt(1, idCatégorie);
			
			ResultSet R=ps.executeQuery();

			while (R.next()) {
				c = new Catégorie();
	            c.setId(R.getInt("id"));
	            c.setDescription(R.getString("description"));
	        }

		} catch (SQLException e) {
	        
       	 e.printStackTrace();
        }
		
		return c;
	}


}