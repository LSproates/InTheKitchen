package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import elementclasses.Recipe;

public class TagsDAO {
		
	public static void addTags(Recipe recipe, Connection newConnection) {
		    	
		try {
		   	PreparedStatement ps = null;
			String queryTags = "insert into taglijst (tagnaam) values (?)";
			
			ps = newConnection.prepareStatement(queryTags);
			
			ps.setString(1, recipe.getTag1());
    		ps.executeUpdate();
		} catch (SQLException e) {
	        if(!e.getSQLState().equals("23505")) {
	        	// Ignore error on duplicate records, print stacktrace on other SQL errors
	        	e.printStackTrace();
	        }
		}
		    	
		try {
		   	PreparedStatement ps = null;
			String queryTags = "insert into taglijst (tagnaam) values (?)";
			
			ps = newConnection.prepareStatement(queryTags);
			
			ps.setString(1, recipe.getTag2());
			ps.executeUpdate();
		} catch (SQLException e) {
		    if(!e.getSQLState().equals("23505")) {
		    	// Ignore error on duplicate records, print stacktrace on other SQL errors
		    	e.printStackTrace();
		    }
		}
		    	
		try {
		   	PreparedStatement ps = null;
			String queryTags = "insert into taglijst (tagnaam) values (?)";
			
			ps = newConnection.prepareStatement(queryTags);
			
			ps.setString(1, recipe.getTag2());
			ps.executeUpdate();
		} catch (SQLException e) {
		    if(!e.getSQLState().equals("23505")) {
		    	// Ignore error on duplicate records, print stacktrace on other SQL errors
		    	e.printStackTrace();
		    }
		}
		
    	
		try {
		   	PreparedStatement ps = null;
			String queryTags = "insert into taglijst (tagnaam) values (?)";
			
			ps = newConnection.prepareStatement(queryTags);
			
			ps.setString(1, recipe.getTag4());
			ps.executeUpdate();
		} catch (SQLException e) {
		    if(!e.getSQLState().equals("23505")) {
		    	// Ignore error on duplicate records, print stacktrace on other SQL errors
		    	e.printStackTrace();
		    }
		}
		
		try {
			PreparedStatement ps = null;
			String queryTags = "insert into taglijst (tagnaam) values (?)";
			
			ps = newConnection.prepareStatement(queryTags);
			
			ps.setString(1, recipe.getTag5());
			ps.executeUpdate();
		} catch (SQLException e) {
		if(!e.getSQLState().equals("23505")) {
			// Ignore error on duplicate records, print stacktrace on other SQL errors
			e.printStackTrace();
			}
		}
		
		try {
			PreparedStatement ps = null;
			String queryTags = "insert into taglijst (tagnaam) values (?)";
			
			ps = newConnection.prepareStatement(queryTags);
			
			ps.setString(1, recipe.getTag6());
			ps.executeUpdate();
		} catch (SQLException e) {
			if(!e.getSQLState().equals("23505")) {
				// Ignore error on duplicate records, print stacktrace on other SQL errors
				e.printStackTrace();
			}
		}
		
		try {
			PreparedStatement ps = null;
			String queryTags = "insert into taglijst (tagnaam) values (?)";
			
			ps = newConnection.prepareStatement(queryTags);
			
			ps.setString(1, recipe.getTag7());
			ps.executeUpdate();
		} catch (SQLException e) {
			if(!e.getSQLState().equals("23505")) {
				// Ignore error on duplicate records, print stacktrace on other SQL errors
				e.printStackTrace();
			}
		}

	}

}
