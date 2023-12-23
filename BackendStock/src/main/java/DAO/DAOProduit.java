package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entites.Catégorie;
import entites.Produit;
import utilitaire.Singleton;

public class DAOProduit {

	
	
	private static final String INSERT_PRODUIT_SQL = "INSERT INTO `produit` (`id`, `description`, `prix`, `idCatégorie`) VALUES (NULL, ?, ?, ?)";
	private static final String SELECT_PRODUIT_BY_ID = "SELECT `produit`.`id` AS `id`,`produit`.`description` AS `descriptionProduit`,`prix`,`idCatégorie`,`catégorie`.`description` AS `descriptionCatégorie` FROM `produit` JOIN `catégorie` on `produit`.`idCatégorie` = `catégorie`.`id` where `produit`.`id` =?";
	private static final String SELECT_ALL_PRODUITS = "SELECT `produit`.`id` AS `id`,`produit`.`description` AS `descriptionProduit`,`prix`,`idCatégorie`,`catégorie`.`description` AS `descriptionCatégorie` FROM `produit` JOIN `catégorie` on `produit`.`idCatégorie` = `catégorie`.`id` ";
	private static final String DELETE_PRODUITS_SQL = "delete from `produit` where id = ?;";
	private static final String UPDATE_PRODUITS_SQL = "UPDATE `produit` SET `description` = ?, `prix` = ? , `idCatégorie` = ? WHERE `produit`.`id` = ?;";
	
	
	public boolean updateProduits(Produit p) {
		Connection con=Singleton.seConnecter();
		boolean res = false;
		
		try
		{
			PreparedStatement ps= con.prepareStatement(UPDATE_PRODUITS_SQL);
			
			ps.setString(1, p.getDescription());
			ps.setFloat(2, p.getPrix());
			ps.setInt(3, p.getCatégorie().getId());
			ps.setInt(4, p.getId());
			
			res = ps.executeUpdate() == 1;
			

		} catch (SQLException e) {
       	 e.printStackTrace();
        }
		
		return res;
	}
	
	
	public boolean deleteProduits(int idProd) {
		Connection con=Singleton.seConnecter();
		boolean res = false;
		
		try
		{
			PreparedStatement ps= con.prepareStatement(DELETE_PRODUITS_SQL);
			
			ps.setInt(1, idProd);
			
			res = ps.executeUpdate() == 1;
			
			
		} catch (SQLException e) {
	        
       	 e.printStackTrace();
        }
		
		return res;
	}
	
	public boolean addProduits(Produit p) {
		Connection con=Singleton.seConnecter();
		boolean res = false;
		
		try
		{
			PreparedStatement ps= con.prepareStatement(INSERT_PRODUIT_SQL);
			
			ps.setString(1, p.getDescription());
			ps.setFloat(2, p.getPrix());
			ps.setInt(3, p.getCatégorie().getId());
			
			res = ps.executeUpdate() == 1;
			

		} catch (SQLException e) {
	        
       	 e.printStackTrace();
        }
		
		return res;
	}
	
	
	public List<Produit> listerProduits() {
		Connection con=Singleton.seConnecter();
		List<Produit> l=new ArrayList<Produit>();
		
		Produit p= null;
		try
		{
			PreparedStatement ps= con.prepareStatement(SELECT_ALL_PRODUITS );
			ResultSet R=ps.executeQuery();
			while (R.next()) {
				p=new Produit();
				
	            p.setId(R.getInt("id"));
	            p.setDescription(R.getString("descriptionProduit"));
	            p.setPrix(R.getFloat("prix"));
	            Catégorie c = new Catégorie();
	            c.setId(R.getInt("idCatégorie"));
	            c.setDescription(R.getString("descriptionCatégorie"));
	            
	            p.setCatégorie(c);
	            
	            l.add(p);
	        }

		} catch (SQLException e) {
	        
       	 e.printStackTrace();
        }
		
		return l;
	}
	
	
	
	public Produit getProduit(int idProd) {
		Connection con=Singleton.seConnecter();
		Produit p = null;
		
		try
		{
			PreparedStatement ps= con.prepareStatement(SELECT_PRODUIT_BY_ID);
			
			ps.setInt(1, idProd);
			
			ResultSet R=ps.executeQuery();
			// `id`,`descriptionProduit`,`prix`,`idCatégorie`, `descriptionCatégorie` ;
			while (R.next()) {
				p=new Produit();
	            p.setId(R.getInt("id"));
	            p.setDescription(R.getString("descriptionProduit"));
	            p.setPrix(R.getFloat("prix"));
	           
	            Catégorie c = new Catégorie();
	            c.setId(R.getInt("idCatégorie"));
	            c.setDescription(R.getString("descriptionCatégorie"));
	            
	            p.setCatégorie(c);
	            
	        }

		} catch (SQLException e) {
	        
       	 e.printStackTrace();
        }
		
		return p;
	}


}