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
    private JTextField bandField;
    
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
        this.setSize(275,500);
        this.setTitle("Create");
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

	public String getBandField() {
		return bandField.getText();
	}

	public void setBandField(JTextField bandField) {
		this.bandField = bandField;
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
	
	// getters for text fields of band, genre and director
	public JTextField getBandTextField() {
		return bandField;
	}
	
	// method to organise components of the window
    private void components(){
        JPanel panel = new JPanel();
        this.add(panel);
        
        // creating text fields and labels
        JLabel titleLabel, typeLabel, yearOfReleaseLabel, formatLabel, priceLabel, bandLabel; 
        titleLabel = new JLabel("Title");
        typeLabel = new JLabel("Type");
        yearOfReleaseLabel = new JLabel("Year Of Release");  
        formatLabel = new JLabel("Format");
        priceLabel = new JLabel("Price");
        bandLabel = new JLabel("Band");
        titleField = new JTextField(20);
        yearOfReleaseField = new JTextField(20);
        priceField = new JTextField(20);
        bandField = new JTextField(20);
        
        // set some fields unable to edit
        bandField.setEnabled(false);
    
        // drop down to select type
        String typeOptions[]={"","Music","Live Concert Videos","Movie","Box Set"};        
        this.typeDropdown = new JComboBox(typeOptions);
        typeDropdown.addActionListener((ActionListener) controller);
        typeDropdown.setActionCommand("select-type");
        
        // drop down to select format
        String formatOptions[]={"","CD","DVD","Blu-Ray"};        
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
        panel.add(bandLabel);
        panel.add(bandField);
        panel.add(createCustomerButton);
    }
    
    // validation and repainting
    private void validation(){
        this.validate();
        this.repaint();
    }
}
