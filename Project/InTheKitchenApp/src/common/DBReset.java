package common;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import elementclasses.Recipe;
import elementclasses.Ingredient;
import dao.ReceptDAO;
import dao.IngredientDAO;
import dao.TagsDAO;

public class DBReset {
	// Special class to reset db data to initial settings
	
	public static void resetDB (Connection con) {
		try {
			dropTables(con);
			createTables(con);
			populateTables(con);
			populateDefaultRecipes(con);
			loadBasisIngredienten(con);
			loadInitailTags(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void dropTables (Connection con) throws SQLException {
		// recept, kitchen, mealtype, themes, tags, basis_ingredienten
		
		Statement st = null;
		String dropTable ="";
		
		//Kichtens
		dropTable = "DROP TABLE recept";
		st = con.createStatement();
    	try {
			st.execute(dropTable);
		} catch (SQLException e) {
			if (!e.getSQLState().equals("42Y55")) {
				e.printStackTrace();
			}
		}
    	
		dropTable = "DROP TABLE kitchen";
		st = con.createStatement();
    	try {
			st.execute(dropTable);
		} catch (SQLException e) {
			if (!e.getSQLState().equals("42Y55")) {
				e.printStackTrace();
			}
		}

    	dropTable = "DROP TABLE mealtype";
		st = con.createStatement();
    	try {
			st.execute(dropTable);
		} catch (SQLException e) {
			if (!e.getSQLState().equals("42Y55")) {
				e.printStackTrace();
			}
		}
    	
		dropTable = "DROP TABLE themes";
		st = con.createStatement();
    	try {
			st.execute(dropTable);
		} catch (SQLException e) {
			if (!e.getSQLState().equals("42Y55")) {
				e.printStackTrace();
			}
		}
    	
		dropTable = "DROP TABLE basis_ingredienten";
		st = con.createStatement();
    	try {
			st.execute(dropTable);
		} catch (SQLException e) {
			if (!e.getSQLState().equals("42Y55")) {
				e.printStackTrace();
			}
		}
    	
		dropTable = "DROP TABLE taglijst";
		st = con.createStatement();
    	try {
			st.execute(dropTable);
		} catch (SQLException e) {
			if (!e.getSQLState().equals("42Y55")) {
				e.printStackTrace();
			}
		}
	}
	
	private static void createTables(Connection con) throws SQLException {
		// recept, kitchen, mealtype, themes, tags, basis_ingredienten
		
		String createTable = "";
		Statement st = null;

		st = con.createStatement();

		createTable = "CREATE TABLE kitchen (" +
				"  id INT NOT NULL GENERATED ALWAYS AS IDENTITY," +
				"  kitchen_type VARCHAR(45) NOT NULL," +
				"  PRIMARY KEY (id))";
	    
    	st.execute(createTable);
		
		createTable = "CREATE TABLE mealtype (" +
				"  id INT NOT NULL GENERATED ALWAYS AS IDENTITY," +
				"  meal_type VARCHAR(45) NOT NULL," +
				"  PRIMARY KEY (id))";
	    
    	st.execute(createTable);

		createTable = "CREATE TABLE themes (" +
				"  id INT NOT NULL GENERATED ALWAYS AS IDENTITY," +
				"  theme VARCHAR(45) NOT NULL," +
				"  PRIMARY KEY (id))";
	    
    	st.execute(createTable);

		createTable = "CREATE TABLE basis_ingredienten ("
				+ "  id int NOT NULL GENERATED ALWAYS AS IDENTITY, "
				+ "  naam varchar(150) NOT NULL UNIQUE, "
				+ "  foto_naam varchar(150) DEFAULT NULL, "
				+ "  beschrijving varchar(500) DEFAULT NULL, "
				+ "	 PRIMARY KEY (id)) ";
	    
    	st.execute(createTable);

		createTable = "CREATE TABLE taglijst (" +
				"  id INT NOT NULL GENERATED ALWAYS AS IDENTITY," +
				"  tagnaam VARCHAR(45) NOT NULL UNIQUE," +
				"  PRIMARY KEY (id))";
	    
		st.execute(createTable);
    		    
    	createTable = "CREATE TABLE recept ( "
    			+ "  id int NOT NULL GENERATED ALWAYS AS IDENTITY, "
    			+ "  naam varchar(150) NOT NULL UNIQUE, "
    			+ "  beschrijving varchar(500) DEFAULT NULL, "
    			+ "  notes varchar(200) DEFAULT NULL, "
    			+ "  aantal_personen varchar(20) DEFAULT NULL, "
    			+ "  suiker varchar(10) DEFAULT NULL, "
    			+ "  energie varchar(10) DEFAULT NULL, "
    			+ "  eiwit varchar(10) DEFAULT NULL, "
    			+ "  koolhydraten varchar(10) DEFAULT NULL, "
    			+ "  vet varchar(10) DEFAULT NULL, "
    			+ "  natrium varchar(10) DEFAULT NULL, "
    			+ "  vezels varchar(10) DEFAULT NULL, "
    			+ "  keuken varchar(45) DEFAULT NULL, "
    			+ "  maal_type varchar(45) DEFAULT NULL, "
    			+ "  thema varchar(45) DEFAULT NULL, "
    			+ "  stappen varchar(2100) DEFAULT NULL, "
    			+ "  extras varchar(250) DEFAULT NULL, "
    			+ "  source varchar(100) DEFAULT NULL, "
    			+ "  foto_naam varchar(150) DEFAULT NULL, "
    			+ "  prep_tijd varchar(10) DEFAULT NULL, "
    			+ "  kook_tijd varchar(10) DEFAULT NULL, "
    			+ "  ingredienten varchar(2100) DEFAULT NULL, "
    			+ "  tag1 varchar(50) DEFAULT NULL, "
    			+ "  tag2 varchar(50) DEFAULT NULL, "
    			+ "  tag3 varchar(50) DEFAULT NULL, "
    			+ "  tag4 varchar(50) DEFAULT NULL, "
    			+ "  tag5 varchar(50) DEFAULT NULL, "
    			+ "  tag6 varchar(50) DEFAULT NULL, "
    			+ "  tag7 varchar(50) DEFAULT NULL, "
    			+ "  tagstring varchar(100) DEFAULT NULL, "
				+ "  PRIMARY KEY (id))";
	    
    	st.execute(createTable);
	    
	}
	
	private static void populateTables (Connection con) throws SQLException {
		
		Statement st = null;
		String sqlRequest = "";
		
		//Kichtens
    	sqlRequest = "INSERT INTO kitchen (kitchen_type) values " +
    			"(''), ('Nederlands'), ('Frans'), ('Engels'), ('Chinees'), ('Japans'), ('Italiaans'), ('Duits'), ('Indonesisch'), ('Chinees-Indisch'), "
    			+ "('Indiers'), ('Ander Aziatisch'), ('Turks-Arabisch'), ('Scandinavisch'), ('Oost Europees'), ('Americaans'), ('Zuid Americaans'), "
    			+ "('Algemeen'), ('Overige'), ('+ Add new')";

		st = con.createStatement();
	    
        st.execute(sqlRequest);
		
		//MealType
    	sqlRequest = "INSERT INTO mealtype (meal_type) values " +
    			"(''), ('Gemengd'), ('Bief'), ('Varkensvlees'), ('Kip'), ('Kaas'), ('Pasta'), ('Ovenschotel'), ('Rijst'), ('Bami-Noodles'), ('Sushi'), "
    			+ "('Aardappel'), ('Groenten'), ('Eieren'), ('Koekjes'), ('Salade'), ('Sauzen'), ('Soepen'), ('Taarten en cakes'), "
    			+ "('Drankjes'), ('Brood'), ('Smoothies'), ('Tussendoortje'), ('Bijgerecht'), ('Wittekool'), "
    			+ "('Hartige snacks'), ('Zoete snacks'), ('Gezond'), ('Vis'), ('Dressing'), ('Chocolade'), ('Fruit'), ('Noten'), "
    			+ "('Eend'), ('+ Add new')";

		st = con.createStatement();
	    
        st.execute(sqlRequest);
		
		//themes
    	sqlRequest = "INSERT INTO themes (theme) VALUES (''), ('Algemeen'), ('Kerst'), ('Pasen'), ('Verjaardag'), ('Sinterklaas'), ('BBQ'), ('Tussendoortje'), "
    			+ "('Lente'), ('Zomer'), ('Herfst'), ('Winter'), ('Daags'), ('Dieeet'), ('Ontbijt'), ('Middag maaltijd'), ('Avond maaltijd'), ('+ Add new')";

		st = con.createStatement();
	    
        st.execute(sqlRequest);
	}
	
	private static void populateDefaultRecipes (Connection con) {
		// read from default recipes file
		// if file not present --- no default recipes
		
		Recipe recipe = new Recipe();
		
		  try {
		      File inputFile = new File("defaultrecipes.txt");
			  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			  Document doc = dBuilder.parse(inputFile);
			  doc.getDocumentElement().normalize();
			  NodeList nList = doc.getElementsByTagName("recipe");
			  
			  for (int i = 0; i < nList.getLength(); i++) {
			     Node nNode = nList.item(i);
			     
			     if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			        Element eElement = (Element) nNode;
			        recipe.setNaam(eElement.getElementsByTagName("title").item(0).getTextContent());
			        recipe.setBeschrijving(eElement.getElementsByTagName("description").item(0).getTextContent());
			        recipe.setNotes(eElement.getElementsByTagName("notes").item(0).getTextContent());
			        recipe.setFoto_naam(eElement.getElementsByTagName("photoname").item(0).getTextContent());
			        recipe.setPrep_tijd(eElement.getElementsByTagName("preptime").item(0).getTextContent());
			        recipe.setKook_tijd(eElement.getElementsByTagName("cookingtime").item(0).getTextContent());
			        recipe.setAantal_personen(eElement.getElementsByTagName("persons").item(0).getTextContent());
			        recipe.setSuiker(eElement.getElementsByTagName("sugar").item(0).getTextContent());
			        recipe.setEnergie(eElement.getElementsByTagName("energy").item(0).getTextContent());
			        recipe.setEiwit(eElement.getElementsByTagName("protein").item(0).getTextContent());
			        recipe.setKoolhydraten(eElement.getElementsByTagName("carbohydrates").item(0).getTextContent());
			        recipe.setVet(eElement.getElementsByTagName("fat").item(0).getTextContent());
			        recipe.setNatrium(eElement.getElementsByTagName("natrium").item(0).getTextContent());
			        recipe.setVezels(eElement.getElementsByTagName("fibres").item(0).getTextContent());
			        recipe.setKeuken(eElement.getElementsByTagName("kitchen").item(0).getTextContent());
			        recipe.setMaal_type(eElement.getElementsByTagName("mealtype").item(0).getTextContent());
			        recipe.setThema(eElement.getElementsByTagName("theme").item(0).getTextContent());
			        recipe.setStappen(eElement.getElementsByTagName("steps").item(0).getTextContent());
			        recipe.setIngredienten(eElement.getElementsByTagName("ingredients").item(0).getTextContent());
			        recipe.setSource(eElement.getElementsByTagName("source").item(0).getTextContent());
			        
			        ReceptDAO.addRecipe(recipe, con);
		         }
		      }
		   } catch (Exception e) {
		      e.printStackTrace();
		   }
	}
	
	private static void loadBasisIngredienten (Connection con) {
		// read from basis ingredients file
		// if no file then no basis ingredients
		
		Ingredient ingredient = new Ingredient();
		
		  try {
		      File inputFile = new File("basisingredients.txt");
			  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			  Document doc = dBuilder.parse(inputFile);
			  doc.getDocumentElement().normalize();
			  NodeList nList = doc.getElementsByTagName("ingredient");
			  
			  for (int i = 0; i < nList.getLength(); i++) {
			     Node nNode = nList.item(i);
			     
			     if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			        Element eElement = (Element) nNode;
			        ingredient.setNaam(eElement.getElementsByTagName("title").item(0).getTextContent());
			        ingredient.setBeschrijving(eElement.getElementsByTagName("description").item(0).getTextContent());
			        ingredient.setFoto_naam(eElement.getElementsByTagName("photoname").item(0).getTextContent());
			        
			        IngredientDAO.addIngredient(ingredient, con);
		         }
		      }
		   } catch (Exception e) {
		      e.printStackTrace();
		   }
		
	}
	
	private static void loadInitailTags (Connection con) {
		String initialTag = "";
		
		  try {
		      File inputFile = new File("initialtags.txt");
			  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			  Document doc = dBuilder.parse(inputFile);
			  doc.getDocumentElement().normalize();
			  NodeList nList = doc.getElementsByTagName("tag");
			  
			  for (int i = 0; i < nList.getLength(); i++) {
			     Node nNode = nList.item(i);
			     
			     if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			        Element eElement = (Element) nNode;
			        initialTag = eElement.getElementsByTagName("naam").item(0).getTextContent();
			        System.out.println(initialTag);
			        
			        TagsDAO.addNewTag(initialTag, con);
		         }
		      }
		   } catch (Exception e) {
		      e.printStackTrace();
		   }
	
	}


}
