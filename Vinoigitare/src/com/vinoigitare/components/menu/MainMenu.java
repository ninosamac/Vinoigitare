package com.vinoigitare.components.menu;

import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Notification;

public class MainMenu extends MenuBar{
	
	public MainMenu(){
		
		Command mycommand = new MenuBar.Command() {
		    public void menuSelected(MenuItem selectedItem) {
		    	
		        Notification.show(selectedItem.getText());
		    }  
		};
		
		MenuItem fileMenuItem = addItem("File", null, mycommand);
		MenuItem aboutMenuItem = addItem("About", null, mycommand);
	}
	
}
