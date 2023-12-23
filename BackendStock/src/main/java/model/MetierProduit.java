package model;


import java.util.List;

import DAO.DAOProduit;
import entites.Produit;

public class MetierProduit {
	
	private DAOProduit daoProduit = new DAOProduit();
	
	public List<Produit> lesProduits(){
			
			return daoProduit.listerProduits();
		}
	
	public Produit getProduit(int idProd){
		
		return daoProduit.getProduit(idProd);
	}

	public boolean addProduits(Produit p) {
		
		return daoProduit.addProduits(p);
		
	}
	
	
	public boolean deleteProduits(int idProd) {
		
		return daoProduit.deleteProduits(idProd);
		
	}
	
	public boolean updateProduits(Produit p) {
		
		return daoProduit.updateProduits(p);
		
	}
	

}
