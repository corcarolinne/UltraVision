package views;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.DashboardController;
import models.Customer;

public class UpdateCustomerView extends JFrame {
	
	// properties
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField addressField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField cardNumberField;
    private JComboBox membershipDropdown;
    private DashboardController controller;
    private Customer customerToUpdate;
    

    // constructor
    public UpdateCustomerView(DashboardController controller, Customer customerToUpdate) {
    	this.controller = controller;
    	this.customerToUpdate = customerToUpdate;
    	
    	// calling methods to make the window or the view
        attributesSetter();
        components();
        validation();
    	
	}

	// method to set attributes
    private void attributesSetter(){
        this.setVisible(true);
        this.setSize(275,500);
        this.setTitle("Update Customer");
    }
    
    // getters and setters
    public String getFirstNameField() {
		return this.firstNameField.getText();
	}

	public String getLastNameField() {
		return this.lastNameField.getText();
	}

	public String getAddressField() {
		return this.addressField.getText();
	}

	public String getEmailField() {
		return this.emailField.getText();
	}

	public String getPhoneField() {
		return this.phoneField.getText();
	}

	public String getCardNumberField() {
		return this.cardNumberField.getText();
	}

	public DashboardController getController() {
		return this.controller;
	}

	public void setFirstNameField(JTextField firstNameField) {
		this.firstNameField = firstNameField;
	}

	public void setLastNameField(JTextField lastNameField) {
		this.lastNameField = lastNameField;
	}

	public void setAddressField(JTextField addressField) {
		this.addressField = addressField;
	}

	public void setEmailField(JTextField emailField) {
		this.emailField = emailField;
	}

	public void setPhoneField(JTextField phoneField) {
		this.phoneField = phoneField;
	}

	public void setCardNumberField(JTextField cardNumberField) {
		this.cardNumberField = cardNumberField;
	}

	public void setController(DashboardController controller) {
		this.controller = controller;
	}

	// getter for drop down
	public String getDropdownItem() {
		   return this.membershipDropdown.getSelectedItem().toString();
	}

	// method to organise components of the window
    private void components(){
        JPanel panel = new JPanel();
        this.add(panel);
        
        // creating text fields and labels
        JLabel firstNameLabel, lastNameLabel, addressLabel, emailLabel, phoneLabel, cardNumberLabel, membershipLabel; 
        firstNameLabel = new JLabel("First Name");
        lastNameLabel = new JLabel("Last Name");
        addressLabel = new JLabel("Address");  
        emailLabel = new JLabel("Email");
        phoneLabel = new JLabel("Phone");
        cardNumberLabel = new JLabel("Card Number");
        membershipLabel = new JLabel("Membership");
        firstNameField = new JTextField(this.customerToUpdate.getFirstName(), 20);
        lastNameField = new JTextField(this.customerToUpdate.getLastName(), 20);
        addressField = new JTextField(this.customerToUpdate.getAddress(), 20);
        emailField = new JTextField(this.customerToUpdate.getEmail(), 20);
        phoneField = new JTextField(this.customerToUpdate.getPhoneNumber(), 20);
        cardNumberField = new JTextField(this.customerToUpdate.getCardNumber(), 20);
   
        // drop down to select membership
        String membershipOptions[]={"Select","Music Lovers","Video Lovers", "TV Lovers", "Premium"};        
        this.membershipDropdown = new JComboBox(membershipOptions);
        this.membershipDropdown.addActionListener((ActionListener) controller);
        this.membershipDropdown.setActionCommand("update-membership");
        
        // button
        JButton updateCustomerButton = new JButton("Update Customer");
        updateCustomerButton.addActionListener((ActionListener) controller);
        updateCustomerButton.setActionCommand("update-customer");
        
        // adding button
    	JButton cancelButton = new JButton("Cancel");
    	cancelButton.addActionListener((ActionListener) controller);
    	cancelButton.setActionCommand("cancel-update");
    	
    	
        // adding components to the panel
        panel.add(firstNameLabel);
        panel.add(firstNameField);
        panel.add(lastNameLabel);
        panel.add(lastNameField);
        panel.add(addressLabel);
        panel.add(addressField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(phoneLabel);
        panel.add(phoneField);
        panel.add(cardNumberLabel);
        panel.add(cardNumberField);
        panel.add(membershipLabel);
        panel.add(membershipDropdown);
        panel.add(updateCustomerButton);
        panel.add(cancelButton);
    }
    
    // validation and repainting
    private void validation(){
        this.validate();
        this.repaint();
    }

}
