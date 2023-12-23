package controller;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import entites.Catégorie;
import entites.Produit;
import model.MetierCatégorie;
import model.MetierProduit;
@Path("/api")
public class ServiceStock {

	private MetierProduit metierp = new MetierProduit();

	private MetierCatégorie metierc = new MetierCatégorie();
	
	@Path("/produits")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Produit> lister(){
		return metierp.lesProduits();
	}
	
	@Path("/produit")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Produit getProduitById( @QueryParam("idProduit") int idProduit) {

        return metierp.getProduit(idProduit);
        
        //test true : http://localhost:8080/BackendStock/api/addProduit?description=TESTAjoutProduitTrue&prix=10.99&idCategorie=1
        //test false : http://localhost:8080/BackendStock/api/addProduit?description=TESTAjoutProduitFals&prix=10.99&idCategorie=2
    }
	
	@Path("/addProduit")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public boolean ajouterProduit(
    		@QueryParam("description") String description,
    		@QueryParam("prix") float prix,
    		@QueryParam("idCategorie") int idCategorie) {

        Produit p = new Produit();
        p.setDescription(description);
        p.setPrix(prix);
        Catégorie c = new Catégorie();
        c.setId(idCategorie);
        p.setCatégorie(c);
        
        return metierp.addProduits(p);
        
        //test true : http://localhost:8080/BackendStock/api/addProduit?description=TESTAjoutProduitTrue&prix=10.99&idCategorie=1
        //test false : http://localhost:8080/BackendStock/api/addProduit?description=TESTAjoutProduitFals&prix=10.99&idCategorie=2
    }
	
	
    @Path("/deleteProduit")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)  
    @Produces(MediaType.APPLICATION_JSON)
    public boolean suprimerProduit(@QueryParam("idProduit") int idProd) {
    	
        return metierp.deleteProduits(idProd);
        
      //test true : http://localhost:8080/BackendStock/api/deleteProduit?idProduit=5
      //test false: http://localhost:8080/BackendStock/api/deleteProduit?idProduit=5
    }

	@Path("/updateProduit")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public boolean updateProduit(
    		@QueryParam("idProduit") int idProduit,
    		@QueryParam("description") String description,
    		@QueryParam("prix") float prix,
    		@QueryParam("idCategorie") int idCategorie) {

        Produit p = new Produit();
        p.setId(idProduit);
        p.setDescription(description);
        p.setPrix(prix);
        Catégorie c = new Catégorie();
        c.setId(idCategorie);
        p.setCatégorie(c);
        
        return metierp.updateProduits(p);
        
        //test true : http://localhost:8080/BackendStock/api/updateProduit?idProduit=2&description=TESTUpdatProduitTrue&prix=99.33&idCategorie=2
 
    }    
    
	
	
	
	//-----------------------------------------------------------------------------------------------------------------------------
	
	@Path("/categories")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Catégorie> listerCatégories(){
		return metierc.listerCatégories();
		//http://localhost:8080/BackendStock/api/categories
	}
	
	@Path("/categorie")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Catégorie getCatégorieById( @QueryParam("idCatégorie") int idCatégorie) {

        return metierc.getCatégorie(idCatégorie);
        //http://localhost:8080/BackendStock/api/categorie?idCatégorie=3
    }
	
	@Path("/addCatégorie")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public boolean ajouterCatégorie(@QueryParam("description") String description) {

        Catégorie c = new Catégorie();
        c.setDescription(description);
        
        return metierc.addCatégorie(c);
        
        //http://localhost:8080/BackendStock/api/addCat%C3%A9gorie?description=testAdd
        
    }
	
	
    @Path("/deleteCatégorie")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)  
    @Produces(MediaType.APPLICATION_JSON)
    public boolean suprimerCatégorie(@QueryParam("idCatégorie") int idCatégorie) {
    	
        return metierc.deleteCatégorie(idCatégorie);
        //http://localhost:8080/BackendStock/api/deleteCatégorie?idCatégorie=3
        
    }

	@Path("/updateCatégorie")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public boolean updateCatégorie(@QueryParam("idCategorie") int idCategorie, @QueryParam("description") String description) {

        Catégorie c = new Catégorie();
        c.setId(idCategorie);
        c.setDescription(description);
        
        return metierc.updateCatégorie(c);
        //http://localhost:8080/BackendStock/api/updateCatégorie?idCategorie=2&description=testUpdate
    }
}

