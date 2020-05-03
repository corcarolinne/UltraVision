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
    
    // constructor receives a Controller class and a 2d array with data for search table
    public SearchResultsView(DashboardController controller, String[][] searchData) {
        // Putting the reference of the controller in the local reference
        this.controller= controller;
        this.searchData = searchData;
        // calling methods to make the view
        this.showView(); 
    }
    
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
  
        // table header
   	 	String[] titlesHeader = {"Title", "Type","Year Of Release", "Genre", "Format", "Price", "Director", "Band"};
        
        // table for search
        JTable searchTable = new JTable(this.searchData, titlesHeader);
        panel.add(searchTable);
        
        JScrollPane scroll = new JScrollPane(searchTable);
        panel.add(scroll); 
        
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
