package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import elementclasses.Ingredient;

public class IngredientDAO {
	
	public static void addIngredient(Ingredient newIngredient, Connection newConnection) {
		
		String idIngredient = "";
		String queryIngredient = "";
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		String sql = "";
		
		// Before storage check that the name is unique
		try {
			sql = "SELECT id FROM basis_ingredienten WHERE naam = \'" + newIngredient.getNaam() + "\'";
			ps = newConnection.prepareStatement(sql);
			rs = ps.executeQuery();
				  
			if (rs.next()) {
				// issue popup --- ingredient already added to database
			} else {
				queryIngredient = "INSERT INTO basis_ingredienten (" + 
					"naam," + 
					"beschrijving," + 
					"foto_naam" + 
					") VALUES ((?), (?), (?))";
				   	
				try {
					ps = newConnection.prepareStatement(queryIngredient);
					ps.setString(1, newIngredient.getNaam());
					ps.setString(2, newIngredient.getBeschrijving());
					ps.setString(3, newIngredient.getFoto_naam());
							
					// Prepare insert for ingredient values, retrieve new id.
						
					ps.executeUpdate();
	
					// Get id of new ingredient record
						
					sql = "SELECT id FROM basis_ingredienten WHERE naam = \'" + newIngredient.getNaam() + "\'";
					ps = newConnection.prepareStatement(sql);
					rs = ps.executeQuery();
						  
					while(rs.next()) {
						idIngredient = rs.getString(1);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}