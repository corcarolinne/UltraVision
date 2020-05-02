package views;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controllers.DashboardController;

public class DashboardView extends JFrame {

	// properties
	private DashboardController controller;
	private JTable availableTitlesTable;
	public String[][] titlesData;
    
	
	// constructor
	public DashboardView(DashboardController controller) {
		this.controller = controller;
		
		// basic window properties
		this.setVisible(true);
        this.setSize(1500,550);
        this.setTitle("Dashboard");
        
        // adding panel
    	JPanel panel = new JPanel();
    	this.add(panel);	
    	
    	// adding a button
    	JButton addRentButton = new JButton("Add Rent");
    	addRentButton.addActionListener((ActionListener) controller);
    	addRentButton.setActionCommand("add-rent");
    	panel.add(addRentButton);
    	
    	// creating header
    	 String[] header = {"Title", "Type","Year Of Release", "Genre", "Format", "Price", "Director", "Band"};
    	
    	// calling method from model to show available titles
    	 titlesData = controller.titleModel.showAvailableTitles("","");
    	 
    	 // 
    	 availableTitlesTable = new JTable(titlesData, header);
         panel.add(availableTitlesTable);
    
         // scroll
         JScrollPane scroll = new JScrollPane(availableTitlesTable);
         panel.add(scroll);
         
         
    	// calling validation method
    	this.validation();
    	
	}
	
	
	
	
    // validation and repainting
    private void validation(){
        this.validate();
        this.repaint();
    }
}
