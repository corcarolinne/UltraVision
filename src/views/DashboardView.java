package views;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controllers.DashboardController;
import models.Customer;
import models.Title;

public class DashboardView extends JFrame {

	// properties
	private JPanel panel;
	private DashboardController controller;
	private JTextField searchTextField;
	private JComboBox dropdown;
	private JTable availableTitlesTable;
	private JTable rentedTitlesTable;
	private JTable customersTable;
	public String[][] availableTitlesData;
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
        this.setSize(1000,1000);
        this.setTitle("Ultra Vision System");
	}
	public void componentsSetter() {
		
		// creating panel
		panel = new JPanel();
		//panel.setLayout();
    	this.add(panel);
    	
    	// adding button
    	JButton addRentButton = new JButton("Rent");
    	addRentButton.addActionListener((ActionListener) controller);
    	addRentButton.setActionCommand("add-rent");
    	panel.add(addRentButton);
    	
    	// adding button
    	JButton addReturnButton = new JButton("Return");
    	addReturnButton.addActionListener((ActionListener) controller);
    	addReturnButton.setActionCommand("add-return");
    	panel.add(addReturnButton);
    	
    	// adding button
    	JButton addCustomerButton = new JButton("Add Customer");
    	addCustomerButton.addActionListener((ActionListener) controller);
    	addCustomerButton.setActionCommand("add-customer");
    	panel.add(addCustomerButton);
    	
    	// adding button
    	JButton addTitleButton = new JButton("Add Title");
    	addTitleButton.addActionListener((ActionListener) controller);
    	addTitleButton.setActionCommand("add-title");
    	panel.add(addTitleButton);
    	
    	// adding button
    	JButton updateCustomerPageButton = new JButton("Update Customer");
    	updateCustomerPageButton.addActionListener((ActionListener) controller);
    	updateCustomerPageButton.setActionCommand("update-customer-page");
    	panel.add(updateCustomerPageButton);
    	
    	// search label and input
        JLabel searchLabel = new JLabel("Search:");        
        searchTextField = new JTextField(20);
        panel.add(searchLabel);
        panel.add(searchTextField);
        
        // filter/search drop down
        String filterOptions[]={"","Titles","Customers"};        
        this.dropdown = new JComboBox(filterOptions);
        dropdown.addActionListener((ActionListener) controller);
        dropdown.setActionCommand("filter");
        panel.add(dropdown);
        
	}
	
	// method to create tables for view 
	public void tablesSetter() {
    	
		// creating header
   	 	String[] titlesHeader = {"ID","Title", "Type","Year Of Release", "Genre", "Format", "Price", "Director", "Artist"};
   	
   	 	// calling method from model to show available titles
   	 	availableTitlesData = controller.titleModel.showAvailableTitles("","");
   	 
   	 	// creating table and adding it to the panel
   	 	availableTitlesTable = new JTable(availableTitlesData, titlesHeader);
        panel.add(availableTitlesTable);
   
        // scroll
        JScrollPane scroll = new JScrollPane(availableTitlesTable);
        scroll.setBorder(BorderFactory.createTitledBorder ("Available Titles"));
        panel.add(scroll);
        		
    	// calling method from model to show rented titles
    	rentedTitlesData = controller.titleModel.showRentedTitles("","");
    	 
		 // creating table and adding it to the panel
		 rentedTitlesTable = new JTable(rentedTitlesData, titlesHeader);
		 panel.add(rentedTitlesTable);
    
		 // scroll
		 JScrollPane scroll2 = new JScrollPane(rentedTitlesTable);
		 scroll2.setBorder(BorderFactory.createTitledBorder ("In Transit"));
		 panel.add(scroll2);    
    	
    	// creating header
   	 	String[] customersHeader = {"ID", "First Name", "Last Name","Address", "Email", "Phone", "Card Number", "Membership", "Loyalty Points"};
   	 	
    	// calling method from model to show available titles
    	customersData = controller.customerModel.showCustomers("","");
    	 
		// creating table and adding it to the panel
		customersTable = new JTable(customersData, customersHeader);
		panel.add(customersTable);
    
		// scroll
		JScrollPane scroll3 = new JScrollPane(customersTable);
		scroll3.setBorder(BorderFactory.createTitledBorder ("Customers"));
		panel.add(scroll3);    
	}
	
	// getters for search 
    public String getSearchInput() {
         return this.searchTextField.getText();
    }
    public String getDropdownItem() {
        return this.dropdown.getSelectedItem().toString();
    }
    
    // method to pick title selected available titles table
    public Title getAvailableTitle() {
    	// creates a new instance of Title
    	Title selectedTitle = new Title(this.controller);
    	// save index from row in a variable
    	int selectedTitleIndex = availableTitlesTable.getSelectedRow();
    	// if something is selected
    	if (selectedTitleIndex > -1) {
    		// creates array to store selected row inside 2d array
    		String[] selectedTitlesArray = availableTitlesData[selectedTitleIndex];
    		// using setters to pass the values in the row to the Title object
    		selectedTitle.setID(Integer.parseInt(selectedTitlesArray[0]));
    		selectedTitle.setTitleName(selectedTitlesArray[1]);
    		selectedTitle.setType(selectedTitlesArray[2]);
    		selectedTitle.setYearOfRelease(selectedTitlesArray[3]);
    		selectedTitle.setGenre(selectedTitlesArray[4]);
    		selectedTitle.setFormat(selectedTitlesArray[5]);
    		selectedTitle.setPrice(Double.parseDouble(selectedTitlesArray[6]));
    		selectedTitle.setDirector(selectedTitlesArray[7]);
    		selectedTitle.setArtist(selectedTitlesArray[8]);
       }
       return selectedTitle;
    }
    
    // method to pick title selected on in transit table
    public Title getRentedTitle() {
    	// creates a new instance of Title
    	Title selectedTitle = new Title(this.controller);
    	// save index from row in a variable
    	int selectedTitleIndex = rentedTitlesTable.getSelectedRow();
    	// if something is selected
    	if (selectedTitleIndex > -1) {
    		// creates array to store selected row inside 2d array
    		String[] selectedTitlesArray = rentedTitlesData[selectedTitleIndex];
    		// using setters to pass the values in the row to the Title object
    		selectedTitle.setID(Integer.parseInt(selectedTitlesArray[0]));
    		selectedTitle.setTitleName(selectedTitlesArray[1]);
    		selectedTitle.setType(selectedTitlesArray[2]);
    		selectedTitle.setYearOfRelease(selectedTitlesArray[3]);
    		selectedTitle.setGenre(selectedTitlesArray[4]);
    		selectedTitle.setFormat(selectedTitlesArray[5]);
    		selectedTitle.setPrice(Double.parseDouble(selectedTitlesArray[6]));
    		selectedTitle.setDirector(selectedTitlesArray[7]);
    		selectedTitle.setArtist(selectedTitlesArray[8]);
       }
       return selectedTitle;
   }
    
    // method to pick customer selected from table
    public Customer getSelectedCustomer() {
        // creates a new instance of Customer
    	Customer selectedCustomer = new Customer(this.controller);
       // save index from row in a variable
       int selectedCustomerIndex = customersTable.getSelectedRow();
       // if something is selected
       if (selectedCustomerIndex > -1) {
           // creates array to store selected row inside 2d array
           String[] selectedCustomersArray = customersData[selectedCustomerIndex];
           // using setters to pass the values in the row to the Customer object
           selectedCustomer.setID(Integer.parseInt(selectedCustomersArray[0]));
           selectedCustomer.setFirstName(selectedCustomersArray[1]);
           selectedCustomer.setLastName(selectedCustomersArray[2]);
           selectedCustomer.setAddress(selectedCustomersArray[3]);
           selectedCustomer.setEmail(selectedCustomersArray[4]);
           selectedCustomer.setPhoneNumber(selectedCustomersArray[5]);
           selectedCustomer.setCardNumber(selectedCustomersArray[6]);
           // to select membership
           if(selectedCustomersArray[7].equals("Music Lovers")) {
        	   selectedCustomer.setMembership(1);
           } else if(selectedCustomersArray[7].equals("Video Lovers")) {
        	   selectedCustomer.setMembership(2);
           } else if(selectedCustomersArray[7].equals("TV Lovers")) {
        	   selectedCustomer.setMembership(3);
           } else if(selectedCustomersArray[7].equals("Premium")) {
        	   selectedCustomer.setMembership(4);
           }
       }
       return selectedCustomer;
   }
	
    // validation and repainting
    private void validation(){
        this.validate();
        this.repaint();
    }
}
