package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.Customer;
import models.Title;
import views.CreateView;
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
	CreateView createView;
	
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
           //this.customerModel.showAvailableTitles(searchInput, selectedFilter);
        	 createView = new CreateView(this);
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
