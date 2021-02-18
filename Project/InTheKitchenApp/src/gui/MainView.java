package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.apache.commons.io.FileUtils;

import common.DBReset;
import common.DatabaseAccess;
import common.FilterDialog;
import common.RecipeImport;
import common.RecipeListElement;
import common.RecipeListRenderer;
import elementclasses.Recipe;
import dao.*;

import javax.swing.JTextArea;
import java.awt.Component;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.border.SoftBevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JTextPane;
import java.awt.SystemColor;

public class MainView {
	
	public ArrayList<String> listOfKitchens = null;
	public ArrayList<String> listOfMealTypes = null;
	public ArrayList<String> listOfThemes = null;
	public ArrayList<String> listOfTags = null;
	public DatabaseAccess databaseAccess = new DatabaseAccess();
	public Connection dbConnection = null;
	public String imageFilename = "";
	
	private int selectedTagField;
	private Recipe recipeInputManSave = null;

	private JFrame frmInTheKitchen;
	private JMenu mnHome;
	private JMenuItem mntmOverzicht;
	private JMenuItem mntmExit;
	private JMenu mnToevoegen;
	private JMenuItem mntmZelf;
	private JMenuItem mntmImport;
	private JMenuItem mntmDocument;
	private JMenuItem mntmScan;
	private JMenu mnIngredienten;
	private JMenu mnPlanner;
	private JMenu mnPrint;
	private JMenu mnAbout;
	private JPanel pnlOverzicht;
	private JPanel pnlHome;
	private JPanel pnlFeaturettes;
	private JPanel pnlInfo;
	private JPanel pnlFirstInfoGroup;
	private JLabel lblFirstInfoImage;
	private JScrollPane spFirstInfo;
	private JEditorPane epFirstInfoText;
	private JPanel pnlSecondInfoGroup;
	private JScrollPane spSecondInfo;
	private JEditorPane epSecondInfoText;
	private JLabel lblSecondInfoImage;
	private JPanel pnlThirdInfoGroup;
	private JLabel lblThirdInfoImage;
	private JScrollPane spThirdInfo;
	private JEditorPane epThirdInfoText;
	private JPanel pnlFeaturette_1;
	private JLabel lblFeaturette_1_Image;
	private JEditorPane epFeaturette_1_Text;
	private JPanel pnlFeaturette_2;
	private JEditorPane epFeaturette_2_Text;
	private JLabel lblFeaturette_2_Image;
	private JPanel pnlRecipeViewsContainer;
	private JPanel pnlTopView;
	private JPanel pnlListView;
	private JPanel pnlDetailView;
	private JPanel pnlGroupedView;
	private JPanel pnlFilterBar;
	private JMenuItem mnDBReset;
	private JTextField txtZoekOpNaam;
	private JTextField txtKeukenFilter;
	private JTextField txtMaaltypeFilter;
	private JTextField txtThemaFilter;
	private JMenu mnZoeken;
	private JMenu mnResetDB;
	private JButton btnKeuken;
	private JButton btnMaalType;
	private JButton btnTheme;
	private JButton btnZoekFilter;
	private JButton btnAllRecipes;
	private JButton btnZoek;
	
	private JList<RecipeListElement> lstRecipeListElement;
	
	public static DefaultListModel<RecipeListElement> recipeList = new DefaultListModel<RecipeListElement>();
	private JPanel pnlDetails;
	private JScrollPane spRecipeDetails;
	private JLabel lblCurrentRecipeName;
	private JPanel pnlCurrentRecipeContainer;
	private JLabel lblCurrentRecipeImage;
	private JTextArea txtaCurrentRecipeBeschrijving;
	private JLabel lblCurrentRecipeNotes;
	private JScrollPane spCurrentRecipeNotes;
	private JTextArea txtaCurrentRecipeNotes;
	private JLabel lblCRKitchen;
	private JLabel lblCurrentRecipeKeuken;
	private JLabel lblCRType;
	private JLabel lblCurrentRecipeMaaltype;
	private JLabel lblCRThema;
	private JLabel lblCurrentRecipeThema;
	private JLabel lblCRAantalPersonen;
	private JLabel lblCurrentRecipeAantalPersonen;
	private JLabel lblCRPrep;
	private JLabel lblCurrentRecipePreptime;
	private JLabel lblCRBron;
	private JLabel lblCurrentRecipeSource;
	private JLabel lblCRKook;
	private JLabel lblCurrentRecipeCooktime;
	private JLabel lblCRVoeding;
	private JLabel lblCREnergie;
	private JLabel lblCurrentRecipeEnergie;
	private JLabel lblCRSuiker;
	private JLabel lblCurrentRecipeSuiker;
	private JLabel lblCRKoolhydraten;
	private JLabel lblCurrentRecipeKoolhydraten;
	private JLabel lblCRNatrium;
	private JLabel lblCurentRecipeNatrium;
	private JLabel lblCREiwit;
	private JLabel lblCurrentRecipeEiwit;
	private JLabel lblCRVet;
	private JLabel lblCurrentRecipeVet;
	private JLabel lblCRVezels;
	private JLabel lblCurrentRecipeVezels;
	private JScrollPane spCRIngredienten;
	private JTextArea txtaCurrentRecipeIngredienten;
	private JScrollPane spCRSteps;
	private JTextArea txtaCurrentRecipeStappen;
	private JLabel lblCRIngredienten;
	private JLabel lblCRStappen;
	private JScrollPane spCRExtras;
	private JLabel lblCRExtras;
	private JTextArea txtaCurrentRecipeExtras;
	private JLabel lblCRTags;
	private JScrollPane spCRTags;
	private JTextArea txtaCurrentRecipeTags;
	private JPanel pnlToevoegen;
	private JScrollPane spRecipeInput;
	private JButton btnImageBrowse;
	private JLabel lblRecipeImageLabel;
	private JLabel lblRecipeInputManBeschrijving;
	private JTextArea txtaRecipeInputManBeschrijving;
	private JTextField txtRecipeInputManKeuken;
	private JButton btnRecipeInputManKeuken;
	private JTextField txtRecipeInputManMaaltype;
	private JTextField txtRecipeInputManThema;
	private JTextField txtRecipeInputManAantalPersonen;
	private JTextField txtRecipeInputManPreptijd;
	private JTextField txtRecipeInputManKooktijd;
	private JTextField txtRecipeInputManBron;
	private JTextField txtRecipeInputManEnergie;
	private JTextField txtRecipeInputManSuiker;
	private JTextField txtRecipeInputManNatrium;
	private JTextField txtRecipeInputManKoolhydraten;
	private JTextField txtRecipeInputManVet;
	private JTextField txtRecipeInputManVezels;
	private JTextField txtRecipeInputManEiwit;
	private JTextArea txtaRecipeInputManStappen;
	private JTextArea txtaRecipeInputManIngredients;
	private JButton btnRecipeInputManMaaltype;
	private JButton btnRecipeInputManThema;
	private JButton btnRecipeInputManSave;
	private JTextField txtRecipeInputManName;
	private JPanel pnlRecipeInputManContainer;
	private JTextArea txtaRecipeInputManNotes;
	private JTextField txtRecipeInputManPhotoName;
	private JTextField txtRecipeInputManPhotoPath;
	private JButton btnRecipeInputImp;
	private JTextField txtRecipeInputManTag2;
	private JTextField txtRecipeInputManTag1;
	private JTextField txtRecipeInputManTag3;
	private JTextField txtRecipeInputManTag4;
	private JTextField txtRecipeInputManTag5;
	private JTextField txtRecipeInputManTag6;
	private JTextField txtRecipeInputManTag7;
	private JScrollPane spRecipeInputManExtras;
	private JButton btnRecipeInputManTags;
	private JTextArea txtaRecipeInputManExtras;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
					window.frmInTheKitchen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainView() {
		initializeApplication();
		componentsInitialize();
		componentsEvents();
		
		//TODO  Add input for Tags and DB Tag table
	}
	
	private void initializeApplication () {
		// Set up database, as needed (check whether exits - if not run load scripts)
		// Load application ArrayLists for kitchen, meal type, themes and tags
		// Set other control application variables
		
		dbConnection = databaseAccess.startDatabase();
		/*
		 * populateTables on dbReset to original setup and content --- give warning
		 * try { databaseAccess.populateTables(dbConnection); } catch (SQLException e) {
		 */	
		
		listOfKitchens = databaseAccess.getKitchenNames(dbConnection);
		listOfMealTypes = databaseAccess.getMealTypes(dbConnection);
		listOfThemes = databaseAccess.getMealThemes(dbConnection);
		listOfTags = databaseAccess.getTags(dbConnection);

	}

	private void swapVisibility(JPanel pnlToBeVisible) {
		// set all top level panels visibility to false, set panel parameter visibility true
		pnlHome.setVisible(false);
		pnlOverzicht.setVisible(false);
		pnlDetails.setVisible(false);
		pnlToevoegen.setVisible(false);
		
		pnlToBeVisible.setVisible(true);
		pnlToBeVisible.repaint();
	}
	
	public void initRecipeList() {
		databaseAccess.getRecipeSummaryList(dbConnection);

	}
	
