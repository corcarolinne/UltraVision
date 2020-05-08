package views;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.DashboardController;
import models.Title;

public class ReturnView extends JFrame {
	
	// properties
	private JPanel panel;
	private JLabel titleLabel;
	private DashboardController controller;
	private Title titleToReturn;
	
	// constructor
	public ReturnView(DashboardController controller, Title titleToReturn) {
		this.controller = controller;
		this.titleToReturn = titleToReturn;
		this.attributesSetter();
        this.componentsSetter();
    	this.validation();
	}
	
	public void attributesSetter() {
		// basic window properties
		this.setVisible(true);
		 this.setSize(320,300);
        this.setTitle("Return");
	}
	public void componentsSetter() {
		// creating panel
		panel = new JPanel();
    	this.add(panel);
    	
    	// title label
    	titleLabel = new JLabel("Confirm return of title: " + this.titleToReturn.getTitleName());
    	panel.add(titleLabel);
    	
    	// adding button
    	JButton returnButton = new JButton("Confirm Return");
    	returnButton.addActionListener((ActionListener) controller);
    	returnButton.setActionCommand("return");
    	panel.add(returnButton);
    	
    	// adding button
    	JButton cancelButton = new JButton("Cancel");
    	cancelButton.addActionListener((ActionListener) controller);
    	cancelButton.setActionCommand("cancel-return");
    	panel.add(cancelButton);
	}
	
	// getter for panel
	public JPanel getPanel() {
		return this.panel;
	}
	

	// validation and repainting
    private void validation(){
        this.validate();
        this.repaint();
    }

}
