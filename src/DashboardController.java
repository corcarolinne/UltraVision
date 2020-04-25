import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardController implements ActionListener {
	
	// properties
	public DashboardModel model;
    DashboardView view;
	
	// constructor
    public DashboardController(){
        model = new DashboardModel();
        view = new DashboardView(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// if a certain button is clicked do the actions inside the brackets
        if(e.getActionCommand().equals("add-rent")){
            System.out.println("test");
        }
	}

}
