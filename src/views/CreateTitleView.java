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
	public JTextField getTitleField() {
		return titleField;
	}

	public JComboBox getTypeDropdown() {
		return typeDropdown;
	}

	public JTextField getYearOfReleaseField() {
		return yearOfReleaseField;
	}

	public JComboBox getFormatDropdown() {
		return formatDropdown;
	}

	public JTextField getPriceField() {
		return priceField;
	}

	public DashboardController getController() {
		return controller;
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
	
	// method to organise components of the window
    private void components(){
        JPanel panel = new JPanel();
        this.add(panel);
        
        // creating text fields and labels
        JLabel titleLabel, typeLabel, yearOfReleaseLabel, formatLabel, priceLabel; 
        titleLabel = new JLabel("Title");
        typeLabel = new JLabel("Type");
        yearOfReleaseLabel = new JLabel("Year Of Release");  
        formatLabel = new JLabel("Format");
        priceLabel = new JLabel("Price");
        titleField = new JTextField(20);
        yearOfReleaseField = new JTextField(20);
        priceField = new JTextField(20);
    
        // drop down to select type
        String typeOptions[]={"","Music","Live Concert Videos","Movies","Box Set"};        
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
        panel.add(createCustomerButton);
    }
    
    // validation and repainting
    private void validation(){
        this.validate();
        this.repaint();
    }
}
