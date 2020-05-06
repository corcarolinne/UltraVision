package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import models.Customer;
import models.Title;
import views.CreateCustomerView;
import views.CreateTitleView;
import views.DashboardView;
import views.SearchResultsView;

public class DashboardController implements ActionListener {
	
	// properties
	public Title titleModel;
	public Customer customerModel;
    public DashboardView view;
    String selectedFilter;
    String searchInput;
    String[][] searchResult;
    SearchResultsView searchResultsView;
	CreateCustomerView createCustomerView;
	String selectedMembership;
	private int membershipID;
	CreateTitleView createTitleView;
	String selectedType;
	String selectedFormat;
	private String type;
	private String format;
	
	
	// constructor
    public DashboardController(){
        titleModel = new Title();
        customerModel = new Customer();
        view = new DashboardView(this);
       
    }
    
    // some getters and setters
	public int getMembershipID() {
		return this.membershipID;
	}
	public String getType() {
		return this.type;
	}
	public String getFormat() {
		return this.format;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// if a certain button is clicked do the actions inside the brackets
        if(e.getActionCommand().equals("add-customer")){
        	createCustomerView = new CreateCustomerView(this);
        } else if(e.getActionCommand().equals("select-membership")) {
        	this.selectedMembership =  createCustomerView.getDropdownItem();
        	// setting membership ID according to selected drop down
        	if(createCustomerView.getDropdownItem().equals("Music Lovers")) {
        		this.membershipID = 1;
        	} else if (createCustomerView.getDropdownItem().equals("Video Lovers")) {
        		this.membershipID = 2;
        	} else if (createCustomerView.getDropdownItem().equals("TV Lovers")) {
        		this.membershipID = 3;
        	} else if (createCustomerView.getDropdownItem().equals("Premium")) {
        		this.membershipID = 4;
        	}
        } else if(e.getActionCommand().equals("create-customer")) {
        	String firstName = createCustomerView.getFirstNameField();
            String lastName = createCustomerView.getLastNameField();
            String address = createCustomerView.getAddressField();
            String email = createCustomerView.getEmailField();
            String phone = createCustomerView.getPhoneField();
            String cardNumber = createCustomerView.getCardNumberField();
            int membership = this.membershipID;
            // create an instance of the customer class with the data collated
            Customer newCustomer = new Customer(firstName, lastName, address, email, phone, cardNumber, membership, this);
            // using model to call method
            this.customerModel.createCustomer(newCustomer);
            // dispose current view and call a new one to refresh table
            view.dispose();
            view = new DashboardView(this);
            createCustomerView.dispose();
	        
         } else if(e.getActionCommand().equals("add-title")){
         	createTitleView = new CreateTitleView(this);
         } else if(e.getActionCommand().equals("select-type")) {
         	this.selectedType =  createTitleView.getTypeDropdownItem();
         	if(createTitleView.getTypeDropdownItem().equals("Music")) {
         		// insert band text field on create title view
         		this.type = "Music";
         		createTitleView.getBandTextField().setEnabled(true);
         	} else if (createTitleView.getTypeDropdownItem().equals("Live Concert Videos")) {
         		// insert band text field on create title view
         		System.out.println("Test Live Concert");
         	} else if (createTitleView.getTypeDropdownItem().equals("Movie")) {
         		// insert genre, director text fields on create title view
         		System.out.println("Test Movie");
         	} else if (createTitleView.getTypeDropdownItem().equals("Box Set")) {
         		// insert genre, director text fields on create title view
         		System.out.println("Test Box Set");         	
         	}
         } else if(e.getActionCommand().equals("select-format")) {
          	this.selectedFormat =  createTitleView.getFormatDropdownItem();
          	if(createTitleView.getFormatDropdownItem().equals("CD")) {
          		this.format = "CD";
          	} else if (createTitleView.getFormatDropdownItem().equals("DVD")) {
          		this.format = "DVD";
          	} else if (createTitleView.getFormatDropdownItem().equals("Blu-Ray")) {
          		this.format = "Blu-Ray";
          	} 
         } else if(e.getActionCommand().equals("create-title")) {
        	String titleName = createTitleView.getTitleField();
        	String type = this.type;
            String yearOfRelease = createTitleView.getYearOfReleaseField();
            String format = this.format;
            String price = createTitleView.getPriceField();
            double priceAsDouble = Double.parseDouble(price);
            boolean isAvailable = true;
            String band = createTitleView.getBandField();
            // create an instance of the customer class with the data collated
            Title newTitle = new Title(titleName, type, yearOfRelease, format, priceAsDouble, isAvailable, band, this);
            // using model to call method
            this.titleModel.createTitle(newTitle);
            // dispose current view and call a new one to refresh table
            view.dispose();
            view = new DashboardView(this);
            createTitleView.dispose();
	        
         }else if(e.getActionCommand().equals("filter")) {
	        // call method in view to get selected item in drop down
	        this.selectedFilter =  view.getDropdownItem();
	        if(view.getDropdownItem().equals("Titles")) {
        		// getting values from input
                this.searchInput = view.getSearchInput();
                // call method to pick data for titles tables passing the search inputs and filter and saving this into a 2d array
                searchResult= this.titleModel.showAvailableTitles(this.searchInput, this.selectedFilter);
                searchResultsView = new SearchResultsView(this, searchResult, this.selectedFilter);
	        } else if(view.getDropdownItem().equals("Customers")) {
        		// getting values from input
                this.searchInput = view.getSearchInput();
                // call method to pick data for titles tables passing the search inputs and filter and saving this into a 2d array
        		searchResult= this.customerModel.showCustomers(this.searchInput, this.selectedFilter);
        		searchResultsView = new SearchResultsView(this, searchResult, this.selectedFilter);
	        }
        } 
	}
        
}
