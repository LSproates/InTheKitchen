package common;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
 
public class RecipeListRenderer extends JLabel implements ListCellRenderer<RecipeListElement> {
 
    @Override
    public Component getListCellRendererComponent(JList<? extends RecipeListElement> list, RecipeListElement recipe, int index,
        boolean isSelected, boolean cellHasFocus) {

        //JEditorPane epForText = new JEditorPane();
        JLabel lblImage = new JLabel("");
        
		BufferedImage recipeImage = null;
		try {
			recipeImage = ImageIO.read(new File("images/recipes/" + recipe.getRecipePhotoName()));
		} catch (IOException e) {
			try {
				recipeImage = ImageIO.read(new File("images/recipes/defaultimage.jpg"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		Image scaled = recipeImage.getScaledInstance(100,70,Image.SCALE_SMOOTH);
		lblImage = new JLabel();

		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		
        ImageIcon imageIcon = new ImageIcon(scaled);
        
		setLayout(new FlowLayout());
		this.setIcon(imageIcon);
		setText("<html><body>" + "<b>" + recipe.getRecipeId() + ":  " + recipe.getRecipeTitle() + "</b>" +
				"<p><i>Keuken: " + recipe.getKitchen() + "&nbsp;&nbsp;&nbsp;&nbsp;Soort maaltijd: "
						+ recipe.getMealType() + "&nbsp;&nbsp;&nbsp;&nbsp;Thema: "
						+ recipe.getTheme() + "</i></p>"  + "</body></html>");
		this.setOpaque(true);

		if (isSelected) {
			this.setBackground(list.getSelectionBackground());
			this.setForeground(list.getSelectionForeground());
		}
		else {
			this.setBackground(list.getBackground());
			this.setForeground(list.getForeground());
		}

		return this;
    }
     

}
