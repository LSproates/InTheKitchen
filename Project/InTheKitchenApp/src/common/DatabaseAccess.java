package common;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import common.RecipeListElement;
import elementclasses.Recipe;
import gui.MainView;

public class DatabaseAccess {
	
	public Recipe currentRecipe;
	
	public Connection startDatabase () {
		String jdbcURL = "jdbc:derby:recipes;create=true";
		Connection dbConnection = null;
		
		try {
			dbConnection = DriverManager.getConnection(jdbcURL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dbConnection;
	}
	
	public void shutdownDatabase (Connection dbConnection) {
		try {
			DriverManager.getConnection("jdbc:derby:;shutdown=true");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if (!e.getMessage().contains("Derby system shutdown") ) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<String> getKitchenNames (Connection con) {
		
		ArrayList<String> lclListOfKitchens = new ArrayList<String>();
    	
    	String selectRows = "SELECT kitchen_type from kitchen order by kitchen_type";

		try {
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(selectRows);
			
			  while(rs.next()) { 
				  lclListOfKitchens.add(rs.getString("kitchen_type"));
			  }
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return lclListOfKitchens;
	}
	
	public ArrayList<String> getMealTypes (Connection con) {
		
		ArrayList<String> lclListOfTypes = new ArrayList<String>();
    	
    	String selectRows = "SELECT meal_type from mealtype order by meal_type";

		try {
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(selectRows);
			
			  while(rs.next()) { 
				  lclListOfTypes.add(rs.getString("meal_type"));
			  }
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return lclListOfTypes;
	}
	
	public ArrayList<String> getMealThemes (Connection con) {
		
		ArrayList<String> lclListOfThemes = new ArrayList<String>();
    	
    	String selectRows = "SELECT theme from themes order by theme";

		try {
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(selectRows);
			
			  while(rs.next()) { 
				  lclListOfThemes.add(rs.getString("theme"));
			  }
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return lclListOfThemes;
	}
	
	public ArrayList<String> getTags (Connection con) {
		
//		ArrayList<String> lclListOfTags = new ArrayList<String>();
    	
//    	String selectRows = "SELECT tagnaam from taglijst order by tagnaam";

//		try {
//			Statement st = con.createStatement();
			
//			ResultSet rs = st.executeQuery(selectRows);
			
//			  while(rs.next()) { 
//				  lclListOfTags.add(rs.getString("tagnaam"));
//			  }
			 
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
//        return lclListOfTags;
		return null;
	}
	
	public DefaultListModel<RecipeListElement> getRecipeSummaryList (Connection con) {
		DefaultListModel<RecipeListElement> listOfRecipeSummaries = new DefaultListModel<RecipeListElement>();
    	
    	String selectRows = "SELECT id, naam, beschrijving, foto_naam, keuken, maal_type, thema from recept order by naam";

		try {
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(selectRows);
			
			  while(rs.next()) {
				  MainView.recipeList.addElement(new RecipeListElement(rs.getInt("id"), rs.getString("naam"), rs.getString("beschrijving"), rs.getString("foto_naam"),
						  rs.getString("keuken"), rs.getString("maal_type"), rs.getString("thema")));
				  
			  }
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return listOfRecipeSummaries;
	}
	
	public void updateKitchenTable (String newKitchen) {
		
	}
	
	public void updateMealsTable (String newMealType) {
		
	}
	
	public void updateThemesTable (String newTheme) {
		
	}
	
	public void updateTagsTable () {
		
	}
	
	public void addNewRecipe () {
		
	}
	
	public void getRecipe (Connection con, int index) {
		
    	String selectRow = "SELECT  "
    			+ "  id, "
    			+ "  naam, "
    			+ "  beschrijving, "
    			+ "  notes, "
    			+ "  aantal_personen, "
    			+ "  suiker, "
    			+ "  energie, "
    			+ "  eiwit, "
    			+ "  koolhydraten, "
    			+ "  vet, "
    			+ "  natrium, "
    			+ "  vezels, "
    			+ "  keuken, "
    			+ "  maal_type, "
    			+ "  thema, "
    			+ "  stappen, "
    			+ "  extras, "
    			+ "  source, "
    			+ "  foto_naam, "
    			+ "  prep_tijd, "
    			+ "  kook_tijd, "
    			+ "  ingredienten, "
    			+ "  tag1, "
    			+ "  tag2, "
    			+ "  tag3, "
    			+ "  tag4, "
    			+ "  tag5, "
    			+ "  tag6, "
    			+ "  tag7 FROM recept WHERE id = " + index;
    	
		try {
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(selectRow);
			
			  while(rs.next()) {
				  currentRecipe = new Recipe();
				  currentRecipe.setId(Integer.toString(rs.getInt("id")));
				  currentRecipe.setNaam(rs.getString("naam"));
				  currentRecipe.setBeschrijving(rs.getString("beschrijving"));
				  currentRecipe.setNotes(rs.getString("notes"));
				  currentRecipe.setAantal_personen(rs.getString("aantal_personen"));
				  currentRecipe.setSuiker(rs.getString("suiker"));
				  currentRecipe.setEnergie(rs.getString("energie"));
				  currentRecipe.setEiwit(rs.getString("eiwit"));
				  currentRecipe.setKoolhydraten(rs.getString("koolhydraten"));
				  currentRecipe.setVet(rs.getString("vet"));
				  currentRecipe.setNatrium(rs.getString("natrium"));
				  currentRecipe.setVezels(rs.getString("vezels"));
				  currentRecipe.setKeuken(rs.getString("keuken"));
				  currentRecipe.setMaal_type(rs.getString("maal_type"));
				  currentRecipe.setThema(rs.getString("thema"));
				  currentRecipe.setStappen(rs.getString("stappen"));
				  currentRecipe.setExtras(rs.getString("extras"));
				  currentRecipe.setSource(rs.getString("source"));
				  currentRecipe.setFoto_naam(rs.getString("foto_naam"));
				  currentRecipe.setPrep_tijd(rs.getString("prep_tijd"));
				  currentRecipe.setKook_tijd(rs.getString("kook_tijd"));
				  currentRecipe.setIngredienten(rs.getString("ingredienten"));
				  currentRecipe.setTag1(rs.getString("tag1"));
				  currentRecipe.setTag2(rs.getString("tag2"));
				  currentRecipe.setTag3(rs.getString("tag3"));
				  currentRecipe.setTag4(rs.getString("tag4"));
				  currentRecipe.setTag5(rs.getString("tag5"));
				  currentRecipe.setTag6(rs.getString("tag6"));
				  currentRecipe.setTag7(rs.getString("tag7"));
				  
			  }
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void resetDatabase () {
		
	}
}
