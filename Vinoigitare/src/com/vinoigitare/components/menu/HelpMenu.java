package com.vinoigitare.components.menu;

import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.MenuBar;
import com.vinoigitare.Constants;
import com.vinoigitare.Vinoigitare;
import com.vinoigitare.pages.HelloPage;

@SuppressWarnings("serial")
public class HelpMenu extends MenuBar {

	public HelpMenu(final Vinoigitare vinoigitare) {

		addStyleName(Constants.STYLE_HELP_MENU);

		Resource aboutIcon = new ThemeResource(Constants.ICON_ABOUT);
		Command aboutCommand = new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				Component aboutPage = new HelloPage();
				vinoigitare.show(aboutPage);
			}
		};
		addItem("Help", aboutIcon, aboutCommand);

	}

}
