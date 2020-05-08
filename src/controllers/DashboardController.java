package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import models.Customer;
import models.Title;
import views.CreateCustomerView;
import views.CreateTitleView;
import views.DashboardView;
import views.RentView;
import views.ReturnView;
import views.SearchResultsView;
import views.UpdateCustomerView;

public class DashboardController implements ActionListener {
	
	// properties
	public Title titleModel;
	public Customer customerModel;
    public DashboardView view;
    String selectedFilter;
    String searchInput;
    String[][] searchResult;
    SearchResultsView searchResultsView;
	CreateCustomerView createCustomerView;
	String selectedMembership;
	private int membershipID;
	CreateTitleView createTitleView;
	String selectedType;
	String selectedFormat;
	private String type;
	private String format;
	Customer customerToUpdate;
	UpdateCustomerView updateCustomerView;
	String updatedMembership;
	RentView rentView;
	Title titleToRent;
	ReturnView returnView;
	Title titleToReturn;
	
	// constructor
    public DashboardController(){
        titleModel = new Title();
        customerModel = new Customer();
        view = new DashboardView(this);
       
    }
    
    // some getters and setters
	public int getMembershipID() {
		return this.membershipID;
	}
	public String getType() {
		return this.type;
	}
	public String getFormat() {
		return this.format;
	}
	public RentView getRentView() {
		return this.rentView;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// if a certain button is clicked do the actions inside the brackets
		if(e.getActionCommand().equals("add-rent")){
			// calling method from view to get title selected from table
			this.titleToRent = view.getAvailableTitle();
            if(this.titleToRent.getTitleName() == null) {
            	JFrame f = new JFrame();
        		JOptionPane.showMessageDialog(f,"Title not selected. Please select a title on Available Titles to rent.","Alert",JOptionPane.ERROR_MESSAGE);
            } else {
            	// redirects to rent page
                rentView = new RentView(this, titleToRent);
            }
            
        } else if(e.getActionCommand().equals("rent")){
        	
        	// if validation is successful
        	if(titleModel.validateRent(titleToRent, rentView.getEmailField()).equals("successfulValidation")) {
        		// call method to check customer's score, if it returns true
        		if(titleModel.checkScore(rentView.getEmailField())) {
        			// show message saying the customer won a free rent
        			JFrame f = new JFrame();
            		JOptionPane.showMessageDialog(f,"Customer won a free title! This title is free!");
        		}
    			// call method to rent title
        		titleModel.rentTitle(titleToRent, rentView.getEmailField());
        		// show message saying the rent was successful
        		JFrame f = new JFrame();
        		JOptionPane.showMessageDialog(f,"Rent Successfully Done!");
        		// closing view and opening the dash board again
        		view.dispose();
                view = new DashboardView(this);
                rentView.dispose();
        	} else if (titleModel.validateRent(titleToRent, rentView.getEmailField()).equals("customerNotFoundError")) {
        		JFrame f = new JFrame();
        		JOptionPane.showMessageDialog(f,"This email do not match any customer. Please enter another email.","Alert",JOptionPane.ERROR_MESSAGE);
        	} else if (titleModel.validateRent(titleToRent, rentView.getEmailField()).equals("membershipPermissionError1")) {
        		JFrame f = new JFrame();
        		JOptionPane.showMessageDialog(f,"This customer is not allowed to rent this title. Please select Music or Live Concert.","Alert",JOptionPane.ERROR_MESSAGE);
        	}  else if (titleModel.validateRent(titleToRent, rentView.getEmailField()).equals("membershipPermissionError2")) {
        		JFrame f = new JFrame();
        		JOptionPane.showMessageDialog(f,"This customer is not allowed to rent this title. Please select a Movie.","Alert",JOptionPane.ERROR_MESSAGE);
        	} else if (titleModel.validateRent(titleToRent, rentView.getEmailField()).equals("membershipPermissionError3")) {
        		JFrame f = new JFrame();
        		JOptionPane.showMessageDialog(f,"This customer is not allowed to rent this title. Please select a Box Set.","Alert",JOptionPane.ERROR_MESSAGE);
        	} else if (titleModel.validateRent(titleToRent, rentView.getEmailField()).equals("rentLimitError")) {
        		JFrame f = new JFrame();
        		JOptionPane.showMessageDialog(f,"This customer reached the rent limit! They need to return a title to be able to rent again.","Alert",JOptionPane.ERROR_MESSAGE);
        	}
            
        } else if(e.getActionCommand().equals("cancel-rent")) {
    		// dispose view
        	rentView.dispose();
        } else if(e.getActionCommand().equals("add-return")){
			// calling method from view to get title selected from table
			this.titleToReturn = view.getRentedTitle();
            if(this.titleToReturn.getTitleName() == null) {
            	JFrame f = new JFrame();
        		JOptionPane.showMessageDialog(f,"Title not selected. Please select a title on In Transit table.","Alert",JOptionPane.ERROR_MESSAGE);
            } else {
            	// redirects to return title page
                returnView = new ReturnView(this, titleToReturn);
            }
        } else if(e.getActionCommand().equals("return")){
        	// call method to return title
    		titleModel.returnTitle(titleToReturn);
    		// show message saying the rent was successful
    		JFrame f = new JFrame();
    		JOptionPane.showMessageDialog(f,"Return Successfully Done!");
    		// closing view and opening the dash board again
    		view.dispose();
            view = new DashboardView(this);
            returnView.dispose();
        } else if(e.getActionCommand().equals("cancel-return")) {
    		// dispose view
        	returnView.dispose();
        } else if(e.getActionCommand().equals("add-customer")){
        	createCustomerView = new CreateCustomerView(this);
        } else if(e.getActionCommand().equals("select-membership")) {
        	this.selectedMembership =  createCustomerView.getDropdownItem();
        	// setting membership ID according to selected drop down
        	if(createCustomerView.getDropdownItem().equals("Music Lovers")) {
        		this.membershipID = 1;
        	} else if (createCustomerView.getDropdownItem().equals("Video Lovers")) {
        		this.membershipID = 2;
        	} else if (createCustomerView.getDropdownItem().equals("TV Lovers")) {
        		this.membershipID = 3;
        	} else if (createCustomerView.getDropdownItem().equals("Premium")) {
        		this.membershipID = 4;
        	}
        } else if(e.getActionCommand().equals("create-customer")) {
        	String firstName = createCustomerView.getFirstNameField();
            String lastName = createCustomerView.getLastNameField();
            String address = createCustomerView.getAddressField();
            String email = createCustomerView.getEmailField();
            String phone = createCustomerView.getPhoneField();
            String cardNumber = createCustomerView.getCardNumberField();
            int membership = this.membershipID;
            // create an instance of the customer class with the data collated
            Customer newCustomer = new Customer(firstName, lastName, address, email, phone, cardNumber, membership, this);
            // using model to call method
            this.customerModel.createCustomer(newCustomer);
            // dispose current view and call a new one to refresh table
            view.dispose();
            view = new DashboardView(this);
            createCustomerView.dispose();
	        
         } else if(e.getActionCommand().equals("add-title")){
         	createTitleView = new CreateTitleView(this);
         } else if(e.getActionCommand().equals("select-type")) {
         	this.selectedType =  createTitleView.getTypeDropdownItem();
         	if(createTitleView.getTypeDropdownItem().equals("Music")) {
         		// insert band text field on create title view
         		this.type = "Music";
         		createTitleView.getArtistFieldComponent().setEnabled(true);
         	} else if (createTitleView.getTypeDropdownItem().equals("Live Concert")) {
         		// insert band text field on create title view
         		this.type = "Live Concert";
         		createTitleView.getArtistFieldComponent().setEnabled(true);
         		System.out.println("Test Live Concert");
         	} else if (createTitleView.getTypeDropdownItem().equals("Movie")) {
         		// insert genre, director text fields on create title view
         		this.type = "Movie";
         		createTitleView.getGenreFieldComponent().setEnabled(true);
         		createTitleView.getDirectorFieldComponent().setEnabled(true);
         	} else if (createTitleView.getTypeDropdownItem().equals("Box Set")) {
         		// insert genre text field on create title view
         		this.type = "Box Set";
         		createTitleView.getGenreFieldComponent().setEnabled(true);      	
         	} 
         } else if(e.getActionCommand().equals("select-format")) {
          	this.selectedFormat =  createTitleView.getFormatDropdownItem();
          	if(createTitleView.getFormatDropdownItem().equals("CD")) {
          		this.format = "CD";
          	} else if (createTitleView.getFormatDropdownItem().equals("DVD")) {
          		this.format = "DVD";
          	} else if (createTitleView.getFormatDropdownItem().equals("Blu-Ray")) {
          		this.format = "Blu-Ray";
          	}
         } else if(e.getActionCommand().equals("create-title")) {
        	 String titleName = createTitleView.getTitleField();
         	String type = this.type;
             String yearOfRelease = createTitleView.getYearOfReleaseField();
             String format = this.format;
             String price = createTitleView.getPriceField();
             boolean isAvailable = true;
             String artist = createTitleView.getArtistField();
             String genre = createTitleView.getGenreField();
             String director = createTitleView.getDirectorField();
             
             // regex pattern for year from 1900 to 2099
             Pattern DATE_PATTERN = Pattern.compile("\\b(19|20)\\d\\d\\b");
             // saving matching result in a variable
             boolean isYearValid = DATE_PATTERN.matcher(yearOfRelease).matches();
             
        	 // if fields are empty
        	if(titleName.equals("") || yearOfRelease.equals("") || price.equals("")) {
        		JFrame f = new JFrame();
        		JOptionPane.showMessageDialog(f,"Mandatory fields are empty. Please type a Title, a Year and a Price.","Alert",JOptionPane.ERROR_MESSAGE);
         	}
        	// check if drop downs were selected
        	else if (createTitleView.getFormatDropdownItem().equals("Select") || createTitleView.getTypeDropdownItem().equals("Select"))  {
         		JFrame f = new JFrame();
        		JOptionPane.showMessageDialog(f,"Mandatory fields are empty. Please select a Format and a Type.","Alert",JOptionPane.ERROR_MESSAGE);
           	}
        	// if year is not a valid year
        	else if (isYearValid == false) {
        		JFrame f = new JFrame();
        		JOptionPane.showMessageDialog(f,"Please enter a valid year on Year Of Release.","Alert",JOptionPane.ERROR_MESSAGE);
         	} 
        	// if all necessary inputs were filled and year is valid 
        	else {
            	try {
            		// if price is not null, try to parse price as double
                	double priceAsDouble = Double.parseDouble(price);
                	
                	// if we convert the price successfully
                	//create an instance of the title class with the data collated
                	Title newTitle = new Title(titleName, type, yearOfRelease, format, priceAsDouble, isAvailable, artist, genre, director, this);
					// using model to call method
					this.titleModel.createTitle(newTitle);
					// dispose current view and call a new one to refresh table
					view.dispose();
					view = new DashboardView(this);
					createTitleView.dispose();
    
            	} catch (Exception e1) {
                	JFrame f = new JFrame();
            		JOptionPane.showMessageDialog(f,"Price input not valid. Please insert only numbers on price field.","Alert",JOptionPane.ERROR_MESSAGE);
                }
            }	  
         }  else if(e.getActionCommand().equals("update-customer-page")){
        	// calling method from view to get customer selected from table
             this.customerToUpdate= view.getSelectedCustomer();
             // redirects to customer update page
            updateCustomerView = new UpdateCustomerView(this, customerToUpdate);
         } else if(e.getActionCommand().equals("update-membership")) {
         	this.selectedMembership =  updateCustomerView.getDropdownItem();
         	// setting membership ID according to selected drop down
         	if(updateCustomerView.getDropdownItem().equals("Music Lovers")) {
         		this.membershipID = 1;
         	} else if (updateCustomerView.getDropdownItem().equals("Video Lovers")) {
         		this.membershipID = 2;
         	} else if (updateCustomerView.getDropdownItem().equals("TV Lovers")) {
         		this.membershipID = 3;
         	} else if (updateCustomerView.getDropdownItem().equals("Premium")) {
         		this.membershipID = 4;
         	}
         } else if(e.getActionCommand().equals("update-customer")) {
        	String firstName = updateCustomerView.getFirstNameField();
            String lastName = updateCustomerView.getLastNameField();
            String address = updateCustomerView.getAddressField();
            String email = updateCustomerView.getEmailField();
            String phone = updateCustomerView.getPhoneField();
            String cardNumber = updateCustomerView.getCardNumberField();
            int membership = this.getMembershipID();
            // create an instance of the customer class with the data collated
            Customer customerSelected = new Customer(firstName, lastName, address, email, phone, cardNumber, membership, this);
            // using model to call method
            this.customerModel.updateCustomer(customerSelected, this.customerToUpdate);
            // dispose current view and call a new one to refresh table
            view.dispose();
            view = new DashboardView(this);
            updateCustomerView.dispose();
	        
         } else if(e.getActionCommand().equals("filter")) {
	        // call method in view to get selected item in drop down
	        this.selectedFilter = view.getDropdownItem();
	        if(view.getDropdownItem().equals("Titles")) {
        		// getting values from input
	        	this.searchInput = view.getSearchInput();
                // if user doesn't enter anything on search text field
                if(view.getSearchInput().equals("")) {
                	JFrame f = new JFrame();
            		JOptionPane.showMessageDialog(f,"No input on search field. Please type what you wanna search and select Titles or Customers.","Alert",JOptionPane.ERROR_MESSAGE);
                } else {
	                // call method to pick data for titles tables passing the search inputs and filter and saving this into a 2d array
	                searchResult= this.titleModel.showAvailableTitles(this.searchInput, this.selectedFilter);
	                searchResultsView = new SearchResultsView(this, searchResult, this.selectedFilter);
                }
	        } else if(view.getDropdownItem().equals("Customers")) {
        		// getting values from input
                this.searchInput = view.getSearchInput();
                if(view.getSearchInput().equals("")) {
                	JFrame f = new JFrame();
            		JOptionPane.showMessageDialog(f,"No input on search field. Please type what you wanna search and select Titles or Customers.","Alert",JOptionPane.ERROR_MESSAGE);
                } else {
                	// call method to pick data for titles tables passing the search inputs and filter and saving this into a 2d array
            		searchResult= this.customerModel.showCustomers(this.searchInput, this.selectedFilter);
            		searchResultsView = new SearchResultsView(this, searchResult, this.selectedFilter);
    	        }
                }
                
        }
	}
        
}
