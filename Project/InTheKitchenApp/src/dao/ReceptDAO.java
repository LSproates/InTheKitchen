package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import elementclasses.Recipe;

public class ReceptDAO {
	
	public static void addRecipe(Recipe newRecept, Connection newConnection) {
		
		// Before storage check that the name is unique
		
		String queryRecept = "insert into recept (" + 
			"naam," + 
			"beschrijving," + 
			"notes," + 
			"aantal_personen," + 
			"suiker," + 
			"energie," + 
			"eiwit," + 
			"koolhydraten," + 
			"vet," + 
			"natrium," + 
			"vezels," + 
			"keuken," + 
			"maal_type," + 
			"thema," + 
			"source," + 
			"stappen," + 
			"ingredienten," + 
			"extras," + 
			"foto_naam," + 
			"prep_tijd," + 
			"kook_tijd," +
			"tag1," +
			"tag2," +
			"tag3," +
			"tag4," +
			"tag5," +
			"tag6," +
			"tag7" +
			") values ("
			+ "(?), (?), (?), (?), (?), (?), (?), (?), (?), (?), (?), (?), "
			+ "(?), (?), (?), (?), (?), (?), (?), (?), (?), (?), (?), (?), "
			+ "(?), (?), (?), (?)"
			+ ")";


		   	PreparedStatement ps = null;
		   	
			try {
				ps = newConnection.prepareStatement(queryRecept);
				ps.setString(1, newRecept.getNaam());				// char
				ps.setString(2, newRecept.getBeschrijving());		// char
				ps.setString(3, newRecept.getNotes());				// char
				ps.setString(4, newRecept.getAantal_personen());	// char
				ps.setString(5, newRecept.getSuiker());				// char
				ps.setString(6, newRecept.getEnergie());			// char
				ps.setString(7, newRecept.getEiwit());				// char
				ps.setString(8, newRecept.getKoolhydraten());		// char
				ps.setString(9, newRecept.getVet());				// char
				ps.setString(10, newRecept.getNatrium());			// char
				ps.setString(11, newRecept.getVezels());			// char
				ps.setString(12, newRecept.getKeuken());			// char
				ps.setString(13, newRecept.getMaal_type());			// char
				ps.setString(14, newRecept.getThema());				// char
				ps.setString(15, newRecept.getSource());			// char
				ps.setString(16, newRecept.getStappen());			// char
				ps.setString(17, newRecept.getIngredienten());		// char
				ps.setString(18, newRecept.getExtras());			// char
				ps.setString(19, newRecept.getFoto_naam());			// char
				ps.setString(20, newRecept.getPrep_tijd());			// char
				ps.setString(21, newRecept.getKook_tijd());			// char
				ps.setString(22, newRecept.getTag1());				// char
				ps.setString(23, newRecept.getTag2());				// char
				ps.setString(24, newRecept.getTag3());				// char
				ps.setString(25, newRecept.getTag4());				// char
				ps.setString(26, newRecept.getTag5());				// char
				ps.setString(27, newRecept.getTag6());				// char
				ps.setString(28, newRecept.getTag7());				// char
	    			
				// Prepare insert for recipe values, retrieve new id, store recipe parts with recipe id,
				// retrieve the ids of the recipe parts, update the recipe with the ids of the stored parts.
				
	    		ps.executeUpdate();

	    		// Get id of new recipe record
	    		
				String idRecept;
				
				String sql = "SELECT id FROM recept WHERE naam = \'" + newRecept.getNaam() + "\'";
				ps = newConnection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
			  
				while(rs.next()) {
					idRecept = rs.getString(1);
				}
				 
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}