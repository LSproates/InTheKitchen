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

import javax.swing.JOptionPane;

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
        titleBBCGF = doc.select("div.header__body > h1").first();
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
            Elements media = doc.select("[src]");
            
            for (Element src : media) {
                if (src.normalName().equals("img") && (src.attr("abs:src").contains(".jpg") || src.attr("abs:src").contains(".JPG"))) {
                    recipeImageURL = src.attr("abs:src").toString();
                }
            }

            recipeDescription = doc.select("header.header-inner > h2").first().text();
            recipeNotes = "";
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
            Elements media = doc.select("[src]");
            Elements infoSectionHeader = doc.select(".recipe-meta-item-header");
            Elements infoSectionBody = doc.select(".recipe-meta-item-body");
            
            for (Element src : media) {
                if (src.normalName().equals("img") && (src.attr("abs:src").contains(".jpg") || src.attr("abs:src").contains(".JPG"))) {
                    recipeImageURL = src.attr("abs:src").toString();
                }
            }

            recipeDescription = doc.select("div.recipe-summary > p").first().text();;
            recipeNotes = "";
            recipePersons = "";
            
            recipePrepTime = "";
            recipeCookTime = "";
            
            String[][] infoItems = new String[2][6];
            
            int iIndex = 0;
	        for (Element infoHeader : infoSectionHeader) {
        		infoItems[0][iIndex] = infoHeader.text();
        		iIndex++;
	        }
            
	        iIndex = 0;
	        for (Element infoBody : infoSectionBody) {
        		infoItems[1][iIndex] = infoBody.text();
        		iIndex++;
	        }

	        for (int i=0; i<iIndex; i++) {
	        	switch (infoItems[0][i]) {
	        	case "prep:":
	        		recipePrepTime = infoItems[1][i];
	        		break;
	        		
	        	case "cook:":
	        		recipeCookTime = infoItems[1][i];
	        		break;
	        		
	        	case "Yield:":
	        		recipePersons = infoItems[1][i];
	        		break;
	        	}
	        }
	        
	        String nutrition = doc.select("div.recipe-nutrition-section > div.section-body").first().text();
	        
	        String[] parts = nutrition.split("; ");
	        String[] subPart1 = parts[0].split(" ");
	        recipeEnergy = subPart1[0] + "Kcal";
            
	        String[] subPart2 = parts[1].split(" ");
	        recipeEiwit = subPart2[1];
            
	        String[] subPart3 = parts[2].split(" ");
	        recipeKoolhyraten = subPart3[1];
            
	        String[] subPart4 = parts[3].split(" ");
	        recipeVet = subPart4[1];
            
	        String[] subPart5 = parts[5].split(" ");
	        recipeZout = subPart5[1];

        	recipeVezels = "";
        	recipeSuiker = "";

            formatRecipeDetails(titleAR, ingredientsAR, instructionsAR);

       	
        } else if (titleBBCGF != null) {
            Elements ingredientsBBCGF = doc.select("section.recipe__ingredients > section > ul > li");
            Elements instructionsBBCGF = doc.select("section.recipe__method-steps > div.grouped-list > ul > li");
            recipeDescription = doc.select("div.mb-lg > div.editor-content > p").first().text();
            Element media = doc.select("main.site-main > div.post > section > div.header__container > div.header__image > div.image > div.image__container > picture > img[src]").first();
            recipeImageURL = media.attr("abs:src").toString();

            recipeNotes = "";
            recipePersons = doc.select("div.header__servings > div.icon-with-text__children").first().text();
            
            Elements timeRange = doc.select("div.time-range-list > div.icon-with-text__children > ul > li > span > time");
            
            int times = 0;
            for (Element time : timeRange) {
                if (times == 0) {
                	recipePrepTime = time.text();
                } else {
                	recipeCookTime = time.text();
                }
                times++;
            }
            
            Elements nutrition = doc.select("td.key-value-blocks__value");
            String[] nutritionValues = new String[nutrition.size()];
            int nutritionIndex = 0;
            for (Element nutritionValue : nutrition) {
            	nutritionValues[nutritionIndex] = nutritionValue.text();
            	nutritionIndex++;
            }
            
        	recipeEnergy = nutritionValues[0] + " kcal";
        	recipeVezels = nutritionValues[5];
        	recipeZout = nutritionValues[7];
        	recipeVet = nutritionValues[1];
        	recipeKoolhyraten = nutritionValues[3];
        	recipeEiwit = nutritionValues[6];
        	recipeSuiker = nutritionValues[4];

            formatRecipeDetails(titleBBCGF, ingredientsBBCGF, instructionsBBCGF);
        	
        } else if (titleATK != null) {
            Elements ingredientsATK = doc.select(".ingredient__title");
            Elements instructionsATK = doc.select(".recipe-instruction__content");
            
            formatRecipeDetails(titleATK, ingredientsATK, instructionsATK);

        	
        } else if (titleTasteShow != null) {
            Elements ingredientsTasteShow = doc.select("div.ingredients > ul > li");
            Elements instructionsTasteShow = doc.select("div.directions > div.one_step > div.text");
            Element recipeImageTasteShow = doc.select("div.featured_image > img").first();
            recipeImageURL = recipeImageTasteShow.attr("abs:src").toString();
            recipePersons = doc.select("div.recipe_info > div.serving").first().text();
            recipeCookTime = doc.select("div.recipe_info > div.total_time").first().text();
            
            formatRecipeDetails(titleTasteShow, ingredientsTasteShow, instructionsTasteShow);
       	
        } else {
        	JOptionPane.showMessageDialog(null, "Recept formaat niet erkend.", "Onbekend recept formaat.", JOptionPane.INFORMATION_MESSAGE);
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
	        	instructionsList = instructionsList + trim(instruction.text(), 450) + "\n\n";
	        }
    	}
    }
    

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trimString (String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
}
