package views;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controllers.DashboardController;

public class DashboardView extends JFrame {

	// properties
	private DashboardController controller;
	//private JTable availableTitlesTable;
	//String[][] titlesData;
    
	
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
    	
    	// calling validation method
    	this.validation();
    	
	}
	
	
	
	
    // validation and repainting
    private void validation(){
        this.validate();
        this.repaint();
    }
}
