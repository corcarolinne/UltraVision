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
import views.UpdateCustomerView;

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
	String selectedMembership2;
	private int membershipID2;
	CreateTitleView createTitleView;
	String selectedType;
	String selectedFormat;
	private String type;
	private String format;
	Customer customerToUpdate;
	UpdateCustomerView updateCustomerView;
	String updatedMembership;
	

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
	// some getters and setters
		public int getMembershipID2() {
			return this.membershipID2;
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
         		createTitleView.getArtistFieldComponent().setEnabled(true);
         	} else if (createTitleView.getTypeDropdownItem().equals("Live Concert Videos")) {
         		// insert band text field on create title view
         		this.type = "Live Concert Videos";
         		createTitleView.getArtistFieldComponent().setEnabled(true);
         		System.out.println("Test Live Concert");
         	} else if (createTitleView.getTypeDropdownItem().equals("Movie")) {
         		// insert genre, director text fields on create title view
         		this.type = "Movie";
         		createTitleView.getGenreFieldComponent().setEnabled(true);
         		createTitleView.getDirectorFieldComponent().setEnabled(true);
         	} else if (createTitleView.getTypeDropdownItem().equals("Box Set")) {
         		// insert genre text field on create title view
         		this.type = "Box Set";
         		createTitleView.getGenreFieldComponent().setEnabled(true);      	
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
            String artist = createTitleView.getArtistField();
            String genre = createTitleView.getGenreField();
            String director = createTitleView.getDirectorField();
            // create an instance of the customer class with the data collated
            Title newTitle = new Title(titleName, type, yearOfRelease, format, priceAsDouble, isAvailable, artist, genre, director, this);
            // using model to call method
            this.titleModel.createTitle(newTitle);
            // dispose current view and call a new one to refresh table
            view.dispose();
            view = new DashboardView(this);
            createTitleView.dispose();
	        
         }  if(e.getActionCommand().equals("update-customer-page")){
        	// calling method from view to get customer selected from table
             this.customerToUpdate= view.getSelectedCustomer();
             // redirects to customer update page
            updateCustomerView = new UpdateCustomerView(this, customerToUpdate);
         } else if(e.getActionCommand().equals("update-membership")) {
         	this.selectedMembership2 =  updateCustomerView.getDropdownItem();
         	System.out.println(this.selectedMembership2);
         	// setting membership ID according to selected drop down
         	if(updateCustomerView.getDropdownItem().equals("Music Lovers")) {
         		this.membershipID2 = 1;
         	} else if (updateCustomerView.getDropdownItem().equals("Video Lovers")) {
         		this.membershipID2 = 2;
         	} else if (updateCustomerView.getDropdownItem().equals("TV Lovers")) {
         		this.membershipID2 = 3;
         	} else if (updateCustomerView.getDropdownItem().equals("Premium")) {
         		this.membershipID2 = 4;
         	}
         } else if(e.getActionCommand().equals("update-customer")) {
        	String firstName = updateCustomerView.getFirstNameField();
            String lastName = updateCustomerView.getLastNameField();
            String address = updateCustomerView.getAddressField();
            String email = updateCustomerView.getEmailField();
            String phone = updateCustomerView.getPhoneField();
            String cardNumber = updateCustomerView.getCardNumberField();
            int membership2 = this.getMembershipID2();
            // create an instance of the customer class with the data collated
            Customer customerSelected = new Customer(firstName, lastName, address, email, phone, cardNumber, membership2, this);
            // using model to call method
            this.customerModel.updateCustomer(customerSelected, this.customerToUpdate);
            // dispose current view and call a new one to refresh table
            view.dispose();
            view = new DashboardView(this);
            updateCustomerView.dispose();
	        
         } else if(e.getActionCommand().equals("filter")) {
	        // call method in view to get selected item in drop down
	        this.selectedFilter = view.getDropdownItem();
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
