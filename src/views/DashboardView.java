package views;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controllers.DashboardController;

public class DashboardView extends JFrame {

	// properties
	private JPanel panel;
	private DashboardController controller;
	private JTextField searchTextField;
	private JComboBox dropdown;
	private JTable availableTitlesTable;
	private JTable rentedTitlesTable;
	private JTable customersTable;
	public String[][] availabletitlesData;
	public String[][] rentedTitlesData;
	public String[][] customersData;
    
	
	// constructor
	public DashboardView(DashboardController controller) {
		this.controller = controller;
		this.attributesSetter();
        this.componentsSetter();
    	this.tablesSetter();
    	this.validation();
	}
	
	public void attributesSetter() {
		// basic window properties
		this.setVisible(true);
        this.setSize(1500,550);
        this.setTitle("Ultra Vision System");
	}
	public void componentsSetter() {
		// creating panel
		panel = new JPanel();
    	this.add(panel);
    	
    	// creating label
    	JLabel brandLabel = new JLabel("Ultra Vision"); 
    	panel.add(brandLabel);
		
    	// adding a button
    	JButton addRentButton = new JButton("Add Rent");
    	addRentButton.addActionListener((ActionListener) controller);
    	addRentButton.setActionCommand("add-rent");
    	panel.add(addRentButton);
    	
    	// search label and input
        JLabel searchLabel = new JLabel("Search:");        
        searchTextField = new JTextField(20);
        panel.add(searchLabel);
        panel.add(searchTextField);
        
        // filter drop down
        String filterOptions[]={"","Titles","Customers"};        
        this.dropdown = new JComboBox(filterOptions);
        dropdown.addActionListener((ActionListener) controller);
        dropdown.setActionCommand("filter");
        panel.add(dropdown);
        
	}
	// method to create tables for view 
	public void tablesSetter() {
		// creating label for table
    	JLabel availableTitlesLabel = new JLabel("Available Titles"); 
    	panel.add(availableTitlesLabel);
    	
		// creating header
   	 	String[] titlesHeader = {"Title", "Type","Year Of Release", "Genre", "Format", "Price", "Director", "Band"};
   	
   	 	// calling method from model to show available titles
   	 	availabletitlesData = controller.titleModel.showAvailableTitles("","");
   	 
   	 	// creating table and adding it to the panel
   	 	availableTitlesTable = new JTable(availabletitlesData, titlesHeader);
        panel.add(availableTitlesTable);
   
        // scroll
        JScrollPane scroll = new JScrollPane(availableTitlesTable);
        panel.add(scroll);
        
        // creating label for table
    	JLabel rentedTitlesLabel = new JLabel("In Transit"); 
    	panel.add(rentedTitlesLabel);
        		
    	// calling method from model to show available titles
    	rentedTitlesData = controller.titleModel.showRentedTitles("","");
    	 
		 // creating table and adding it to the panel
		 rentedTitlesTable = new JTable(rentedTitlesData, titlesHeader);
		 panel.add(rentedTitlesTable);
    
		 // scroll
		 JScrollPane scroll2 = new JScrollPane(rentedTitlesTable);
		 panel.add(scroll2);    
		 
		// creating label for table
    	JLabel customersLabel = new JLabel("Customers"); 
    	panel.add(customersLabel);
    	
    	// creating header
   	 	String[] customersHeader = {"First Name", "Last Name","Address", "Email", "Phone", "Card Number", "Membership", "Loyalty Points"};
   	 	
    	// calling method from model to show available titles
    	customersData = controller.customerModel.showCustomers("","");
    	 
		 // creating table and adding it to the panel
		 customersTable = new JTable(customersData, customersHeader);
		 panel.add(customersTable);
    
		 // scroll
		 JScrollPane scroll3 = new JScrollPane(customersTable);
		 panel.add(scroll3);    
	}
	
	// getters for search 
    public String getSearchInput() {
         return this.searchTextField.getText();
    }
    
    public String getDropdownItem() {
        return this.dropdown.getSelectedItem().toString();
    }
	
    // validation and repainting
    private void validation(){
        this.validate();
        this.repaint();
    }
}
