package model;

import java.util.List;

import DAO.DAOCatégorie;
import entites.Catégorie;

public class MetierCatégorie {
	
	private DAOCatégorie daoCatégorie = new DAOCatégorie();
	
	public boolean updateCatégorie(Catégorie c) {
		return daoCatégorie.updateCatégorie(c);
	}
	
	public boolean deleteCatégorie(int idCatégorie) {
		return daoCatégorie.deleteCatégorie(idCatégorie);
	}
	
	public boolean addCatégorie(Catégorie c) {
		return daoCatégorie.addCatégorie(c);
	}
	
	public List<Catégorie> listerCatégories(){
		return daoCatégorie.listerCatégories();
	}
	
	public Catégorie getCatégorie(int idCatégorie) {
		return daoCatégorie.getCatégorie(idCatégorie);
	}
	

}
