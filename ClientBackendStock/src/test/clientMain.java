package test;

import java.io.IOException;
import java.net.URI;
import java.util.Scanner;

import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import entites.Catégorie;
import entites.Produit;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;



public class clientMain {
	
	private static final Scanner scanner = new Scanner(System.in);
	private static final Scanner scannerString = new Scanner(System.in);
	private static final Scanner scannerInt = new Scanner(System.in);
	private static final Scanner scannerFloat = new Scanner(System.in);

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
	
		
	        Client client = Client.create(new DefaultClientConfig());
	        URI uri = UriBuilder.fromUri("http://localhost:8085/BackendStock/api").build();

	        while (true) {
	            
	            afficherMenu();
	            int choix = scanner.nextInt();
	            scanner.nextLine();

	            switch (choix) {
	                case 1:
	                    addProduit(client, uri);
	                    break;
	                case 2:
	                    getAllProduit(client, uri);
	                    break;
	                case 3:
	                    updateProduit(client, uri);
	                    break;
	                case 4:
	                    getProduit(client, uri);
	                    break;
	                case 5:
	                    deleteProduit(client, uri);
	                    break;
	                case 6:
	                    addCatégorie(client, uri);
	                    break;
	                case 7:
	                    getAllCatégories(client, uri);
	                    break;
	                case 8:
	                    updateCatégorie(client, uri);
	                    break;
	                case 9:
	                    getCatégorie(client, uri);
	                    break;
	                case 10:
	                    deleteCatégorie(client, uri);
	                    break;
	                case 0:
	                    System.out.println("Sortie du programme.");
	                    System.exit(0);
	                default:
	                    System.out.println("Choix invalide. Veuillez saisir un numéro de 0 à 10.");
	                    break;
	            }
	        }
	    }

	    private static void afficherMenu() {
	        System.out.println("----------- Menu -----------");
	        System.out.println("1. Ajouter un produit");
	        System.out.println("2. Lister tous les produits");
	        System.out.println("3. Mettre à jour un produit");
	        System.out.println("4. Obtenir un produit");
	        System.out.println("5. Supprimer un produit");
	        System.out.println("6. Ajouter une catégorie");
	        System.out.println("7. Lister toutes les catégories");
	        System.out.println("8. Mettre à jour une catégorie");
	        System.out.println("9. Obtenir une catégorie");
	        System.out.println("10. Supprimer une catégorie");
	        System.out.println("0. Quitter le programme");
	        System.out.print("Veuillez saisir votre choix : ");
	    }
	    
	    
	

    private static void getAllProduit(Client client, URI uri) throws IOException {

    	ClientResponse respense = client.resource(uri).path("/produits").get(ClientResponse.class);
		
		String corprep = respense.getEntity(String.class) ;
		System.out.println(corprep);
		ObjectMapper mapper=new ObjectMapper();
		Produit[] tabprod=mapper.readValue(corprep, Produit[].class);
		
		for(Produit p:tabprod)
		{
			System.out.println(p.getDescription());
		}
    	

		}

    private static void getProduit(Client client, URI uri) throws IOException {
    	
    		System.out.print("\n-------- Entrez l'ID du produit  : ");
			int idProduit  = scannerInt.nextInt();
			
			ClientResponse response = client.resource(uri).path("/produit").queryParam("idProduit",  String.valueOf(idProduit)).get(ClientResponse.class);
			
			if (response.getStatus() == 200) {
				String corprep = response.getEntity(String.class) ;
				System.out.println(corprep);
				
				ObjectMapper mapper=new ObjectMapper();
				Produit prod=mapper.readValue(corprep, Produit.class);
				
				System.out.println(prod.getDescription());
			}
			else {
             System.out.println("Le produit avec l'ID spécifié n'existe pas.");
         }
		
    	
		}
    
    private static void addProduit(Client client, URI uri) throws IOException {

    	
    		
			System.out.print("\n-------- Entrez la description du produit : ");
			String description = scannerString.nextLine();

			System.out.print("\n-------- Entrez le prix du produit : ");
			float prix = scannerFloat.nextFloat();

			System.out.print("\n-------- Entrez l'ID de la catégorie du produit : ");
			int idCategorie = scannerInt.nextInt();
			
			
			ClientResponse response = client.resource(uri).path("/addProduit")
			        .queryParam("description", description)
			        .queryParam("prix", String.valueOf(prix))
			        .queryParam("idCategorie", String.valueOf(idCategorie))
			        .get(ClientResponse.class);

			System.out.println("Response status: " + response.getStatus());
			System.out.println("Response body: " + response.getEntity(String.class));
			

		}
		
    private static void updateProduit(Client client, URI uri) throws IOException {

        
        
			System.out.print("\n-------- Entrez l'ID du produit : ");
			int idProduit = scannerInt.nextInt();

			System.out.print("\n-------- Entrez la nouvelle description du produit : ");
			String description = scannerString.nextLine();

			System.out.print("\n-------- Entrez le nouveau prix du produit : ");
			float prix = scannerFloat.nextFloat();

			System.out.print("\n-------- Entrez l'ID de la catégorie du produit : ");
			int idCategorie = scannerInt.nextInt();
			
			
			ClientResponse response = client.resource(uri).path("/updateProduit")
					.queryParam("idProduit",  String.valueOf(idProduit))
					.queryParam("description", description)
			        .queryParam("prix", String.valueOf(prix))
			        .queryParam("idCategorie", String.valueOf(idCategorie))
			        .get(ClientResponse.class);

			System.out.println("Response status: " + response.getStatus());
			System.out.println("Response body: " + response.getEntity(String.class));
			
			
		

		}
    
    private static void deleteProduit(Client client, URI uri) throws IOException {
    	
       
			System.out.print("\n-------- Entrez l'ID du produit à supprimer : ");
			int idProduit = scannerInt.nextInt();

			ClientResponse respense = client.resource(uri).path("/deleteProduit").queryParam("idProduit",  String.valueOf(idProduit)).get(ClientResponse.class);
			
			String corprep = respense.getEntity(String.class) ;
			System.out.println(corprep);
		
		
    	
		}

    
    
    
    //------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    private static void addCatégorie(Client client, URI uri) throws IOException {
        
            System.out.print("\n-------- Entrez la description de la nouvelle catégorie : ");
            String descriptione = scannerString.nextLine();
            
            ClientResponse response = client.resource(uri).path("/addCatégorie")
                    .queryParam("description", descriptione)
                    .get(ClientResponse.class);

            System.out.println("Response status: " + response.getStatus());
            System.out.println("Response body: " + response.getEntity(String.class));
        
    }
    
    private static void  getAllCatégories(Client client, URI uri) throws IOException {
    	
        ClientResponse response = client.resource(uri).path("/categories").get(ClientResponse.class);

        String corprep = response.getEntity(String.class);
        System.out.println(corprep);

        ObjectMapper mapper = new ObjectMapper();
        Catégorie[] catégories = mapper.readValue(corprep, Catégorie[].class);

        for (Catégorie c : catégories) {
            System.out.println(c.getDescription());
        }
    }

    private static void updateCatégorie(Client client, URI uri) throws IOException {
        
            System.out.print("\n-------- Entrez l'ID de la catégorie : ");
            int idCatégorie = scannerInt.nextInt();
            
            System.out.print("\n-------- Entrez la nouvelle description de la catégorie : ");
            String description = scannerString.nextLine();

            ClientResponse response = client.resource(uri).path("/updateCatégorie")
                    .queryParam("idCategorie", String.valueOf(idCatégorie))
                    .queryParam("description", description)
                    .get(ClientResponse.class);

            System.out.println("Response status: " + response.getStatus());
            System.out.println("Response body: " + response.getEntity(String.class));
            
    }

    
    
    private static void getCatégorie(Client client, URI uri) throws IOException {
        
            System.out.print("\n-------- Entrez l'ID de la catégorie : ");
            int idCatégorie = scannerInt.nextInt();
            

            ClientResponse response = client.resource(uri).path("/categorie")
                    .queryParam("idCatégorie", String.valueOf(idCatégorie))
                    .get(ClientResponse.class);

            if (response.getStatus() == 200) {
            	
                String corprep = response.getEntity(String.class);
                System.out.println(corprep);

                ObjectMapper mapper = new ObjectMapper();
                Catégorie catégorie = mapper.readValue(corprep, Catégorie.class);

                System.out.println(catégorie.getDescription());
                
            } else {
                System.out.println("La catégorie avec l'ID spécifié n'existe pas.");
            }
    }
    
    
    
    private static void deleteCatégorie(Client client, URI uri) throws IOException {
        
            System.out.print("\n-------- Entrez l'ID de la catégorie à supprimer : ");
            int idCatégorie = scannerInt.nextInt();

            ClientResponse response = client.resource(uri).path("/deleteCatégorie")
            		.queryParam("idCatégorie", String.valueOf(idCatégorie))
            		.get(ClientResponse.class);

            System.out.println("Response status: " + response.getStatus());
            System.out.println("Response body: " + response.getEntity(String.class));
            
    }
    
}
