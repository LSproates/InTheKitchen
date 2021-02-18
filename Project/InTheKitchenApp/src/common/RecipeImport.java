package common;

import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.util.ArrayList;

public class RecipeImport {
	
	private static String recipeTitle = "";
	private static String ingredientsList = "";
	private static String instructionsList = "";
	private static String recipeDescription = "";
	private static String recipePersons = "";
	private static String recipeNotes = "";
	private static String recipeExtras = "";
	private static String recipePrepTime = "";
	private static String recipeCookTime = "";
	private static String recipeEnergy = "";
	private static String recipeVezels = "";
	private static String recipeZout = "";
	private static String recipeVet = "";
	private static String recipeKoolhyraten = "";
	private static String recipeEiwit = "";
	private static String recipeSuiker = "";
	private static String recipeImageURL = "";
	
	public static ArrayList<String> importRecipe (String internetURL) {
		
		ArrayList<String> readWebRecipe = new ArrayList<String>();

		String webHTMLText = "";
		String webURL = "";
		
		try {
	        URL url = new URL(internetURL);
			webURL = internetURL;
	        // Make a URL to the web page
	
	        // Get the input stream through URL Connection
	        URLConnection con = url.openConnection();
			
	        InputStream inStream = con.getInputStream();
	
	        // Input Stream created, it's just plain old Java IO stuff.
	
	        BufferedReader br = new BufferedReader(new InputStreamReader(inStream));
	
	        String line = null;
		    try {
		        // read each line and write to System.out and save in HTML string
		        while ((line = br.readLine()) != null) {
		            webHTMLText = webHTMLText + line + "\n";
		        }
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Jsoup can also be used to connect to the url directly 

	    Document doc = null;
	    
		try {
			doc = Jsoup.connect(webURL).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Element title24Kitchen = null;
        Element titleAH = null;
        Element titleAR = null;
        Element titleBBCGF = null;
        Element titleATK = null;
        Element titleTasteShow = null;
        
        title24Kitchen = doc.select("div.node__top > h1.p-name").first();
        titleAH = doc.select("header.header-inner > h1").first();
        titleAR = doc.select("div.headline-wrapper > h1").first();
        titleBBCGF = doc.select("div.masthead__body > h1").first();
        titleATK = doc.select("h1.recipe-detail-header__title").first();
        titleTasteShow = doc.select("div.left_side > h1").first();
        
        if (title24Kitchen != null) {
            Elements ingredients24Kitchen = doc.select(".recipe-ingredient");
            Elements instructions24Kitchen = doc.select(".body-text > ul > li");
            recipeDescription = "";
            recipeNotes = "";
            Element recipeImage24Kitchen = doc.select("picture > img").first();
            recipePersons = doc.select("div.portion-amount").first().text();
            recipeExtras = "";
            recipePrepTime = "";
            recipeCookTime = doc.select("div.recipe--total-time > span.time").first().text();
            
            String recipeImage24KitchenURL = recipeImage24Kitchen.text();
            recipeImage24KitchenURL = recipeImage24KitchenURL.replace("<img src=\"", "https://www.24Kitchen.nl/");
            recipeImageURL = recipeImage24KitchenURL.substring(0, recipeImage24KitchenURL.indexOf("\"")-1);

            formatRecipeDetails(title24Kitchen, ingredients24Kitchen, instructions24Kitchen);
       	
        } else if (titleAH != null) {
            Elements ingredientsAH = doc.select("section.ingredients > ul.ingredient-selector-list > li");
            Elements instructionsAH = doc.select("section.preparation > ol > li");
            recipeDescription = doc.select("header.header-inner > h2").first().text();
            recipeNotes = "";
            recipeImageURL = doc.select("article > img.recipe-image").first().text();
            recipePersons = doc.select("div.scaler__scale > span.scaler__label").first().text();
            
            recipePrepTime = "";
            recipeCookTime = doc.select("li.cooking-time > ul > li").first().text();
            
        	recipeEnergy = doc.select("section.nutrition > ul > li > span[itemprop = calories]").first().text();
        	recipeVezels = doc.select("section.nutrition > ul > li > span[itemprop = fiberContent]").first().text();
        	recipeZout = doc.select("section.nutrition > ul > li > span[itemprop = sodiumContent]").first().text();
        	recipeVet = doc.select("section.nutrition > ul > li > span[itemprop = fatContent]").first().text();
        	recipeKoolhyraten = doc.select("section.nutrition > ul > li > span[itemprop = carbohydrateContent]").first().text();
        	recipeEiwit = doc.select("section.nutrition > ul > li > span[itemprop = proteinContent]").first().text();
        	recipeSuiker = doc.select("section.nutrition > ul > li > span[itemprop = sugarContent]").first().text();

            formatRecipeDetails(titleAH, ingredientsAH, instructionsAH);
        	
        } else if (titleAR != null) {
            Elements ingredientsAR = doc.select(".ingredients-item");
            Elements instructionsAR = doc.select("div.paragraph");
            Element recipeDescriptionAR = null;
            Element recipeImageAR = null;
            
        	recipeTitle = trim(titleAR.text(), 100);
            for (Element ingredient : ingredientsAR) {
             	ingredientsList = ingredientsList + trim(ingredient.text(), 50) + "\n";
             }

       	
        } else if (titleBBCGF != null) {
            Elements ingredientsBBCGF = doc.select("section.recipe-template__ingredients > section > ul > li");
            Elements instructionsBBCGF = doc.select("section.recipe-template__method-steps > div.grouped-list");
            Element recipeDescriptionBBCGF = null;
            Element recipeImageBBCGF = null;
      	
        } else if (titleATK != null) {
            Elements ingredientsATK = doc.select(".ingredient__title");
            Elements instructionsATK = doc.select(".recipe-instruction__content");
            Element recipeDescriptionATK = null;
            Element recipeImageATK = null;
            
            for (Element ingredient : ingredientsATK) {
            	ingredientsList = ingredientsList + trim(ingredient.text(), 50) + "\n";
            }

        	
        } else if (titleTasteShow != null) {
            Elements ingredientsTasteShow = doc.select("div.ingredients > ul > li");
            Elements instructionsTasteShow = doc.select("div.directions > div.one_step > div.text");
            Element recipeDescriptionTasteShow = null;
            Element recipeImageTasteShow = null;
            
            for (Element ingredient : ingredientsTasteShow) {
            	ingredientsList = ingredientsList + trim(ingredient.text(), 50) + "\n";
            }
       	
        }
        
        readWebRecipe.add(recipeTitle);
        readWebRecipe.add(ingredientsList);
        readWebRecipe.add(instructionsList);
        readWebRecipe.add(recipeDescription);
        readWebRecipe.add(recipeNotes);
        readWebRecipe.add(recipeImageURL);
        readWebRecipe.add(recipePersons);
        readWebRecipe.add(recipeExtras);
        readWebRecipe.add(recipePrepTime);
        readWebRecipe.add(recipeCookTime);
        readWebRecipe.add(recipeEnergy);
        readWebRecipe.add(recipeVezels);
        readWebRecipe.add(recipeZout);
        readWebRecipe.add(recipeVet);
        readWebRecipe.add(recipeKoolhyraten);
        readWebRecipe.add(recipeEiwit);
        readWebRecipe.add(recipeSuiker);

        return readWebRecipe;

	}

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }

    private static void formatRecipeDetails (Element title, Elements ingredients, Elements instructions) {

    	recipeTitle = trim(title.text(), 100);
    	
    	if (ingredients != null) {
	        for (Element ingredient : ingredients) {
	         	ingredientsList = ingredientsList + trim(ingredient.text(), 50) + "\n";
	        }
    	}
    	
    	if (instructions != null ) {
	        for (Element instruction : instructions) {
	        	instructionsList = instructionsList + trim(instruction.text(), 250) + "\n\n";
	        }
    	}
    }
}
