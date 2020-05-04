package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.Customer;
import models.Title;
import views.CreateCustomerView;
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
	
	// constructor
    public DashboardController(){
        titleModel = new Title();
        customerModel = new Customer();
        view = new DashboardView(this);
       
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// if a certain button is clicked do the actions inside the brackets
        if(e.getActionCommand().equals("add-customer")){
        	createCustomerView = new CreateCustomerView(this);
        } else if(e.getActionCommand().equals("create-customer")) {
        	String firstName = createCustomerView.getFirstNameField();
            String lastName = createCustomerView.getLastNameField();
            String address = createCustomerView.getAddressField();
            String email = createCustomerView.getEmailField();
            String phone = createCustomerView.getPhoneField();
            String cardNumber = createCustomerView.getCardNumberField();
            String membership = createCustomerView.getMembershipField();
            // converting membership into integer
            int membershipAsInt = Integer.parseInt(membership);
            // create an instance of the customer class with the data collated
            Customer newCustomer = new Customer(firstName, lastName, address, email, phone, cardNumber, membershipAsInt);
            // using model to call method
            this.customerModel.createCustomer(newCustomer);
            // dispose current view and call a new one to refresh table
            view.dispose();
            view = new DashboardView(this);
            createCustomerView.dispose();
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
