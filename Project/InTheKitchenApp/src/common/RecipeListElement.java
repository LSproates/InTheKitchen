package common;

public class RecipeListElement {
	private int recipeId;
	private String recipeTitle;
	private String recipeSummary;
	private String recipePhotoName;
	private String kitchen;
	private String mealType;
	private String theme;
	
	public RecipeListElement() {
		recipeId = 0;
		recipeTitle = "Default recipe title";
		recipeSummary = "Good food";
		recipePhotoName = "";
		kitchen = "General";
		mealType= "Not specified";
		theme = "Daags";

	}
	
	public RecipeListElement (int id, String title, String summary, String imageName, String keuken, String type, String thema) {
		// Strings to be displayed using html layout
		recipeId = id;
		recipeTitle = title;
		recipeSummary = summary;
		recipePhotoName = imageName;
		kitchen = keuken;
		mealType= type;
		theme = thema;
	}
	
	public String displayText() {
		String objectDisplay = "";
		
		objectDisplay = recipeTitle + " Keuken: " + kitchen + " - " + recipeSummary; 
		
		return objectDisplay;
	}
	
	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int id) {
		recipeId = id;
	}

	public String getRecipeTitle() {
		return recipeTitle;
	}
	public void setRecipeTitle(String recipeTitle) {
		this.recipeTitle = recipeTitle;
	}
	public String getRecipeSummary() {
		return recipeSummary;
	}
	public void setRecipeSummary(String recipeSummary) {
		this.recipeSummary = recipeSummary;
	}
	public String getRecipePhotoName() {
		return recipePhotoName;
	}
	public void setRecipePhotoName(String recipePhotoName) {
		this.recipePhotoName = recipePhotoName;
	}
	public String getKitchen() {
		return kitchen;
	}
	public void setKitchen(String kitchen) {
		this.kitchen = kitchen;
	}
	public String getMealType() {
		return mealType;
	}
	public void setMealType(String mealType) {
		this.mealType = mealType;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	public String toString() {
		return "ID: " + recipeId + ",    Object: " + recipeTitle;
	}
	
}
