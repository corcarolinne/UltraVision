package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controllers.DashboardController;

public class SearchResultsView extends JFrame {
	
	// properties
    private DashboardController controller;
    private String[][] searchData;
    private String selectedFilter;
    
    // constructor receives a Controller class and a 2d array with data for search table
    public SearchResultsView(DashboardController controller, String[][] searchData, String selectedFilter) {
        // Putting the reference of the controller in the local reference
        this.controller= controller;
        this.searchData = searchData;
        this.selectedFilter = selectedFilter;
        
        // calling methods to make the view
        this.showView(); 
    }
    
    // getters and setters
    public DashboardController getController() {
		return this.controller;
	}

	public String[][] getSearchData() {
		return this.searchData;
	}

	public String getSelectedFilter() {
		return this.selectedFilter;
	}

	public void setController(DashboardController controller) {
		this.controller = controller;
	}

	public void setSelectedFilter(String selectedFilter) {
		this.selectedFilter = selectedFilter;
	}

	// method to display view
	public void showView() {
        // calling methods to make the window or the view
        attributesSetter();
        components();
        validation();
    }
    
    // method to set attributes
    private void attributesSetter(){
        this.setVisible(true);
        this.setSize(500,500);
        this.setTitle("Search Results");
    }
    
    // method to organise components of the window
    private void components(){
        JPanel panel = new JPanel();
        this.add(panel);
        
        String[] header = null;
        
        if(this.selectedFilter.equals("Titles")) {
        	header = new String[]{"ID","Title", "Type","Year Of Release", "Genre", "Format", "Price", "Director", "Artist"};
        } else {
        	header = new String[]{"ID","First Name", "Last Name","Address", "Email", "Phone", "Card Number", "Membership", "Loyalty Points"};
        }
     
        // table for search
        JTable searchTable = new JTable(this.searchData, header);
        panel.add(searchTable);
        JScrollPane scroll = new JScrollPane(searchTable);
        panel.add(scroll); 
        
        // call validation method
        validation();  
    }
   
    // validation and repainting
    private void validation(){
        this.validate();
        this.repaint();
    }

    public void setSearchData(String[][] searchData) {
        this.searchData = searchData;
    }

}
