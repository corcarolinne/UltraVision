package views;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controllers.DashboardController;

public class DashboardView extends JFrame {

	// properties
	private JPanel panel;
	private DashboardController controller;
	private JTable availableTitlesTable;
	private JTable rentedTitlesTable;
	public String[][] availabletitlesData;
	public String[][] rentedTitlesData;
    
	
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
		
	}
	// method to create tables for view 
	public void tablesSetter() {
		// creating label for table
    	JLabel availableTitlesLabel = new JLabel("Available Titles"); 
    	panel.add(availableTitlesLabel);
    	
		// creating header
   	 	String[] header = {"Title", "Type","Year Of Release", "Genre", "Format", "Price", "Director", "Band"};
   	
   	 	// calling method from model to show available titles
   	 	availabletitlesData = controller.titleModel.showAvailableTitles("","");
   	 
   	 	// creating table and adding it to the panel
   	 	availableTitlesTable = new JTable(availabletitlesData, header);
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
		 rentedTitlesTable = new JTable(rentedTitlesData, header);
		 panel.add(rentedTitlesTable);
    
		 // scroll
		 JScrollPane scroll2 = new JScrollPane(rentedTitlesTable);
		 panel.add(scroll2);    
	}
	
    // validation and repainting
    private void validation(){
        this.validate();
        this.repaint();
    }
}
