package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	// constructor
    public DashboardController(){
        titleModel = new Title();
        customerModel = new Customer();
        view = new DashboardView(this);
       
    }
	public int getMembershipID() {
		return this.membershipID;
	}

	public void setSelectedMembership(String selectedMembership) {
		this.selectedMembership = selectedMembership;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// if a certain button is clicked do the actions inside the brackets
        if(e.getActionCommand().equals("add-customer")){
        	createCustomerView = new CreateCustomerView(this);
        } else if(e.getActionCommand().equals("select-membership")) {
        	this.selectedFilter =  createCustomerView.getDropdownItem();
        	// setting membership ID according to selected drop down
        	if(createCustomerView.getDropdownItem().equals("Music Lovers")) {
        		membershipID = 1;
        	} else if (createCustomerView.getDropdownItem().equals("Video Lovers")) {
        		membershipID = 2;
        	} else if (createCustomerView.getDropdownItem().equals("TV Lovers")) {
        		membershipID = 3;
        	} else if (createCustomerView.getDropdownItem().equals("Premium")) {
        		membershipID = 4;
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
         } else if(e.getActionCommand().equals("filter")) {
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
