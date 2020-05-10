package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import interfaces.CustomerInterface;
import interfaces.TitleInterface;
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
	public TitleInterface titleModel;
	public CustomerInterface customerModel;
    public DashboardView view;
    private String selectedFilter;
    private String searchInput;
    private String[][] searchResult;
    private SearchResultsView searchResultsView;
    private CreateCustomerView createCustomerView;
    private String selectedMembership;
	private int membershipID;
	private CreateTitleView createTitleView;
	private String selectedType;
	private String selectedFormat;
	private String type;
	private String format;
	private Customer customerToUpdate;
	private UpdateCustomerView updateCustomerView;
	private String updatedMembership;
	private RentView rentView;
	private Title titleToRent;
	private ReturnView returnView;
	private Title titleToReturn;
	
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
	
	// action listener
	@Override
	public void actionPerformed(ActionEvent e) {
		// if a certain button is clicked do the actions inside the brackets
		if(e.getActionCommand().equals("add-rent")){
			// calling method from view to get title selected from table
			this.titleToRent = view.getAvailableTitle();
			// if title is not selected, display the following message
            if(this.titleToRent.getTitleName() == null) {
            	JFrame f = new JFrame();
        		JOptionPane.showMessageDialog(f,"Title not selected. Please select a title on Available Titles to rent.","Alert",JOptionPane.ERROR_MESSAGE);
            } else {
            	// otherwise, redirects to rent page
                rentView = new RentView(this, titleToRent);
            }
        
        // action listener of button to make the rent of a title
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
              // series of messages that will be displayed depending on the result of validateRent method
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
        // action listener for button to cancel a rent   
        } else if(e.getActionCommand().equals("cancel-rent")) {
    		// dispose view
        	rentView.dispose();
        // action listener for button to redirect to return title page
        } else if(e.getActionCommand().equals("add-return")){
			// calling method from view to get title selected from table
			this.titleToReturn = view.getRentedTitle();
			// if title from appropriate table is not selected, display the following message
            if(this.titleToReturn.getTitleName() == null) {
            	JFrame f = new JFrame();
        		JOptionPane.showMessageDialog(f,"Title not selected. Please select a title on In Transit table.","Alert",JOptionPane.ERROR_MESSAGE);
            } else {
            	// redirects to return title page
                returnView = new ReturnView(this, titleToReturn);
            }
        // action listener for button to make the return
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
        // action listener to cancel a return
        } else if(e.getActionCommand().equals("cancel-return")) {
    		// dispose view
        	returnView.dispose();
        // action listener for button to redirects to page to add a customer
        } else if(e.getActionCommand().equals("add-customer")){
        	createCustomerView = new CreateCustomerView(this);
        // when membership is selected on create customer page, make it to match the right ID
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
        // action listener for button to create customer 
        } else if(e.getActionCommand().equals("create-customer")) {
        	// getting values from the fields on create customer page
        	String firstName = createCustomerView.getFirstNameField();
            String lastName = createCustomerView.getLastNameField();
            String address = createCustomerView.getAddressField();
            String email = createCustomerView.getEmailField();
            String phone = createCustomerView.getPhoneField();
            String cardNumber = createCustomerView.getCardNumberField();
            // setting membership to be the selected one
            int membership = this.membershipID;
            
            // regex pattern for email
            Pattern EMAIL_PATTERN = Pattern.compile("\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b");
            // saving matching result in a variable
            boolean isEmailValid = EMAIL_PATTERN.matcher(email).matches();
            
            // regex pattern for card number
            Pattern CARD_NUMBER_PATTERN = Pattern.compile("(\\d{4}[-. ]?){4}|\\d{4}[-. ]?\\d{6}[-. ]?\\d{5}");
            // saving matching result in a variable
            boolean isCardNumberValid = CARD_NUMBER_PATTERN.matcher(cardNumber).matches();

            // regex pattern for phone number
            Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^(\\(\\d+\\)\\s{0,1}){0,1}(\\d+((\\-|\\s){0,1})\\d+){0,}$");
            // saving matching result in a variable
            boolean isPhoneNumberValid = PHONE_NUMBER_PATTERN.matcher(phone).matches();
            
            // if mandatory fields are empty display following message
        	if(firstName.equals("") || lastName.equals("") || address.equals("")){
        		JFrame f = new JFrame();
        		JOptionPane.showMessageDialog(f,"Mandatory fields are empty. Please type First Name, Last Name and Address of customer.","Alert",JOptionPane.ERROR_MESSAGE);
         	} else if (createCustomerView.getDropdownItem().equals("Select"))  {
         		JFrame f = new JFrame();
        		JOptionPane.showMessageDialog(f,"Mandatory field is empty. Please select a Membership.","Alert",JOptionPane.ERROR_MESSAGE);
           	}
        	// if email is not a valid email
        	else if (isEmailValid == false) {
        		JFrame f = new JFrame();
        		JOptionPane.showMessageDialog(f,"Please enter email address in format: youremail@example.com","Alert",JOptionPane.ERROR_MESSAGE);
         	} else if (this.customerModel.validateCreateEmail(email) == false) {
        		JFrame f = new JFrame();
        		JOptionPane.showMessageDialog(f,"This email is already in use. Please type another email.","Alert",JOptionPane.ERROR_MESSAGE);
         	} 
        	// if card number is not a valid card number
        	else if (isCardNumberValid == false) {
        		JFrame f = new JFrame();
        		JOptionPane.showMessageDialog(f,"Wrong card number. Please enter a valid card number.","Alert",JOptionPane.ERROR_MESSAGE);
         	}
        	// if phone number is entered check if it's a phone number
        	else if (isPhoneNumberValid == false) {
        		JFrame f = new JFrame();
        		JOptionPane.showMessageDialog(f,"Please enter phone number such as: (999) 999 999-999.","Alert",JOptionPane.ERROR_MESSAGE);
         	} 
        	// if all the inputs are valid
        	else {
         		// create an instance of the customer class with the data collated
                Customer newCustomer = new Customer(firstName, lastName, address, email, phone, cardNumber, membership, this);
                // using model to call method
                this.customerModel.createCustomer(newCustomer);
                // show success message to the user
                JFrame f = new JFrame();
        		JOptionPane.showMessageDialog(f,"Customer created successfully!");
                // dispose current view and call a new one to refresh table
                view.dispose();
                view = new DashboardView(this);
                createCustomerView.dispose();
         	}
         // action listener for button to redirects to add a title page
         } else if(e.getActionCommand().equals("add-title")){
         	createTitleView = new CreateTitleView(this);
         //  when type is selected on create title page, enable the corresponding fields
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
         //  when format is selected on create title page, match it to the corresponding value
         } else if(e.getActionCommand().equals("select-format")) {
          	this.selectedFormat =  createTitleView.getFormatDropdownItem();
          	if(createTitleView.getFormatDropdownItem().equals("CD")) {
          		this.format = "CD";
          	} else if (createTitleView.getFormatDropdownItem().equals("DVD")) {
          		this.format = "DVD";
          	} else if (createTitleView.getFormatDropdownItem().equals("Blu-Ray")) {
          		this.format = "Blu-Ray";
          	}
         // action listener for create title button
         } else if(e.getActionCommand().equals("create-title")) {
        	 // set the variables to be the value of the input on create title page
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
             Pattern YEAR_PATTERN = Pattern.compile("\\b(19|20)\\d\\d\\b");
             // saving matching result in a variable
             boolean isYearValid = YEAR_PATTERN.matcher(yearOfRelease).matches();
             
        	 // if fields are empty
        	if(titleName.equals("") || yearOfRelease.equals("") || price.equals("")) {
        		JFrame f = new JFrame();
        		JOptionPane.showMessageDialog(f,"Mandatory fields are empty. Please type a Title, a Year and a Price.","Alert",JOptionPane.ERROR_MESSAGE);
         	}
        	// check if drop downs weren't selected
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
					// success message to user
					JFrame f = new JFrame();
	        		JOptionPane.showMessageDialog(f,"Title created successfully!");
					// dispose current view and call a new one to refresh table
					view.dispose();
					view = new DashboardView(this);
					createTitleView.dispose();
            	} catch (Exception e1) {
                	JFrame f = new JFrame();
            		JOptionPane.showMessageDialog(f,"Price input not valid. Please insert only numbers on price field.","Alert",JOptionPane.ERROR_MESSAGE);
                }
            }
        	// action listener for button to redirects to update customer page
         	} else if(e.getActionCommand().equals("update-customer-page")){
        	// calling method from view to get customer selected from table
             this.customerToUpdate= view.getSelectedCustomer();
             // if nothing is selected, display the following message
             if(this.customerToUpdate.getFirstName() == null) {
              	JFrame f = new JFrame();
          		JOptionPane.showMessageDialog(f,"Customer not selected. Please select a customer on Customers table.","Alert",JOptionPane.ERROR_MESSAGE);
              } else {
            	// redirects to customer update page
                  updateCustomerView = new UpdateCustomerView(this, customerToUpdate);
              }
             // action listener for button to cancel update
         	} else if(e.getActionCommand().equals("cancel-update")) {
         		// dispose view
         		updateCustomerView.dispose();
         	// set membership to match its right ID
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
         // action listener for update customer button
         } else if(e.getActionCommand().equals("update-customer")) {
        	// get inputs from view
        	int ID = customerToUpdate.getID();
        	String firstName = updateCustomerView.getFirstNameField();
            String lastName = updateCustomerView.getLastNameField();
            String address = updateCustomerView.getAddressField();
            String email = updateCustomerView.getEmailField();
            String phone = updateCustomerView.getPhoneField();
            String cardNumber = updateCustomerView.getCardNumberField();
            int membership = this.getMembershipID();
            
            // regex pattern for email
            Pattern EMAIL_PATTERN = Pattern.compile("\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b");
            // saving matching result in a variable
            boolean isEmailValid = EMAIL_PATTERN.matcher(email).matches();
            
            // regex pattern for card number
            Pattern CARD_NUMBER_PATTERN = Pattern.compile("(\\d{4}[-. ]?){4}|\\d{4}[-. ]?\\d{6}[-. ]?\\d{5}");
            // saving matching result in a variable
            boolean isCardNumberValid = CARD_NUMBER_PATTERN.matcher(cardNumber).matches();

            // regex pattern for phone number
            Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^(\\(\\d+\\)\\s{0,1}){0,1}(\\d+((\\-|\\s){0,1})\\d+){0,}$");
            // saving matching result in a variable
            boolean isPhoneNumberValid = PHONE_NUMBER_PATTERN.matcher(phone).matches();
            
            // if mandatory fields are empty
        	if(firstName.equals("") || lastName.equals("") || address.equals("")){
        		JFrame f = new JFrame();
        		JOptionPane.showMessageDialog(f,"Mandatory fields are empty. Please type First Name, Last Name and Address of customer.","Alert",JOptionPane.ERROR_MESSAGE);
         	} else if (updateCustomerView.getDropdownItem().equals("Select"))  {
         		JFrame f = new JFrame();
        		JOptionPane.showMessageDialog(f,"Mandatory field is empty. Please select a Membership.","Alert",JOptionPane.ERROR_MESSAGE);
           	}
        	// if email is not a valid email
        	else if (isEmailValid == false) {
        		JFrame f = new JFrame();
        		JOptionPane.showMessageDialog(f,"Please enter email address in format: youremail@example.com","Alert",JOptionPane.ERROR_MESSAGE);
         	} 
        	else if (this.customerModel.validateEmail(email, ID) == false) {
        		JFrame f = new JFrame();
        		JOptionPane.showMessageDialog(f,"This email is already in use. Please type another email.","Alert",JOptionPane.ERROR_MESSAGE);
         	}
        	// if card number is not a valid card number
        	else if (isCardNumberValid == false) {
        		JFrame f = new JFrame();
        		JOptionPane.showMessageDialog(f,"Wrong card number. Please enter a valid card number.","Alert",JOptionPane.ERROR_MESSAGE);
         	}
        	// if phone number is entered check if it's a phone number
        	else if (isPhoneNumberValid == false) {
        		JFrame f = new JFrame();
        		JOptionPane.showMessageDialog(f,"Please enter a valid phone number such as (999) 999 999-999.","Alert",JOptionPane.ERROR_MESSAGE);
         	} else {
         	// create an instance of the customer class with the data collated
                Customer customerSelected = new Customer(firstName, lastName, address, email, phone, cardNumber, membership, this);
                // using model to call method
                this.customerModel.updateCustomer(customerSelected, this.customerToUpdate);
                // show message saying the update was successful
        		JFrame f = new JFrame();
        		JOptionPane.showMessageDialog(f,"Update Successfully Done!");
                // dispose current view and call a new one to refresh table
                view.dispose();
                view = new DashboardView(this);
                updateCustomerView.dispose();
         	} 
        // action listener for filter/search button
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
