package views;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.DashboardController;
import models.Title;

public class RentView extends JFrame {
	// properties
	private JLabel titleLabel;
	private JLabel emailLabel;
	private JTextField emailField;
	private JLabel priceLabel;
	private JLabel returnDateLabel;
	private JPanel panel;
	private DashboardController controller;
	private Title titleToRent;
	
	// constructor
	public RentView(DashboardController controller, Title titleToRent) {
		this.controller = controller;
		this.titleToRent = titleToRent;
		this.attributesSetter();
        this.componentsSetter();
    	this.validation();
	}
	
	// calling method to build the view
	public void attributesSetter() {
		// basic window properties
		this.setVisible(true);
		 this.setSize(300,400);
        this.setTitle("Rent");
	}
	public void componentsSetter() {
		// creating panel
		panel = new JPanel();
    	this.add(panel);
    	
    	// title label
    	titleLabel = new JLabel("Title: " + this.titleToRent.getTitleName());
    	panel.add(titleLabel);
    	
    	// email label & text field
    	emailLabel = new JLabel("Customer Email: ");
    	panel.add(emailLabel);
    	emailField = new JTextField(20);
    	panel.add(emailField);
    	
    	// price label
    	priceLabel = new JLabel("Price: €" + String.valueOf(this.titleToRent.getPrice()));
    	panel.add(priceLabel);
    	
    	// return label
    	returnDateLabel = new JLabel("This title should be returned at: <date goes here>");
    	panel.add(returnDateLabel);
    	
    	// adding button
    	JButton rentButton = new JButton("Rent");
    	rentButton.addActionListener((ActionListener) controller);
    	rentButton.setActionCommand("rent");
    	panel.add(rentButton);
    	
    	// adding button
    	JButton cancelButton = new JButton("Cancel");
    	cancelButton.addActionListener((ActionListener) controller);
    	cancelButton.setActionCommand("cancel-rent");
    	panel.add(cancelButton);
	}
	
	// getter for customer's email
	public String getEmailField() {
		return emailField.getText();
	}
	public JPanel getPanel() {
		return this.panel;
	}
	
	// validation and repainting
    private void validation(){
        this.validate();
        this.repaint();
    }

}
