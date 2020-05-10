package views;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.DashboardController;

public class CreateTitleView extends JFrame{
	// properties
    private JTextField titleField;
    private JComboBox typeDropdown;
    private JTextField yearOfReleaseField;
    private JComboBox formatDropdown;
    private JTextField priceField;
    private JTextField artistField;
    private JTextField genreField;
    private JTextField directorField;
    private DashboardController controller;
    
    // constructor
    public CreateTitleView(DashboardController controller) {

        this.controller= controller;
        // calling methods to make the window or the view
        attributesSetter();
        components();
        validation();
    }
  
    // method to set attributes
    private void attributesSetter(){
        this.setVisible(true);
        this.setSize(410,400);
        this.setTitle("Create Title");
    }

    // getters and setters
	public String getTitleField() {
		return titleField.getText();
	}

	public String getYearOfReleaseField() {
		return yearOfReleaseField.getText();
	}

	public String getPriceField() {
		return priceField.getText();
	}

	public DashboardController getController() {
		return controller;
	}

	public String getArtistField() {
		return artistField.getText();
	}
	
	public String getGenreField() {
		return genreField.getText();
	}

	public String getDirectorField() {
		return directorField.getText();
	}

	public void setGenreField(JTextField genreField) {
		this.genreField = genreField;
	}

	public void setDirectorField(JTextField directorField) {
		this.directorField = directorField;
	}

	public void setArtistField(JTextField artistField) {
		this.artistField = artistField;
	}

	public void setTitleField(JTextField titleField) {
		this.titleField = titleField;
	}

	public void setTypeDropdown(JComboBox typeDropdown) {
		this.typeDropdown = typeDropdown;
	}

	public void setYearOfReleaseField(JTextField yearOfReleaseField) {
		this.yearOfReleaseField = yearOfReleaseField;
	}

	public void setFormatDropdown(JComboBox formatDropdown) {
		this.formatDropdown = formatDropdown;
	}

	public void setPriceField(JTextField priceField) {
		this.priceField = priceField;
	}

	public void setController(DashboardController controller) {
		this.controller = controller;
	}
    
	// getters for drop down
	public String getTypeDropdownItem() {
		return this.typeDropdown.getSelectedItem().toString();
	}
	public String getFormatDropdownItem() {
		return this.formatDropdown.getSelectedItem().toString();
	}
	
	// getters for text field itself of band, genre and director
	public JTextField getArtistFieldComponent() {
		return artistField;
	}
	public JTextField getGenreFieldComponent() {
		return genreField;
	}
	public JTextField getDirectorFieldComponent() {
		return directorField;
	}
	
	// method to organise components of the window
    private void components(){
        JPanel panel = new JPanel();
        this.add(panel);
        
        // creating text fields and labels
        JLabel titleLabel, typeLabel, yearOfReleaseLabel, formatLabel, priceLabel, artistLabel, genreLabel, directorLabel; 
        titleLabel = new JLabel("Title");
        typeLabel = new JLabel("Type");
        yearOfReleaseLabel = new JLabel("Year Of Release");  
        formatLabel = new JLabel("Format");
        priceLabel = new JLabel("Price");
        artistLabel = new JLabel("Artist");
        genreLabel = new JLabel("Genre");
        directorLabel = new JLabel("Director");
        titleField = new JTextField(30);
        yearOfReleaseField = new JTextField(10);
        priceField = new JTextField(20);
        artistField = new JTextField(15);
        genreField = new JTextField(10);
        directorField = new JTextField(30);
        
        // set some fields unable to edit
        this.artistField.setEnabled(false);
        this.genreField.setEnabled(false);
        this.directorField.setEnabled(false);
    
        // drop down to select type
        String typeOptions[]={"Select","Music","Live Concert","Movie","Box Set"};        
        this.typeDropdown = new JComboBox(typeOptions);
        typeDropdown.addActionListener((ActionListener) controller);
        typeDropdown.setActionCommand("select-type");
        
        // drop down to select format
        String formatOptions[]={"Select","CD","DVD","Blu-Ray"};        
        this.formatDropdown = new JComboBox(formatOptions);
        formatDropdown.addActionListener((ActionListener) controller);
        formatDropdown.setActionCommand("select-format");
        
        // button
        JButton createCustomerButton = new JButton("Create Title");
        createCustomerButton.addActionListener((ActionListener) controller);
        createCustomerButton.setActionCommand("create-title");
        
        // adding components to the panel
        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(typeLabel);
        panel.add(typeDropdown);
        panel.add(yearOfReleaseLabel);
        panel.add(yearOfReleaseField);
        panel.add(formatLabel);
        panel.add(formatDropdown);
        panel.add(priceLabel);
        panel.add(priceField);
        panel.add(artistLabel);
        panel.add(artistField);
        panel.add(genreLabel);
        panel.add(genreField);
        panel.add(directorLabel);
        panel.add(directorField);
        panel.add(createCustomerButton);
    }
    
    // validation and repainting
    private void validation(){
        this.validate();
        this.repaint();
    }
}