	private void selectTag (JTextField selectedTagFocus) {
		
		System.out.println(listOfTags.size());
		if (listOfTags == null || listOfTags.size() == 0) {
			JOptionPane.showMessageDialog(frmInTheKitchen, "Nog geen tags beschikbaar", "Informatie", JOptionPane.INFORMATION_MESSAGE);
		} else {
			String[] listOfItems = new String[listOfTags.size()];
			listOfItems = listOfTags.toArray(listOfItems);
			
			ArrayList<String> selectedNames = FilterDialog.showDialog(
								frmInTheKitchen,
								btnRecipeInputManTags, 
								"Defined tags:", 
								"Choose tag", 
								listOfItems,
								selectedTagFocus.getText(), 
								listOfItems[0],
								ListSelectionModel.SINGLE_SELECTION);
			  
			String itemsSelected = "";
			
			for (String itemName: selectedNames) {
					if(itemsSelected.equals("")) {
						itemsSelected = itemName;
					} else {
						itemsSelected = itemsSelected + "," + itemName;
					}
			}
			
			selectedTagFocus.setText(itemsSelected);
		}
	}

	
    // Methode to resize imageIcon with the same size of a Jlabel
   public ImageIcon ResizeImage(String imagePath, JLabel label)
   {
       ImageIcon MyImage = new ImageIcon(imagePath);
       Image img = MyImage.getImage();
       Image newImg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
       ImageIcon image = new ImageIcon(newImg);
       return image;
   }

	
	public void displayRecipeDetails (int dbRecipeID) {
		
        databaseAccess.getRecipe(dbConnection, dbRecipeID);
        
		BufferedImage recipeImage = null;
		try {
			recipeImage = ImageIO.read(new File("images/recipes/" + databaseAccess.currentRecipe.getFoto_naam()));
		} catch (IOException e) {
			try {
				recipeImage = ImageIO.read(new File("images/recipes/defaultimage.jpg"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		Image scaled = recipeImage.getScaledInstance(200,140,Image.SCALE_SMOOTH);

		lblCurrentRecipeImage.setHorizontalAlignment(SwingConstants.CENTER);
		
        ImageIcon imageIcon = new ImageIcon(scaled);
        
        lblCurrentRecipeImage.setLayout(new FlowLayout());

        lblCurrentRecipeName.setText("Nr. " + databaseAccess.currentRecipe.getId() + ":  " + databaseAccess.currentRecipe.getNaam());
        lblCurrentRecipeImage.setIcon(imageIcon);
        txtaCurrentRecipeBeschrijving.setText(databaseAccess.currentRecipe.getBeschrijving());
        txtaCurrentRecipeNotes.setText(databaseAccess.currentRecipe.getNotes());
        lblCurrentRecipeKeuken.setText(databaseAccess.currentRecipe.getKeuken());
        lblCurrentRecipeMaaltype.setText(databaseAccess.currentRecipe.getMaal_type());
        lblCurrentRecipeThema.setText(databaseAccess.currentRecipe.getThema());
        lblCurrentRecipeAantalPersonen.setText(databaseAccess.currentRecipe.getAantal_personen());
        lblCurrentRecipePreptime.setText(databaseAccess.currentRecipe.getPrep_tijd());
        lblCurrentRecipeCooktime.setText(databaseAccess.currentRecipe.getKook_tijd());
        lblCurrentRecipeSource.setText(databaseAccess.currentRecipe.getSource());
        lblCurrentRecipeEnergie.setText(databaseAccess.currentRecipe.getEnergie());
        lblCurrentRecipeSuiker.setText(databaseAccess.currentRecipe.getSuiker());
        lblCurrentRecipeKoolhydraten.setText(databaseAccess.currentRecipe.getKoolhydraten());
        lblCurentRecipeNatrium.setText(databaseAccess.currentRecipe.getNatrium());
        lblCurrentRecipeEiwit.setText(databaseAccess.currentRecipe.getEiwit());
        lblCurrentRecipeVet.setText(databaseAccess.currentRecipe.getVet());
        lblCurrentRecipeVezels.setText(databaseAccess.currentRecipe.getVezels());
        txtaCurrentRecipeIngredienten.setText(databaseAccess.currentRecipe.getIngredienten());
        txtaCurrentRecipeStappen.setText(databaseAccess.currentRecipe.getStappen());
        txtaCurrentRecipeExtras.setText(databaseAccess.currentRecipe.getExtras());
        txtaCurrentRecipeTags.setText(databaseAccess.currentRecipe.getTagstring());

	}

	/**
	 * Set the events for the components of the frame.
	 */
	private void componentsEvents() {

		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(frmInTheKitchen, "Afsluiten?");
				
				if (result == JOptionPane.YES_OPTION) {
					databaseAccess.shutdownDatabase(dbConnection);
					System.exit(0);
				}
			}
		});
		
		mntmOverzicht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				swapVisibility(pnlOverzicht);
			}
		});

		mnDBReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBReset.resetDB(dbConnection);
			}
		});
		
		mnZoeken.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				swapVisibility(pnlOverzicht);
			}
		});
		
		mntmImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				swapVisibility(pnlToevoegen);
				btnRecipeInputImp.setVisible(true);
			}
		});
		
		btnImageBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
	          JFileChooser file = new JFileChooser();
	          file.setCurrentDirectory(new File(System.getProperty("user.home")));
	          
	          //filter the files
	          FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg","gif","png");
	          file.addChoosableFileFilter(filter);
	          int result = file.showSaveDialog(null);

	          if(result == JFileChooser.APPROVE_OPTION){
	              File selectedFile = file.getSelectedFile();
	              String path = selectedFile.getAbsolutePath();
	              txtRecipeInputManPhotoPath.setText(path);
	              
	              imageFilename = selectedFile.getName();
	              txtRecipeInputManPhotoName.setText(imageFilename);
	              lblRecipeImageLabel.setText("");
	              lblRecipeImageLabel.setIcon(ResizeImage(path, lblRecipeImageLabel));
	              
	              // TODO Add copyto copy image to image repository; allow for paste from clipboard
	          }
	          else if(result == JFileChooser.CANCEL_OPTION){
	        	  imageFilename = "";
	          }
			}
		});
		
		btnRecipeInputManKeuken.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] listOfItems = new String[listOfKitchens.size()];
				listOfItems = listOfKitchens.toArray(listOfItems);
				
				ArrayList<String> selectedNames = FilterDialog.showDialog(
									frmInTheKitchen,
									btnRecipeInputManKeuken, 
									"Available kitchens:", 
									"Choose kitchen", 
									listOfItems,
									txtRecipeInputManKeuken.getText(), 
									"Algemeen",
									ListSelectionModel.SINGLE_SELECTION);
				  
				String itemsSelected = "";
				
				for (String itemName: selectedNames) {
						if(itemsSelected.equals("")) {
							itemsSelected = itemName;
						} else {
							itemsSelected = itemsSelected + "," + itemName;
						}
				}
				
				txtRecipeInputManKeuken.setText(itemsSelected);
			 
			}
		});
		
		btnRecipeInputManMaaltype.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] listOfItems = new String[listOfMealTypes.size()];
				listOfItems = listOfMealTypes.toArray(listOfItems);
				
				ArrayList<String> selectedNames = FilterDialog.showDialog(
									frmInTheKitchen,
									btnRecipeInputManMaaltype, 
									"Available meal types:", 
									"Choose meal types", 
									listOfItems,
									txtRecipeInputManMaaltype.getText(), 
									"Aardappel",
									ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				  
				String itemsSelected = "";
				
				for (String itemName: selectedNames) {
						if(itemsSelected.equals("")) {
							itemsSelected = itemName;
						} else {
							itemsSelected = itemsSelected + "," + itemName;
						}
				}
				
				txtRecipeInputManMaaltype.setText(itemsSelected);
			}
		});
		
		btnRecipeInputManThema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] listOfItems = new String[listOfThemes.size()];
				listOfItems = listOfThemes.toArray(listOfItems);
				
				ArrayList<String> selectedNames = FilterDialog.showDialog(
									frmInTheKitchen,
									btnRecipeInputManThema, 
									"Available Themes:", 
									"Choose theme", 
									listOfItems,
									txtRecipeInputManThema.getText(), 
									"Daags",
									ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				  
				String itemsSelected = "";
				
				for (String itemName: selectedNames) {
						if(itemsSelected.equals("")) {
							itemsSelected = itemName;
						} else {
							itemsSelected = itemsSelected + "," + itemName;
						}
				}
				
				txtRecipeInputManThema.setText(itemsSelected);
			}
		});


		btnTheme.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String[] listOfItems = new String[listOfThemes.size()];
				listOfItems = listOfThemes.toArray(listOfItems);
				
				ArrayList<String> selectedNames = FilterDialog.showDialog(
									frmInTheKitchen,
									btnTheme, 
									"Available Themes:", 
									"Choose theme", 
									listOfItems,
									txtThemaFilter.getText(), 
									"Daags",
									ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				  
				String itemsSelected = "";
				
				for (String itemName: selectedNames) {
						if(itemsSelected.equals("")) {
							itemsSelected = itemName;
						} else {
							itemsSelected = itemsSelected + "," + itemName;
						}
				}
				
				txtThemaFilter.setText(itemsSelected);
			}
		});

		btnMaalType.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String[] listOfItems = new String[listOfMealTypes.size()];
				listOfItems = listOfMealTypes.toArray(listOfItems);
				
				ArrayList<String> selectedNames = FilterDialog.showDialog(
									frmInTheKitchen,
									btnMaalType, 
									"Available meal types:", 
									"Choose meal types", 
									listOfItems,
									txtMaaltypeFilter.getText(), 
									"Aardappel",
									ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				  
				String itemsSelected = "";
				
				for (String itemName: selectedNames) {
						if(itemsSelected.equals("")) {
							itemsSelected = itemName;
						} else {
							itemsSelected = itemsSelected + "," + itemName;
						}
				}
				
				txtMaaltypeFilter.setText(itemsSelected);
			 
			}
		});
		
		btnKeuken.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String[] listOfItems = new String[listOfKitchens.size()];
				listOfItems = listOfKitchens.toArray(listOfItems);
				
				ArrayList<String> selectedNames = FilterDialog.showDialog(
									frmInTheKitchen,
									btnKeuken, 
									"Available kitchens:", 
									"Choose kitchen", 
									listOfItems,
									txtKeukenFilter.getText(), 
									"Algemeen",
									ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				  
				String itemsSelected = "";
				
				for (String itemName: selectedNames) {
						if(itemsSelected.equals("")) {
							itemsSelected = itemName;
						} else {
							itemsSelected = itemsSelected + "," + itemName;
						}
				}
				
				txtKeukenFilter.setText(itemsSelected);
			 
			}
		});
		
		btnAllRecipes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				recipeList.clear();

				initRecipeList();
				lstRecipeListElement.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				lstRecipeListElement.setCellRenderer(new RecipeListRenderer());
			}
		});
		
		mntmZelf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				swapVisibility(pnlToevoegen);
				btnRecipeInputImp.setVisible(false);

			}
		});
		
		btnRecipeInputImp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String url = txtRecipeInputManBron.getText();
				
				String imageURL = "https://www.24kitchen.nl/files/styles/300h_300w/public/2014-04/134748.original.jpg?itok=X9rm0sgJ";
				
				if (url.isEmpty() || url.equals("")) {
					JOptionPane.showMessageDialog(pnlToevoegen, "Website Bron is niet gevuld.", "Website Bron", JOptionPane.INFORMATION_MESSAGE);
				} else {
				
					ArrayList<String> recipeParts = new ArrayList<String> ();
					recipeParts = RecipeImport.importRecipe(url);
					
					String[] partsOfWebRecipe = new String[recipeParts.size()];
					partsOfWebRecipe = recipeParts.toArray(partsOfWebRecipe);
					
					txtRecipeInputManName.setText(partsOfWebRecipe[0]);
					txtaRecipeInputManIngredients.setText(partsOfWebRecipe[1]);
					txtaRecipeInputManStappen.setText(partsOfWebRecipe[2]);
					txtaRecipeInputManBeschrijving.setText(partsOfWebRecipe[3]);
					txtaRecipeInputManNotes.setText(partsOfWebRecipe[4]);
					String imageurl = partsOfWebRecipe[5];
					System.out.println(imageurl);
					txtRecipeInputManAantalPersonen.setText(partsOfWebRecipe[6]);
					txtaRecipeInputManExtras.setText(partsOfWebRecipe[7]);
					txtRecipeInputManPreptijd.setText(partsOfWebRecipe[8]);
					txtRecipeInputManKooktijd.setText(partsOfWebRecipe[9]);
					txtRecipeInputManEnergie.setText(partsOfWebRecipe[10]);
					txtRecipeInputManVezels.setText(partsOfWebRecipe[11]);
					txtRecipeInputManNatrium.setText(partsOfWebRecipe[12]);
					txtRecipeInputManVet.setText(partsOfWebRecipe[13]);
					txtRecipeInputManKoolhydraten.setText(partsOfWebRecipe[14]);
					txtRecipeInputManEiwit.setText(partsOfWebRecipe[15]);
					txtRecipeInputManSuiker.setText(partsOfWebRecipe[16]);
					
					Image image = null;
					try {
					    URL webImageURL = new URL(imageURL);
					    image = ImageIO.read(webImageURL);
						Image scaled = image.getScaledInstance(150,125,Image.SCALE_SMOOTH);

				        ImageIcon imageIcon = new ImageIcon(scaled);

					    lblRecipeImageLabel.setIcon(imageIcon);
					    lblRecipeImageLabel.setText("");
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
				}
			}
		});
		
		lstRecipeListElement.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        int selectedID = 0;
		        if ((evt.getClickCount() == 2) || (evt.getClickCount() == 3)) {

		            // Double-click / triple-click detected
		            int index = list.locationToIndex(evt.getPoint());
		            String myString = String.valueOf(list.getSelectedValue());
		            selectedID = Integer.parseInt(myString.substring(myString.indexOf("ID: ") + 4, myString.indexOf(", ")));
		            displayRecipeDetails(selectedID);
					swapVisibility(pnlDetails);
		        }
			}
		});

		mnResetDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBReset.resetDB(dbConnection);
			}
		});
		
		btnRecipeInputManSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Copy input data to instance Recipe class
				// Verify that Name has been filled, otherwise abort
				
				recipeInputManSave = new Recipe();
				
				if (txtRecipeInputManName.getText().equals("") || txtRecipeInputManName.getText() == null) {
					JOptionPane.showMessageDialog(null, "De naam van het recept moet ingevuld worden.", "information", JOptionPane.INFORMATION_MESSAGE);
				} else {
					recipeInputManSave.setNaam(txtRecipeInputManName.getText());
					recipeInputManSave.setBeschrijving(txtaRecipeInputManBeschrijving.getText());
					recipeInputManSave.setNotes(txtaRecipeInputManNotes.getText());
					recipeInputManSave.setAantal_personen(txtRecipeInputManAantalPersonen.getText());
					recipeInputManSave.setEnergie(txtRecipeInputManEnergie.getText());
					recipeInputManSave.setEiwit(txtRecipeInputManEiwit.getText());
					recipeInputManSave.setKoolhydraten(txtRecipeInputManKoolhydraten.getText());
					recipeInputManSave.setVet(txtRecipeInputManVet.getText());
					recipeInputManSave.setNatrium(txtRecipeInputManNatrium.getText());
					recipeInputManSave.setVezels(txtRecipeInputManVezels.getText());
					recipeInputManSave.setKeuken(txtRecipeInputManKeuken.getText());
					recipeInputManSave.setMaal_type(txtRecipeInputManMaaltype.getText());
					recipeInputManSave.setThema(txtRecipeInputManThema.getText());
					recipeInputManSave.setSource(txtRecipeInputManBron.getText());
					recipeInputManSave.setFoto_naam(txtRecipeInputManPhotoName.getText());
					recipeInputManSave.setPrep_tijd(txtRecipeInputManPreptijd.getText());
					recipeInputManSave.setKook_tijd(txtRecipeInputManKooktijd.getText());
					recipeInputManSave.setStappen(txtaRecipeInputManStappen.getText());
					recipeInputManSave.setIngredienten(txtaRecipeInputManIngredients.getText());
					recipeInputManSave.setExtras(txtaRecipeInputManExtras.getText());
					
					// If no image is included - set filename to null. No copy when filename is null.
					if (!txtRecipeInputManPhotoName.getText().isEmpty()) {
						// Copy the photo of recipe to image subfolder
						
						File imageSource = new File(txtRecipeInputManPhotoPath.getText() + "\\" + txtRecipeInputManPhotoName.getText());
						File imageDest = new File("\\images\\recipes\\" + txtRecipeInputManPhotoName.getText());
						try {
						    FileUtils.copyFile(imageSource, imageDest);
						} catch (IOException ioe) {
						    ioe.printStackTrace();
						}
					}

					
					// tags are added to the recipe record and to the tags table in the database
					recipeInputManSave.setTag1(txtRecipeInputManTag1.getText());
					//newTagsLijst.setTag1(value);
					recipeInputManSave.setTag2(txtRecipeInputManTag2.getText());
					//newTagsLijst.setTag2(value);
					recipeInputManSave.setTag3(txtRecipeInputManTag3.getText());
					//newTagsLijst.setTag3(value);
					recipeInputManSave.setTag4(txtRecipeInputManTag4.getText());
					//newTagsLijst.setTag4(value);
					recipeInputManSave.setTag5(txtRecipeInputManTag5.getText());
					//newTagsLijst.setTag5(value);
					recipeInputManSave.setTag6(txtRecipeInputManTag6.getText());
					//newTagsLijst.setTag6(value);
					recipeInputManSave.setTag7(txtRecipeInputManTag7.getText());
					//newTagsLijst.setTag7(value);
					
					ReceptDAO.addRecipe(recipeInputManSave, dbConnection);
					TagsDAO.addTags(recipeInputManSave, dbConnection);
				}
			}
		});

}


	/**
	 * Initialize the contents of the frame.
	 */
	private void componentsInitialize() {
		frmInTheKitchen = new JFrame();
		frmInTheKitchen.setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/icons/InTheKitchenLogo.png")));
		frmInTheKitchen.setTitle("In The Kitchen");
		frmInTheKitchen.setBounds(100, 100, 1104, 825);
		frmInTheKitchen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuMainMenuBar = new JMenuBar();
		frmInTheKitchen.setJMenuBar(menuMainMenuBar);
		
		mnHome = new JMenu("Home");
		mnHome.setIcon(new ImageIcon(MainView.class.getResource("/icons/home.png")));
		menuMainMenuBar.add(mnHome);
		
		mntmOverzicht = new JMenuItem("Overzicht");
		mntmOverzicht.setIcon(new ImageIcon(MainView.class.getResource("/icons/overview.png")));
		mnHome.add(mntmOverzicht);
		
		mnDBReset = new JMenuItem("DBReset");
		mnHome.add(mnDBReset);
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.setIcon(new ImageIcon(MainView.class.getResource("/icons/icon_exit.png")));
		mnHome.add(mntmExit);
		
		mnZoeken = new JMenu("Zoeken");
		menuMainMenuBar.add(mnZoeken);
		
		mnToevoegen = new JMenu("Toevoegen");
		mnToevoegen.setIcon(new ImageIcon(MainView.class.getResource("/icons/toevoegen.png")));
		menuMainMenuBar.add(mnToevoegen);
		
		mntmZelf = new JMenuItem("Zelf");
		mntmZelf.setIcon(new ImageIcon(MainView.class.getResource("/icons/add_self.png")));
		mnToevoegen.add(mntmZelf);
		
		mntmImport = new JMenuItem("Import van Internet");
		mntmImport.setIcon(new ImageIcon(MainView.class.getResource("/icons/page-down.png")));
		mnToevoegen.add(mntmImport);
		
		mntmDocument = new JMenuItem("PDF/MS Doc");
		mntmDocument.setIcon(new ImageIcon(MainView.class.getResource("/icons/document.png")));
		mnToevoegen.add(mntmDocument);
		
		mntmScan = new JMenuItem("Foto scan");
		mntmScan.setIcon(new ImageIcon(MainView.class.getResource("/icons/scan.png")));
		mnToevoegen.add(mntmScan);
		
		mnIngredienten = new JMenu("Ingredienten");
		mnIngredienten.setIcon(new ImageIcon(MainView.class.getResource("/icons/ingredients.png")));
		menuMainMenuBar.add(mnIngredienten);
		
		mnPlanner = new JMenu("Planner");
		mnPlanner.setIcon(new ImageIcon(MainView.class.getResource("/icons/planner.png")));
		menuMainMenuBar.add(mnPlanner);
		
		mnPrint = new JMenu("Print");
		mnPrint.setIcon(new ImageIcon(MainView.class.getResource("/icons/print.png")));
		menuMainMenuBar.add(mnPrint);
		
		mnResetDB = new JMenu("DB Reset");
		menuMainMenuBar.add(mnResetDB);
		
		mnAbout = new JMenu("About");
		mnAbout.setIcon(new ImageIcon(MainView.class.getResource("/icons/Info-icon.png")));
		menuMainMenuBar.add(mnAbout);
		frmInTheKitchen.getContentPane().setLayout(new CardLayout(0, 0));
		
		pnlHome = new JPanel();
		frmInTheKitchen.getContentPane().add(pnlHome, "name_220330865520400");
		
		pnlInfo = new JPanel();
		pnlInfo.setBackground(new Color(224, 255, 255));
		pnlInfo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		pnlFeaturettes = new JPanel();
		pnlFeaturettes.setBorder(null);
		pnlFeaturettes.setBackground(new Color(255, 255, 224));
		
		JLabel lblAanbevolen = new JLabel("Aanbevolen");
		lblAanbevolen.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		GroupLayout gl_pnlHome = new GroupLayout(pnlHome);
		gl_pnlHome.setHorizontalGroup(
			gl_pnlHome.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlHome.createSequentialGroup()
					.addGroup(gl_pnlHome.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlHome.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_pnlHome.createParallelGroup(Alignment.LEADING)
								.addComponent(pnlFeaturettes, GroupLayout.DEFAULT_SIZE, 939, Short.MAX_VALUE)
								.addComponent(pnlInfo, GroupLayout.DEFAULT_SIZE, 939, Short.MAX_VALUE)))
						.addGroup(gl_pnlHome.createSequentialGroup()
							.addGap(371)
							.addComponent(lblAanbevolen)))
					.addContainerGap())
		);
		gl_pnlHome.setVerticalGroup(
			gl_pnlHome.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlHome.createSequentialGroup()
					.addContainerGap()
					.addComponent(pnlInfo, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblAanbevolen)
					.addGap(5)
					.addComponent(pnlFeaturettes, GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		pnlFeaturette_1 = new JPanel();
		
		pnlFeaturette_2 = new JPanel();
		GroupLayout gl_pnlFeaturettes = new GroupLayout(pnlFeaturettes);
		gl_pnlFeaturettes.setHorizontalGroup(
			gl_pnlFeaturettes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFeaturettes.createSequentialGroup()
					.addContainerGap()
					.addComponent(pnlFeaturette_1, GroupLayout.PREFERRED_SIZE, 915, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_pnlFeaturettes.createSequentialGroup()
					.addContainerGap(259, Short.MAX_VALUE)
					.addComponent(pnlFeaturette_2, GroupLayout.PREFERRED_SIZE, 666, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_pnlFeaturettes.setVerticalGroup(
			gl_pnlFeaturettes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFeaturettes.createSequentialGroup()
					.addContainerGap()
					.addComponent(pnlFeaturette_1, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnlFeaturette_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(71, Short.MAX_VALUE))
		);
		
		epFeaturette_2_Text = new JEditorPane();
		epFeaturette_2_Text.setContentType("text/html");
		epFeaturette_2_Text.setText("<html>\r\n  <head>\r\n    <meta charset=\"utf-8\">\r\n    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->\r\n\t\t<!-- Bootstrap -->\r\n\t\t<!-- Bootstrap CSS -->\r\n\t\t<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\">\r\n\t\t<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css\">\r\n\t\t<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.css\" />\r\n    <!-- Latest compiled and minified CSS -->\r\n    <link rel=\"stylesheet\"\r\n        href=\"https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css\">\r\n</head>\r\n\r\n<body>\r\n<h2 class=\"featurette-heading\">Oh yeah, it's that good. <span class=\"text-muted\">See for yourself.</span></h2>\r\n          <p class=\"lead\">Pulled pork op z'n best.</p>\r\n</body></html>");
		epFeaturette_2_Text.setEditable(false);
		
		lblFeaturette_2_Image = new JLabel("");
		lblFeaturette_2_Image.setIcon(new ImageIcon(MainView.class.getResource("/recipe_photos/kitchen/americaans/pulled pork burger.jpg")));
		lblFeaturette_2_Image.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_pnlFeaturette_2 = new GroupLayout(pnlFeaturette_2);
		gl_pnlFeaturette_2.setHorizontalGroup(
			gl_pnlFeaturette_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFeaturette_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(epFeaturette_2_Text, GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(lblFeaturette_2_Image, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_pnlFeaturette_2.setVerticalGroup(
			gl_pnlFeaturette_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFeaturette_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlFeaturette_2.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFeaturette_2_Image, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
						.addComponent(epFeaturette_2_Text))
					.addContainerGap())
		);
		pnlFeaturette_2.setLayout(gl_pnlFeaturette_2);
		
		lblFeaturette_1_Image = new JLabel("");
		lblFeaturette_1_Image.setIcon(new ImageIcon(MainView.class.getResource("/recipe_photos/kitchen/nederlands/hartige wentelteefjes.jpg")));
		lblFeaturette_1_Image.setHorizontalAlignment(SwingConstants.CENTER);
		
		epFeaturette_1_Text = new JEditorPane();
		epFeaturette_1_Text.setContentType("text/html");
		epFeaturette_1_Text.setText("<html>\r\n  <head>\r\n    <meta charset=\"utf-8\">\r\n    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->\r\n\t\t<!-- Bootstrap -->\r\n\t\t<!-- Bootstrap CSS -->\r\n\t\t<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\">\r\n\t\t<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css\">\r\n\t\t<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.css\" />\r\n    <!-- Latest compiled and minified CSS -->\r\n    <link rel=\"stylesheet\"\r\n        href=\"https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css\">\r\n</head>\r\n\r\n<body>\r\n<h2><span class=\"text-muted\">Wentelteefjes. Een simple en lekker tussendoortje.</span></h2>\r\n          <p class=\"lead\">Wentelteefjes zijn heerlijk met een zoete topping, maar ook heel lekker met een hartige topping van bacon, tomaat en kaas.</p>\r\n          <p>Check it out</p>\r\n</body></html>");
		epFeaturette_1_Text.setEditable(false);
		GroupLayout gl_pnlFeaturette_1 = new GroupLayout(pnlFeaturette_1);
		gl_pnlFeaturette_1.setHorizontalGroup(
			gl_pnlFeaturette_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFeaturette_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblFeaturette_1_Image, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(epFeaturette_1_Text, GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
					.addGap(20))
		);
		gl_pnlFeaturette_1.setVerticalGroup(
			gl_pnlFeaturette_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFeaturette_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlFeaturette_1.createParallelGroup(Alignment.LEADING)
						.addComponent(epFeaturette_1_Text, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
						.addComponent(lblFeaturette_1_Image, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		pnlFeaturette_1.setLayout(gl_pnlFeaturette_1);
		pnlFeaturettes.setLayout(gl_pnlFeaturettes);
		
		pnlFirstInfoGroup = new JPanel();
		pnlFirstInfoGroup.setBackground(new Color(255, 245, 238));
		
		pnlSecondInfoGroup = new JPanel();
		pnlSecondInfoGroup.setBackground(new Color(255, 245, 238));
		
		pnlThirdInfoGroup = new JPanel();
		pnlThirdInfoGroup.setBackground(new Color(255, 245, 238));
		GroupLayout gl_pnlInfo = new GroupLayout(pnlInfo);
		gl_pnlInfo.setHorizontalGroup(
			gl_pnlInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlInfo.createSequentialGroup()
					.addContainerGap()
					.addComponent(pnlFirstInfoGroup, GroupLayout.PREFERRED_SIZE, 260, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(pnlSecondInfoGroup, GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
					.addGap(11)
					.addComponent(pnlThirdInfoGroup, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
					.addGap(7))
		);
		gl_pnlInfo.setVerticalGroup(
			gl_pnlInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlInfo.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlInfo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlInfo.createSequentialGroup()
							.addComponent(pnlThirdInfoGroup, GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_pnlInfo.createSequentialGroup()
							.addComponent(pnlSecondInfoGroup, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_pnlInfo.createSequentialGroup()
							.addComponent(pnlFirstInfoGroup, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
							.addContainerGap())))
		);
		
		lblThirdInfoImage = new JLabel("");
		lblThirdInfoImage.setIcon(new ImageIcon(MainView.class.getResource("/images/ITK3_150px.jpg")));
		lblThirdInfoImage.setHorizontalAlignment(SwingConstants.CENTER);
		
		spThirdInfo = new JScrollPane();
		spThirdInfo.setBorder(null);
		spThirdInfo.setOpaque(false);
		GroupLayout gl_pnlThirdInfoGroup = new GroupLayout(pnlThirdInfoGroup);
		gl_pnlThirdInfoGroup.setHorizontalGroup(
			gl_pnlThirdInfoGroup.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlThirdInfoGroup.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlThirdInfoGroup.createParallelGroup(Alignment.LEADING)
						.addComponent(lblThirdInfoImage, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
						.addComponent(spThirdInfo, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_pnlThirdInfoGroup.setVerticalGroup(
			gl_pnlThirdInfoGroup.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlThirdInfoGroup.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblThirdInfoImage, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(spThirdInfo, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
					.addGap(14))
		);
		
		epThirdInfoText = new JEditorPane();
		epThirdInfoText.setBorder(null);
		epThirdInfoText.setOpaque(false);
		epThirdInfoText.setContentType("text/html");
		epThirdInfoText.setText("<html><body>\r\n          <h2>Koken kan best leuk zijn</h2>\r\n          <p>Bezig zijn met eten klaar maken is niet voor iedereen weggelegd.\r\n          Toch kan koken, creatief zijn, best leuk zijn --- of dat een hoogstandje uitgebreid menu wordt of gewoon een bevroren pizza in de oven stoppen.\r\n          Want je eet toch de resultaat uiteidelijk wel lekker op. Een beloning voor dat hard werk, toch? En complimentjes van gezelschap aan tafel.</p>\r\n          <p>Deze keukenla heeft een heleboel leuke en lekkere recepten die de meeste in de familie lekker vindt. Zoek wel eens een recept op om zelf uit te probereb.\r\n          </p>\r\n          <p>LET OP: Paniek in de keuken is geen ramp --- probeer niet te veel tegelijk te doen. Het is tenslotte je eigen creatie! Timing is alles!</p>\r\n          <p><a class=\"btn btn-default\" href=\"https://www.dailybase.nl/thegoodlife/lifestyle-thegoodlife/vijf-redenen-waarom-koken-leuk-is-en-waarom-je-er-een-goede-keuken-voor-nodig-hebt/\" role=\"button\">View &raquo;</a></p>\r\n</body>/<html>");
		epThirdInfoText.setEditable(false);
		spThirdInfo.setViewportView(epThirdInfoText);
		pnlThirdInfoGroup.setLayout(gl_pnlThirdInfoGroup);
		
		spSecondInfo = new JScrollPane();
		spSecondInfo.setBorder(null);
		spSecondInfo.setOpaque(false);
		
		lblSecondInfoImage = new JLabel("");
		lblSecondInfoImage.setIcon(new ImageIcon(MainView.class.getResource("/images/ITK2_115px.jpg")));
		lblSecondInfoImage.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_pnlSecondInfoGroup = new GroupLayout(pnlSecondInfoGroup);
		gl_pnlSecondInfoGroup.setHorizontalGroup(
			gl_pnlSecondInfoGroup.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlSecondInfoGroup.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlSecondInfoGroup.createParallelGroup(Alignment.LEADING)
						.addComponent(spSecondInfo, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
						.addComponent(lblSecondInfoImage, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_pnlSecondInfoGroup.setVerticalGroup(
			gl_pnlSecondInfoGroup.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlSecondInfoGroup.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSecondInfoImage, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(spSecondInfo, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		epSecondInfoText = new JEditorPane();
		epSecondInfoText.setBorder(null);
		epSecondInfoText.setOpaque(false);
		epSecondInfoText.setContentType("text/html");
		epSecondInfoText.setText("<html><body>\r\n          <h2>Gezond</h2>\r\n          <p>Gezond eten is voornaamlijk gevarieerd eten.</p>\r\n          <p>Met gezonde voeding krijgt je lichaam alle voedingsstoffen binnen die het nodig heeft. Dus voldoende eiwitten, koolhydraten, vetten, vezels, vitaminen en mineralen. Gezond eten helpt je lichaam in een goede conditie te houden.\r\n\t\t\t\t\t\tDe volgende 5 regels vormen de basis voor gezonde voeding.\r\n\t\t\t\t\t<ul>\r\n\t\t\t    <li>Eet gevarieerd</li>\r\n\t\t\t    <li>Eet niet te veel, maar ook niet te weinig</li>\r\n\t\t\t    <li>Eet minder verzadigd vet</li>\r\n\t\t\t    <li>Eet veel groente, fruit en brood</li>\r\n\t\t\t    <li>Eet veilig, en ook duurzaam</li>\r\n\t\t\t    </ul>\r\n          <p>Zie de Schijf van Vijf. <a class=\"btn btn-default\" href=\"https://www.voedingscentrum.nl/nl/gezond-eten-met-de-schijf-van-vijf.aspx\" role=\"button\">Schijf van Vijf &raquo;</a></p>\r\n</body></html>");
		epSecondInfoText.setEditable(false);
		spSecondInfo.setViewportView(epSecondInfoText);
		pnlSecondInfoGroup.setLayout(gl_pnlSecondInfoGroup);
		
		lblFirstInfoImage = new JLabel("");
		lblFirstInfoImage.setIcon(new ImageIcon(MainView.class.getResource("/images/ITK1_30pct.jpg")));
		lblFirstInfoImage.setHorizontalAlignment(SwingConstants.CENTER);
		
		spFirstInfo = new JScrollPane();
		spFirstInfo.setBackground(new Color(224, 255, 255));
		spFirstInfo.setBorder(null);
		GroupLayout gl_pnlFirstInfoGroup = new GroupLayout(pnlFirstInfoGroup);
		gl_pnlFirstInfoGroup.setHorizontalGroup(
			gl_pnlFirstInfoGroup.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFirstInfoGroup.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlFirstInfoGroup.createParallelGroup(Alignment.TRAILING)
						.addComponent(spFirstInfo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
						.addComponent(lblFirstInfoImage, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_pnlFirstInfoGroup.setVerticalGroup(
			gl_pnlFirstInfoGroup.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFirstInfoGroup.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblFirstInfoImage, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spFirstInfo, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		epFirstInfoText = new JEditorPane();
		epFirstInfoText.setOpaque(false);
		epFirstInfoText.setBackground(new Color(224, 255, 255));
		epFirstInfoText.setBorder(null);
		epFirstInfoText.setEditable(false);
		epFirstInfoText.setContentType("text/html");
		epFirstInfoText.setText("<html><body>\r\n          <h2>Verschillende meeteenheden</h2>\r\n          <p>Soms in een recept zie je meeteenheden van 'eetlepel' of 'eetlpl' en 'tblspn' of 'tablespoon.\r\n          Hier kan een verschil ontstaan doordat Americaanse recepten gebruiken 'tablespoon' en Europese recepten gebruiken 'eetlepel'.\r\n          Deze zijn in principe hetzelfde.</p>\r\n          <p>Als je verschilende maten wil checken zie de link hieronder.</p>\r\n          <p><a class=\"btn btn-default\" href=\"https://www.zelfmaakrecepten.nl/maten-gewichten/\" role=\"button\">Maten &raquo;</a></p>\r\n\r\n</body></html>");
		spFirstInfo.setViewportView(epFirstInfoText);
		pnlFirstInfoGroup.setLayout(gl_pnlFirstInfoGroup);
		pnlInfo.setLayout(gl_pnlInfo);
		pnlHome.setLayout(gl_pnlHome);
		
		pnlOverzicht = new JPanel();
		frmInTheKitchen.getContentPane().add(pnlOverzicht, "name_220364989791700");
		
		pnlRecipeViewsContainer = new JPanel();
		GroupLayout gl_pnlOverzicht = new GroupLayout(pnlOverzicht);
		gl_pnlOverzicht.setHorizontalGroup(
			gl_pnlOverzicht.createParallelGroup(Alignment.LEADING)
				.addComponent(pnlRecipeViewsContainer, GroupLayout.DEFAULT_SIZE, 959, Short.MAX_VALUE)
		);
		gl_pnlOverzicht.setVerticalGroup(
			gl_pnlOverzicht.createParallelGroup(Alignment.LEADING)
				.addComponent(pnlRecipeViewsContainer, GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
		);
		pnlRecipeViewsContainer.setLayout(new CardLayout(0, 0));
		
		pnlTopView = new JPanel();
		pnlRecipeViewsContainer.add(pnlTopView, "name_8828056843300");
		
		pnlFilterBar = new JPanel();
		
		JScrollPane spRecipeElementList = new JScrollPane();
		GroupLayout gl_pnlTopView = new GroupLayout(pnlTopView);
		gl_pnlTopView.setHorizontalGroup(
			gl_pnlTopView.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_pnlTopView.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlTopView.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlTopView.createSequentialGroup()
							.addGap(10)
							.addComponent(spRecipeElementList, GroupLayout.DEFAULT_SIZE, 929, Short.MAX_VALUE))
						.addComponent(pnlFilterBar, GroupLayout.DEFAULT_SIZE, 939, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_pnlTopView.setVerticalGroup(
			gl_pnlTopView.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlTopView.createSequentialGroup()
					.addComponent(pnlFilterBar, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spRecipeElementList, GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		lstRecipeListElement = new JList<RecipeListElement>(recipeList);
		spRecipeElementList.setViewportView(lstRecipeListElement);
		
		JLabel lblFilters = new JLabel("Filters");
		
		btnAllRecipes = new JButton("Alle recepten");
		
		btnKeuken = new JButton("Keuken");
		
		btnMaalType = new JButton("Soort maaltijd");
		
		btnTheme = new JButton("Thema");
		
		JLabel lblZoek = new JLabel("Zoek op Naam");
		
		txtZoekOpNaam = new JTextField();
		txtZoekOpNaam.setColumns(10);
		
		txtKeukenFilter = new JTextField();
		txtKeukenFilter.setColumns(10);
		
		btnZoek = new JButton("Zoek");
		
		txtMaaltypeFilter = new JTextField();
		txtMaaltypeFilter.setColumns(10);
		
		txtThemaFilter = new JTextField();
		txtThemaFilter.setColumns(10);
		
		btnZoekFilter = new JButton("Zoeken");
		GroupLayout gl_pnlFilterBar = new GroupLayout(pnlFilterBar);
		gl_pnlFilterBar.setHorizontalGroup(
			gl_pnlFilterBar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFilterBar.createSequentialGroup()
					.addGroup(gl_pnlFilterBar.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlFilterBar.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblFilters))
						.addGroup(gl_pnlFilterBar.createSequentialGroup()
							.addComponent(btnAllRecipes)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_pnlFilterBar.createParallelGroup(Alignment.LEADING)
								.addComponent(txtKeukenFilter, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnKeuken))))
					.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
					.addGroup(gl_pnlFilterBar.createParallelGroup(Alignment.LEADING)
						.addComponent(btnMaalType)
						.addComponent(txtMaaltypeFilter, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
					.addGap(41)
					.addGroup(gl_pnlFilterBar.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlFilterBar.createSequentialGroup()
							.addComponent(btnTheme)
							.addGap(150)
							.addGroup(gl_pnlFilterBar.createParallelGroup(Alignment.LEADING)
								.addComponent(lblZoek)
								.addGroup(gl_pnlFilterBar.createSequentialGroup()
									.addComponent(txtZoekOpNaam, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnZoek))))
						.addComponent(txtThemaFilter, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
					.addGap(1))
				.addGroup(gl_pnlFilterBar.createSequentialGroup()
					.addComponent(btnZoekFilter)
					.addContainerGap())
		);
		gl_pnlFilterBar.setVerticalGroup(
			gl_pnlFilterBar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFilterBar.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlFilterBar.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlFilterBar.createSequentialGroup()
							.addComponent(lblZoek)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_pnlFilterBar.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtZoekOpNaam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnZoek)))
						.addGroup(gl_pnlFilterBar.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_pnlFilterBar.createSequentialGroup()
								.addComponent(lblFilters)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_pnlFilterBar.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnAllRecipes)
									.addComponent(btnKeuken)))
							.addGroup(gl_pnlFilterBar.createSequentialGroup()
								.addGap(25)
								.addGroup(gl_pnlFilterBar.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnTheme)
									.addComponent(btnMaalType)))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlFilterBar.createParallelGroup(Alignment.LEADING)
						.addComponent(txtKeukenFilter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtMaaltypeFilter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtThemaFilter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
					.addComponent(btnZoekFilter))
		);
		pnlFilterBar.setLayout(gl_pnlFilterBar);
		pnlTopView.setLayout(gl_pnlTopView);
		
		pnlListView = new JPanel();
		pnlRecipeViewsContainer.add(pnlListView, "name_8890439600300");
		
		pnlDetailView = new JPanel();
		pnlRecipeViewsContainer.add(pnlDetailView, "name_8923887479200");
		
		pnlGroupedView = new JPanel();
		pnlRecipeViewsContainer.add(pnlGroupedView, "name_29579778707800");
		pnlOverzicht.setLayout(gl_pnlOverzicht);
		
		pnlDetails = new JPanel();
		frmInTheKitchen.getContentPane().add(pnlDetails, "name_119937554572100");
		
		spRecipeDetails = new JScrollPane();
		GroupLayout gl_pnlDetails = new GroupLayout(pnlDetails);
		gl_pnlDetails.setHorizontalGroup(
			gl_pnlDetails.createParallelGroup(Alignment.LEADING)
				.addComponent(spRecipeDetails, GroupLayout.DEFAULT_SIZE, 1088, Short.MAX_VALUE)
		);
		gl_pnlDetails.setVerticalGroup(
			gl_pnlDetails.createParallelGroup(Alignment.LEADING)
				.addComponent(spRecipeDetails, GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
		);
		
		JPanel pnlHeader = new JPanel();
		spRecipeDetails.setColumnHeaderView(pnlHeader);
		
		lblCurrentRecipeName = new JLabel("Title");
		lblCurrentRecipeName.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnlHeader.add(lblCurrentRecipeName);
		
		pnlCurrentRecipeContainer = new JPanel();
		spRecipeDetails.setViewportView(pnlCurrentRecipeContainer);
		
		JPanel pnlCurrentRecipeInfo = new JPanel();
		pnlCurrentRecipeInfo.setBackground(new Color(204, 255, 255));
		
		JPanel pnlDivider = new JPanel();
		pnlDivider.setBackground(Color.GRAY);
		
		JPanel pnlCurrentRecipeInstructions = new JPanel();
		pnlCurrentRecipeInstructions.setBackground(new Color(224, 255, 255));
		GroupLayout gl_pnlCurrentRecipeContainer = new GroupLayout(pnlCurrentRecipeContainer);
		gl_pnlCurrentRecipeContainer.setHorizontalGroup(
			gl_pnlCurrentRecipeContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCurrentRecipeContainer.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlCurrentRecipeContainer.createParallelGroup(Alignment.LEADING)
						.addComponent(pnlCurrentRecipeInfo, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 1076, Short.MAX_VALUE)
						.addComponent(pnlDivider, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1076, Short.MAX_VALUE)
						.addComponent(pnlCurrentRecipeInstructions, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_pnlCurrentRecipeContainer.setVerticalGroup(
			gl_pnlCurrentRecipeContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCurrentRecipeContainer.createSequentialGroup()
					.addContainerGap()
					.addComponent(pnlCurrentRecipeInfo, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnlDivider, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(48)
					.addComponent(pnlCurrentRecipeInstructions, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		spCRIngredienten = new JScrollPane();
		
		spCRSteps = new JScrollPane();
		
		lblCRIngredienten = new JLabel("Ingredienten");
		lblCRIngredienten.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		lblCRStappen = new JLabel("Stappen");
		lblCRStappen.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		spCRExtras = new JScrollPane();
		
		lblCRExtras = new JLabel("Extras");
		lblCRExtras.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		lblCRTags = new JLabel("Tags");
		lblCRTags.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		spCRTags = new JScrollPane();
		GroupLayout gl_pnlCurrentRecipeInstructions = new GroupLayout(pnlCurrentRecipeInstructions);
		gl_pnlCurrentRecipeInstructions.setHorizontalGroup(
			gl_pnlCurrentRecipeInstructions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCurrentRecipeInstructions.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlCurrentRecipeInstructions.createParallelGroup(Alignment.LEADING)
						.addComponent(spCRIngredienten, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCRIngredienten))
					.addGap(18)
					.addGroup(gl_pnlCurrentRecipeInstructions.createParallelGroup(Alignment.LEADING)
						.addComponent(spCRSteps, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
						.addComponent(lblCRStappen))
					.addGap(18)
					.addGroup(gl_pnlCurrentRecipeInstructions.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCRExtras)
						.addComponent(spCRExtras, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCRTags)
						.addComponent(spCRTags, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE))
					.addGap(39))
		);
		gl_pnlCurrentRecipeInstructions.setVerticalGroup(
			gl_pnlCurrentRecipeInstructions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCurrentRecipeInstructions.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlCurrentRecipeInstructions.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCRIngredienten)
						.addComponent(lblCRStappen)
						.addComponent(lblCRExtras))
					.addGap(7)
					.addGroup(gl_pnlCurrentRecipeInstructions.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlCurrentRecipeInstructions.createSequentialGroup()
							.addComponent(spCRExtras, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblCRTags)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spCRTags, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
						.addComponent(spCRIngredienten, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
						.addComponent(spCRSteps, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		txtaCurrentRecipeTags = new JTextArea();
		spCRTags.setViewportView(txtaCurrentRecipeTags);
		
		txtaCurrentRecipeExtras = new JTextArea();
		spCRExtras.setViewportView(txtaCurrentRecipeExtras);
		
		txtaCurrentRecipeStappen = new JTextArea();
		spCRSteps.setViewportView(txtaCurrentRecipeStappen);
		
		txtaCurrentRecipeIngredienten = new JTextArea();
		spCRIngredienten.setViewportView(txtaCurrentRecipeIngredienten);
		pnlCurrentRecipeInstructions.setLayout(gl_pnlCurrentRecipeInstructions);
		
		lblCurrentRecipeImage = new JLabel("Image");
		lblCurrentRecipeImage.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblCurrentRecipeImage.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblCurrentRecipeBeschrijving = new JLabel("Beschrijving");
		lblCurrentRecipeBeschrijving.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JScrollPane spCurrentRecipeBeschrijving = new JScrollPane();
		spCurrentRecipeBeschrijving.setBackground(new Color(204, 255, 255));
		
		lblCurrentRecipeNotes = new JLabel("Notes");
		lblCurrentRecipeNotes.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		spCurrentRecipeNotes = new JScrollPane();
		spCurrentRecipeNotes.setBackground(new Color(204, 255, 255));
		
		lblCRKitchen = new JLabel("Keuken:");
		lblCRKitchen.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		lblCurrentRecipeKeuken = new JLabel("Huidige keuken");
		lblCurrentRecipeKeuken.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		
		lblCRType = new JLabel("Type maal:");
		lblCRType.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		lblCurrentRecipeMaaltype = new JLabel("Soort maaltype");
		lblCurrentRecipeMaaltype.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		
		lblCRThema = new JLabel("Thema:");
		lblCRThema.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		lblCurrentRecipeThema = new JLabel("Thema");
		lblCurrentRecipeThema.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		
		lblCRAantalPersonen = new JLabel("Aantal personen:");
		lblCRAantalPersonen.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		lblCurrentRecipeAantalPersonen = new JLabel("New label");
		lblCurrentRecipeAantalPersonen.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		
		lblCRPrep = new JLabel("Prep-tijd: ");
		lblCRPrep.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		lblCurrentRecipePreptime = new JLabel("Prep");
		lblCurrentRecipePreptime.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		
		lblCRBron = new JLabel("Bron:");
		lblCRBron.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		lblCurrentRecipeSource = new JLabel("Bron");
		lblCurrentRecipeSource.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		
		lblCRKook = new JLabel("Kook-tijd:");
		lblCRKook.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		lblCurrentRecipeCooktime = new JLabel("Kook");
		lblCurrentRecipeCooktime.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		
		lblCRVoeding = new JLabel("Voedingswaarden");
		lblCRVoeding.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		lblCREnergie = new JLabel("Energie");
		
		lblCurrentRecipeEnergie = new JLabel("Energie");
		lblCurrentRecipeEnergie.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		
		lblCRSuiker = new JLabel("Suiker:");
		
		lblCurrentRecipeSuiker = new JLabel("Suiker");
		lblCurrentRecipeSuiker.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		
		lblCRKoolhydraten = new JLabel("Koolhydraten:");
		
		lblCurrentRecipeKoolhydraten = new JLabel("New label");
		lblCurrentRecipeKoolhydraten.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		
		lblCRNatrium = new JLabel("Zout:");
		
		lblCurentRecipeNatrium = new JLabel("New label");
		lblCurentRecipeNatrium.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		
		lblCREiwit = new JLabel("Eiwit:");
		
		lblCurrentRecipeEiwit = new JLabel("New label");
		lblCurrentRecipeEiwit.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		
		lblCRVet = new JLabel("Vet:");
		
		lblCurrentRecipeVet = new JLabel("New label");
		lblCurrentRecipeVet.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		
		lblCRVezels = new JLabel("Vezels:");
		
		lblCurrentRecipeVezels = new JLabel("New label");
		lblCurrentRecipeVezels.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		GroupLayout gl_pnlCurrentRecipeInfo = new GroupLayout(pnlCurrentRecipeInfo);
		gl_pnlCurrentRecipeInfo.setHorizontalGroup(
			gl_pnlCurrentRecipeInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCurrentRecipeInfo.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlCurrentRecipeInfo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlCurrentRecipeInfo.createSequentialGroup()
							.addGap(10)
							.addComponent(lblCREnergie)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblCurrentRecipeEnergie)
							.addGap(18)
							.addComponent(lblCRSuiker)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblCurrentRecipeSuiker)
							.addGap(18)
							.addComponent(lblCRKoolhydraten)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblCurrentRecipeKoolhydraten)
							.addGap(18)
							.addComponent(lblCRNatrium)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblCurentRecipeNatrium)
							.addGap(18)
							.addComponent(lblCREiwit)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblCurrentRecipeEiwit)
							.addGap(18)
							.addComponent(lblCRVet)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblCurrentRecipeVet)
							.addGap(18)
							.addComponent(lblCRVezels)
							.addGap(18)
							.addComponent(lblCurrentRecipeVezels)
							.addGap(311))
						.addGroup(gl_pnlCurrentRecipeInfo.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_pnlCurrentRecipeInfo.createSequentialGroup()
								.addGroup(gl_pnlCurrentRecipeInfo.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_pnlCurrentRecipeInfo.createSequentialGroup()
										.addGroup(gl_pnlCurrentRecipeInfo.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_pnlCurrentRecipeInfo.createSequentialGroup()
												.addComponent(lblCurrentRecipeImage, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
												.addGap(10)
												.addGroup(gl_pnlCurrentRecipeInfo.createParallelGroup(Alignment.LEADING)
													.addGroup(gl_pnlCurrentRecipeInfo.createSequentialGroup()
														.addComponent(lblCurrentRecipeBeschrijving)
														.addGap(38))
													.addComponent(spCurrentRecipeBeschrijving, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)))
											.addGroup(gl_pnlCurrentRecipeInfo.createSequentialGroup()
												.addComponent(lblCRKitchen)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(lblCurrentRecipeKeuken, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
												.addGap(53)
												.addComponent(lblCRType)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(lblCurrentRecipeMaaltype, GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE))
											.addGroup(gl_pnlCurrentRecipeInfo.createSequentialGroup()
												.addComponent(lblCRBron)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(lblCurrentRecipeSource, GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)))
										.addGap(30))
									.addGroup(gl_pnlCurrentRecipeInfo.createSequentialGroup()
										.addComponent(lblCRAantalPersonen)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblCurrentRecipeAantalPersonen, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
										.addGap(501)))
								.addGroup(gl_pnlCurrentRecipeInfo.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_pnlCurrentRecipeInfo.createSequentialGroup()
										.addGroup(gl_pnlCurrentRecipeInfo.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_pnlCurrentRecipeInfo.createSequentialGroup()
												.addComponent(lblCRThema)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(lblCurrentRecipeThema, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
											.addComponent(lblCurrentRecipeNotes)
											.addGroup(gl_pnlCurrentRecipeInfo.createSequentialGroup()
												.addComponent(spCurrentRecipeNotes, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
												.addGap(50)))
										.addGap(23))
									.addGroup(gl_pnlCurrentRecipeInfo.createSequentialGroup()
										.addGroup(gl_pnlCurrentRecipeInfo.createParallelGroup(Alignment.TRAILING, false)
											.addGroup(Alignment.LEADING, gl_pnlCurrentRecipeInfo.createSequentialGroup()
												.addComponent(lblCRKook)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(lblCurrentRecipeCooktime, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
											.addGroup(Alignment.LEADING, gl_pnlCurrentRecipeInfo.createSequentialGroup()
												.addComponent(lblCRPrep)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(lblCurrentRecipePreptime, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)))
										.addContainerGap())))
							.addGroup(gl_pnlCurrentRecipeInfo.createSequentialGroup()
								.addComponent(lblCRVoeding)
								.addContainerGap(950, Short.MAX_VALUE)))))
		);
		gl_pnlCurrentRecipeInfo.setVerticalGroup(
			gl_pnlCurrentRecipeInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCurrentRecipeInfo.createSequentialGroup()
					.addGroup(gl_pnlCurrentRecipeInfo.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCurrentRecipeBeschrijving)
						.addComponent(lblCurrentRecipeNotes))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlCurrentRecipeInfo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlCurrentRecipeInfo.createSequentialGroup()
							.addComponent(spCurrentRecipeNotes, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_pnlCurrentRecipeInfo.createSequentialGroup()
							.addComponent(lblCurrentRecipeImage, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addGap(37))
						.addGroup(gl_pnlCurrentRecipeInfo.createSequentialGroup()
							.addComponent(spCurrentRecipeBeschrijving, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_pnlCurrentRecipeInfo.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCRKitchen)
						.addComponent(lblCurrentRecipeKeuken)
						.addComponent(lblCRType)
						.addComponent(lblCurrentRecipeMaaltype)
						.addComponent(lblCRThema)
						.addComponent(lblCurrentRecipeThema))
					.addGap(18)
					.addGroup(gl_pnlCurrentRecipeInfo.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCRAantalPersonen)
						.addComponent(lblCurrentRecipeAantalPersonen)
						.addComponent(lblCRPrep)
						.addComponent(lblCurrentRecipePreptime))
					.addGap(18)
					.addGroup(gl_pnlCurrentRecipeInfo.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCRBron)
						.addComponent(lblCurrentRecipeSource)
						.addComponent(lblCRKook)
						.addComponent(lblCurrentRecipeCooktime))
					.addGap(18)
					.addComponent(lblCRVoeding)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlCurrentRecipeInfo.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCREnergie)
						.addComponent(lblCurrentRecipeEnergie)
						.addComponent(lblCRSuiker)
						.addComponent(lblCurrentRecipeSuiker)
						.addComponent(lblCRKoolhydraten)
						.addComponent(lblCurrentRecipeKoolhydraten)
						.addComponent(lblCRNatrium)
						.addComponent(lblCurentRecipeNatrium)
						.addComponent(lblCREiwit)
						.addComponent(lblCurrentRecipeEiwit)
						.addComponent(lblCRVet)
						.addComponent(lblCurrentRecipeVet)
						.addComponent(lblCRVezels)
						.addComponent(lblCurrentRecipeVezels))
					.addGap(63))
		);
		
		txtaCurrentRecipeNotes = new JTextArea();
		txtaCurrentRecipeNotes.setBackground(new Color(204, 255, 255));
		txtaCurrentRecipeNotes.setWrapStyleWord(true);
		txtaCurrentRecipeNotes.setLineWrap(true);
		spCurrentRecipeNotes.setViewportView(txtaCurrentRecipeNotes);
		
		txtaCurrentRecipeBeschrijving = new JTextArea();
		txtaCurrentRecipeBeschrijving.setBackground(new Color(204, 255, 255));
		txtaCurrentRecipeBeschrijving.setLineWrap(true);
		txtaCurrentRecipeBeschrijving.setWrapStyleWord(true);
		spCurrentRecipeBeschrijving.setViewportView(txtaCurrentRecipeBeschrijving);
		pnlCurrentRecipeInfo.setLayout(gl_pnlCurrentRecipeInfo);
		pnlCurrentRecipeContainer.setLayout(gl_pnlCurrentRecipeContainer);
		pnlDetails.setLayout(gl_pnlDetails);
		
		pnlToevoegen = new JPanel();
		frmInTheKitchen.getContentPane().add(pnlToevoegen, "name_282488984061500");
		
		spRecipeInput = new JScrollPane();
		GroupLayout gl_pnlToevoegen = new GroupLayout(pnlToevoegen);
		gl_pnlToevoegen.setHorizontalGroup(
			gl_pnlToevoegen.createParallelGroup(Alignment.LEADING)
				.addGap(0, 1088, Short.MAX_VALUE)
				.addComponent(spRecipeInput, GroupLayout.DEFAULT_SIZE, 1088, Short.MAX_VALUE)
		);
		gl_pnlToevoegen.setVerticalGroup(
			gl_pnlToevoegen.createParallelGroup(Alignment.LEADING)
				.addGap(0, 764, Short.MAX_VALUE)
				.addComponent(spRecipeInput, GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
		);
		
		pnlRecipeInputManContainer = new JPanel();
		spRecipeInput.setViewportView(pnlRecipeInputManContainer);
		
		JLabel lblRIManImage = new JLabel("Image");
		lblRIManImage.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		btnImageBrowse = new JButton("Browse ...");
		
		lblRecipeImageLabel = new JLabel("Paste");
		lblRecipeImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblRecipeInputManBeschrijving = new JLabel("Beschrijving");
		lblRecipeInputManBeschrijving.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JScrollPane spRecipeInputManBeschrijving = new JScrollPane();
		
		JLabel lblRecipeInputManNotes = new JLabel("Notes");
		lblRecipeInputManNotes.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JScrollPane spRecipeInputManNotes = new JScrollPane();
		
		JLabel lblRecipeInputManKeuken = new JLabel("Keuken");
		lblRecipeInputManKeuken.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		txtRecipeInputManKeuken = new JTextField();
		txtRecipeInputManKeuken.setEditable(false);
		txtRecipeInputManKeuken.setColumns(10);
		
		btnRecipeInputManKeuken = new JButton("Select");
		
		JLabel lblRecipeInputManMaaltype = new JLabel("Soort maal");
		lblRecipeInputManMaaltype.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		txtRecipeInputManMaaltype = new JTextField();
		txtRecipeInputManMaaltype.setEditable(false);
		txtRecipeInputManMaaltype.setColumns(10);
		
		btnRecipeInputManMaaltype = new JButton("Select");
		
		JLabel lblRecipeInputManThema = new JLabel("Thema");
		lblRecipeInputManThema.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		txtRecipeInputManThema = new JTextField();
		txtRecipeInputManThema.setEditable(false);
		txtRecipeInputManThema.setColumns(10);
		
		btnRecipeInputManThema = new JButton("Select");
		
		JLabel lblRecipeInputManAantalpersonen = new JLabel("Aantal personen");
		lblRecipeInputManAantalpersonen.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		txtRecipeInputManAantalPersonen = new JTextField();
		txtRecipeInputManAantalPersonen.setColumns(10);
		
		JLabel lblRecipeInputManPreptijd = new JLabel("Prep-tijd");
		lblRecipeInputManPreptijd.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		txtRecipeInputManPreptijd = new JTextField();
		txtRecipeInputManPreptijd.setColumns(10);
		
		JLabel lblRecipeInputManKooktijd = new JLabel("Kook-tijd");
		lblRecipeInputManKooktijd.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		txtRecipeInputManKooktijd = new JTextField();
		txtRecipeInputManKooktijd.setColumns(10);
		
		JLabel lblRecipeInputManBron = new JLabel("Bron");
		lblRecipeInputManBron.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		txtRecipeInputManBron = new JTextField();
		txtRecipeInputManBron.setColumns(10);
		
		JLabel lblRecipeInputManVoeding = new JLabel("Voedingswaarden");
		lblRecipeInputManVoeding.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblRecipeInputManEnergie = new JLabel("Energie");
		lblRecipeInputManEnergie.setFont(new Font("Tahoma", Font.ITALIC, 11));
		
		txtRecipeInputManEnergie = new JTextField();
		txtRecipeInputManEnergie.setColumns(10);
		
		JLabel lblRecipeInputManSuiker = new JLabel("Suiker");
		lblRecipeInputManSuiker.setFont(new Font("Tahoma", Font.ITALIC, 11));
		
		txtRecipeInputManSuiker = new JTextField();
		txtRecipeInputManSuiker.setColumns(10);
		
		JLabel lblRecipeInputManNatrium = new JLabel("Zout");
		lblRecipeInputManNatrium.setFont(new Font("Tahoma", Font.ITALIC, 11));
		
		txtRecipeInputManNatrium = new JTextField();
		txtRecipeInputManNatrium.setColumns(10);
		
		JLabel lblRecipeInputManKoolhydraten = new JLabel("Koolhydraten");
		lblRecipeInputManKoolhydraten.setFont(new Font("Tahoma", Font.ITALIC, 11));
		
		txtRecipeInputManKoolhydraten = new JTextField();
		txtRecipeInputManKoolhydraten.setColumns(10);
		
		JLabel lblRecipeInputManVet = new JLabel("Vet");
		lblRecipeInputManVet.setFont(new Font("Tahoma", Font.ITALIC, 11));
		
		txtRecipeInputManVet = new JTextField();
		txtRecipeInputManVet.setColumns(10);
		
		JLabel lblRecipeInputManVezels = new JLabel("Vezels");
		lblRecipeInputManVezels.setFont(new Font("Tahoma", Font.ITALIC, 11));
		
		txtRecipeInputManVezels = new JTextField();
		txtRecipeInputManVezels.setColumns(10);
		
		JLabel lblRecipeInputManEiwit = new JLabel("Eiwit");
		lblRecipeInputManEiwit.setFont(new Font("Tahoma", Font.ITALIC, 11));
		
		txtRecipeInputManEiwit = new JTextField();
		txtRecipeInputManEiwit.setColumns(10);
		
		JPanel pnlRecipeInputManDivider = new JPanel();
		pnlRecipeInputManDivider.setBackground(Color.DARK_GRAY);
		
		JLabel lblRecipeInputManIngredients = new JLabel("Ingredi\u00EBnten");
		lblRecipeInputManIngredients.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JScrollPane spRecipeInputManIngredients = new JScrollPane();
		
		JLabel lblRecipeInputManStappen = new JLabel("Stappen");
		lblRecipeInputManStappen.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JScrollPane spRecipeInputManStappen = new JScrollPane();
		
		btnRecipeInputManSave = new JButton("Opslaan");
		btnRecipeInputManSave.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblRIManName = new JLabel("Recept naam:");
		lblRIManName.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		txtRecipeInputManName = new JTextField();
		txtRecipeInputManName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		txtRecipeInputManName.setColumns(10);
		
		txtRecipeInputManPhotoName = new JTextField();
		txtRecipeInputManPhotoName.setEditable(false);
		txtRecipeInputManPhotoName.setVisible(false);
		txtRecipeInputManPhotoName.setColumns(10);
		
		txtRecipeInputManPhotoPath = new JTextField();
		txtRecipeInputManPhotoPath.setVisible(false);
		txtRecipeInputManPhotoPath.setEditable(false);
		txtRecipeInputManPhotoPath.setColumns(10);
		
		btnRecipeInputImp = new JButton("Import");
		btnRecipeInputImp.setBackground(new Color(0, 102, 255));
		
		JLabel lblRecipeInputManExtras = new JLabel("Extras");
		lblRecipeInputManExtras.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblRecipeInputManTags = new JLabel("Tags");
		lblRecipeInputManTags.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		spRecipeInputManExtras = new JScrollPane();
		
		txtRecipeInputManTag2 = new JTextField();
		txtRecipeInputManTag2.setColumns(10);
		
		txtRecipeInputManTag1 = new JTextField();
		txtRecipeInputManTag1.setColumns(10);
		
		txtRecipeInputManTag3 = new JTextField();
		txtRecipeInputManTag3.setColumns(10);
		
		txtRecipeInputManTag4 = new JTextField();
		txtRecipeInputManTag4.setColumns(10);
		
		txtRecipeInputManTag5 = new JTextField();
		txtRecipeInputManTag5.setColumns(10);
		
		txtRecipeInputManTag6 = new JTextField();
		txtRecipeInputManTag6.setColumns(10);
		
		txtRecipeInputManTag7 = new JTextField();
		txtRecipeInputManTag7.setColumns(10);
		
		btnRecipeInputManTags = new JButton("Select");
		GroupLayout gl_pnlRecipeInputManContainer = new GroupLayout(pnlRecipeInputManContainer);
		gl_pnlRecipeInputManContainer.setHorizontalGroup(
			gl_pnlRecipeInputManContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlRecipeInputManContainer.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlRecipeInputManContainer.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlRecipeInputManContainer.createSequentialGroup()
							.addComponent(lblRecipeInputManEnergie)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtRecipeInputManEnergie, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblRecipeInputManSuiker)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtRecipeInputManSuiker, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblRecipeInputManNatrium)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtRecipeInputManNatrium, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblRecipeInputManKoolhydraten)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtRecipeInputManKoolhydraten, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblRecipeInputManVet)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtRecipeInputManVet, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblRecipeInputManVezels)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtRecipeInputManVezels, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblRecipeInputManEiwit)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtRecipeInputManEiwit, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlRecipeInputManContainer.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_pnlRecipeInputManContainer.createSequentialGroup()
								.addComponent(lblRecipeInputManVoeding)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnRecipeInputImp))
							.addGroup(gl_pnlRecipeInputManContainer.createSequentialGroup()
								.addComponent(lblRecipeInputManAantalpersonen)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(txtRecipeInputManAantalPersonen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(lblRecipeInputManPreptijd)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(txtRecipeInputManPreptijd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(lblRecipeInputManKooktijd)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(txtRecipeInputManKooktijd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(lblRecipeInputManBron)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(txtRecipeInputManBron))
							.addGroup(gl_pnlRecipeInputManContainer.createSequentialGroup()
								.addGroup(gl_pnlRecipeInputManContainer.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_pnlRecipeInputManContainer.createSequentialGroup()
										.addComponent(lblRecipeInputManKeuken)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txtRecipeInputManKeuken, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnRecipeInputManKeuken)
										.addGap(18)
										.addComponent(lblRecipeInputManMaaltype)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(txtRecipeInputManMaaltype, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_pnlRecipeInputManContainer.createSequentialGroup()
										.addGroup(gl_pnlRecipeInputManContainer.createParallelGroup(Alignment.LEADING, false)
											.addComponent(lblRecipeImageLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addGroup(gl_pnlRecipeInputManContainer.createSequentialGroup()
												.addComponent(lblRIManImage)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnImageBrowse))
											.addGroup(gl_pnlRecipeInputManContainer.createSequentialGroup()
												.addComponent(txtRecipeInputManPhotoName, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(txtRecipeInputManPhotoPath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
										.addGroup(gl_pnlRecipeInputManContainer.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_pnlRecipeInputManContainer.createSequentialGroup()
												.addGap(63)
												.addComponent(lblRecipeInputManBeschrijving))
											.addGroup(gl_pnlRecipeInputManContainer.createSequentialGroup()
												.addGap(17)
												.addComponent(spRecipeInputManBeschrijving, GroupLayout.PREFERRED_SIZE, 442, GroupLayout.PREFERRED_SIZE)))))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_pnlRecipeInputManContainer.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_pnlRecipeInputManContainer.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_pnlRecipeInputManContainer.createSequentialGroup()
											.addGap(151)
											.addComponent(lblRecipeInputManNotes))
										.addComponent(spRecipeInputManNotes, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))
									.addGroup(gl_pnlRecipeInputManContainer.createSequentialGroup()
										.addComponent(btnRecipeInputManMaaltype)
										.addGap(18)
										.addComponent(lblRecipeInputManThema)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txtRecipeInputManThema, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnRecipeInputManThema))))))
					.addGap(794))
				.addGroup(gl_pnlRecipeInputManContainer.createSequentialGroup()
					.addComponent(pnlRecipeInputManDivider, GroupLayout.DEFAULT_SIZE, 1559, Short.MAX_VALUE)
					.addGap(671))
				.addGroup(gl_pnlRecipeInputManContainer.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlRecipeInputManContainer.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRecipeInputManIngredients)
						.addComponent(spRecipeInputManIngredients, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE))
					.addGap(41)
					.addGroup(gl_pnlRecipeInputManContainer.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRecipeInputManStappen)
						.addComponent(spRecipeInputManStappen, GroupLayout.PREFERRED_SIZE, 423, GroupLayout.PREFERRED_SIZE))
					.addGap(50)
					.addGroup(gl_pnlRecipeInputManContainer.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRecipeInputManExtras, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(spRecipeInputManExtras, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRecipeInputManTags, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnlRecipeInputManContainer.createSequentialGroup()
							.addComponent(txtRecipeInputManTag1, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btnRecipeInputManTags, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtRecipeInputManTag2, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtRecipeInputManTag3, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtRecipeInputManTag4, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtRecipeInputManTag5, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtRecipeInputManTag6, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtRecipeInputManTag7, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(1229, Short.MAX_VALUE))
				.addGroup(gl_pnlRecipeInputManContainer.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRIManName, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(txtRecipeInputManName, GroupLayout.PREFERRED_SIZE, 481, GroupLayout.PREFERRED_SIZE)
					.addGap(307)
					.addComponent(btnRecipeInputManSave, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(1225, Short.MAX_VALUE))
		);
		gl_pnlRecipeInputManContainer.setVerticalGroup(
			gl_pnlRecipeInputManContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlRecipeInputManContainer.createSequentialGroup()
					.addGroup(gl_pnlRecipeInputManContainer.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlRecipeInputManContainer.createSequentialGroup()
							.addGap(3)
							.addComponent(lblRIManName, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlRecipeInputManContainer.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtRecipeInputManName, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnRecipeInputManSave, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlRecipeInputManContainer.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRIManImage)
						.addComponent(btnImageBrowse)
						.addComponent(lblRecipeInputManBeschrijving)
						.addComponent(lblRecipeInputManNotes))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlRecipeInputManContainer.createParallelGroup(Alignment.LEADING)
						.addComponent(spRecipeInputManNotes, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
						.addComponent(spRecipeInputManBeschrijving, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
						.addGroup(gl_pnlRecipeInputManContainer.createSequentialGroup()
							.addComponent(lblRecipeImageLabel, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
							.addGroup(gl_pnlRecipeInputManContainer.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtRecipeInputManPhotoName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtRecipeInputManPhotoPath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlRecipeInputManContainer.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRecipeInputManKeuken)
						.addComponent(txtRecipeInputManKeuken, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRecipeInputManKeuken)
						.addComponent(lblRecipeInputManMaaltype)
						.addComponent(txtRecipeInputManMaaltype, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRecipeInputManMaaltype)
						.addComponent(lblRecipeInputManThema)
						.addComponent(txtRecipeInputManThema, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRecipeInputManThema))
					.addGap(18)
					.addGroup(gl_pnlRecipeInputManContainer.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRecipeInputManAantalpersonen)
						.addComponent(txtRecipeInputManAantalPersonen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRecipeInputManPreptijd)
						.addComponent(txtRecipeInputManPreptijd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRecipeInputManKooktijd)
						.addComponent(txtRecipeInputManKooktijd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRecipeInputManBron)
						.addComponent(txtRecipeInputManBron, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlRecipeInputManContainer.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblRecipeInputManVoeding)
						.addComponent(btnRecipeInputImp))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlRecipeInputManContainer.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRecipeInputManEnergie)
						.addComponent(txtRecipeInputManEnergie, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRecipeInputManSuiker)
						.addComponent(txtRecipeInputManSuiker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRecipeInputManNatrium)
						.addComponent(txtRecipeInputManNatrium, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRecipeInputManKoolhydraten)
						.addComponent(txtRecipeInputManKoolhydraten, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRecipeInputManVet)
						.addComponent(txtRecipeInputManVet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRecipeInputManVezels)
						.addComponent(txtRecipeInputManVezels, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRecipeInputManEiwit)
						.addComponent(txtRecipeInputManEiwit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnlRecipeInputManDivider, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnlRecipeInputManContainer.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRecipeInputManIngredients)
						.addComponent(lblRecipeInputManStappen))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlRecipeInputManContainer.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlRecipeInputManContainer.createSequentialGroup()
							.addComponent(lblRecipeInputManExtras, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(spRecipeInputManExtras, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(lblRecipeInputManTags, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addGroup(gl_pnlRecipeInputManContainer.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlRecipeInputManContainer.createSequentialGroup()
									.addGap(1)
									.addComponent(txtRecipeInputManTag1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnRecipeInputManTags))
							.addGap(6)
							.addComponent(txtRecipeInputManTag2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(txtRecipeInputManTag3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(txtRecipeInputManTag4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(txtRecipeInputManTag5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(txtRecipeInputManTag6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(txtRecipeInputManTag7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_pnlRecipeInputManContainer.createSequentialGroup()
							.addGroup(gl_pnlRecipeInputManContainer.createParallelGroup(Alignment.TRAILING)
								.addComponent(spRecipeInputManStappen, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
								.addComponent(spRecipeInputManIngredients, GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE))
							.addGap(62))))
		);
		
		txtaRecipeInputManExtras = new JTextArea();
		spRecipeInputManExtras.setViewportView(txtaRecipeInputManExtras);
		
		txtaRecipeInputManStappen = new JTextArea();
		txtaRecipeInputManStappen.setWrapStyleWord(true);
		txtaRecipeInputManStappen.setLineWrap(true);
		spRecipeInputManStappen.setViewportView(txtaRecipeInputManStappen);
		
		txtaRecipeInputManIngredients = new JTextArea();
		txtaRecipeInputManIngredients.setWrapStyleWord(true);
		txtaRecipeInputManIngredients.setLineWrap(true);
		spRecipeInputManIngredients.setViewportView(txtaRecipeInputManIngredients);
		
		txtaRecipeInputManNotes = new JTextArea();
		txtaRecipeInputManNotes.setWrapStyleWord(true);
		txtaRecipeInputManNotes.setLineWrap(true);
		spRecipeInputManNotes.setViewportView(txtaRecipeInputManNotes);
		
		txtaRecipeInputManBeschrijving = new JTextArea();
		txtaRecipeInputManBeschrijving.setWrapStyleWord(true);
		txtaRecipeInputManBeschrijving.setLineWrap(true);
		spRecipeInputManBeschrijving.setViewportView(txtaRecipeInputManBeschrijving);
		pnlRecipeInputManContainer.setLayout(gl_pnlRecipeInputManContainer);
		pnlToevoegen.setLayout(gl_pnlToevoegen);

	}
}
