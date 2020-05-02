package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.DashboardModel;
import models.Title;
import views.DashboardView;

public class DashboardController implements ActionListener {
	
	// properties
	public Title titleModel;
    DashboardView view;
    String selectedFilter;
    String searchInput;
    String[][] searchResult;
	
	// constructor
    public DashboardController(){
        titleModel = new Title();
        view = new DashboardView(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// if a certain button is clicked do the actions inside the brackets
        if(e.getActionCommand().equals("add-rent")){
           //model.showAvailableTitles(searchInput, selectedFilter);
        }
	}

}
