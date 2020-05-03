package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.Customer;
import models.Title;
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
	
	// constructor
    public DashboardController(){
        titleModel = new Title();
        customerModel = new Customer();
        view = new DashboardView(this);
       
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// if a certain button is clicked do the actions inside the brackets
        if(e.getActionCommand().equals("add-rent")){
           //model.showAvailableTitles(searchInput, selectedFilter);
        } else if(e.getActionCommand().equals("filter")) {
	        // call method in view to get selected item in drop down
	        this.selectedFilter =  view.getDropdownItem();
        } else if(e.getActionCommand().equals("search")){
            // getting values from input
            this.searchInput = view.getSearchInput();
            // call method to pick data for titles tables passing the search inputs and filter and saving this into a 2d array
            searchResult= this.titleModel.showAvailableTitles(this.searchInput, this.selectedFilter);
            //searchResult= this.titleModel.showRentedTitles(this.searchInput, this.selectedFilter);
            // call the view to show  search results passing 2d array
            searchResultsView = new SearchResultsView(this, searchResult);
        }
	}
        
}
