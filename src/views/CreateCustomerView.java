package views;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.DashboardController;

public class CreateCustomerView extends JFrame {
	
	// properties
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField addressField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField cardNumberField;
    private JTextField membershipField;
    private DashboardController controller;

    // constructor
    public CreateCustomerView(DashboardController controller) {

        this.controller= controller;
        // calling methods to make the window or the view
        attributesSetter();
        components();
        validation();
    }
    
    // getters and setters
    
    // method to set attributes
    private void attributesSetter(){
        this.setVisible(true);
        this.setSize(275,500);
        this.setTitle("Create");
    }
    
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

	public String getMembershipField() {
		return this.membershipField.getText();
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

	public void setMembershipField(JTextField membershipField) {
		this.membershipField = membershipField;
	}

	public void setController(DashboardController controller) {
		this.controller = controller;
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
        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        addressField = new JTextField(20);
        emailField = new JTextField(20);
        phoneField = new JTextField(20);
        cardNumberField = new JTextField(20);
        membershipField = new JTextField(20);
        
        // button
        JButton createButton = new JButton("Create Customer");
        createButton.addActionListener((ActionListener) controller);
        createButton.setActionCommand("create-customer");
        
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
        panel.add(membershipField);
        panel.add(createButton);
    }
    
    // validation and repainting
    private void validation(){
        this.validate();
        this.repaint();
    }
}    
