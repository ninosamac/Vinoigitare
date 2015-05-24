package com.vinoigitare.components.search;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import com.vinoigitare.Constants;

@SuppressWarnings("serial")
public class SearchDialog extends Window {

	protected String searchString;

	public SearchDialog() {

		center();
		setWidth(25, Unit.EM);
		// setHeight(12, Unit.EX);

		setCaption("Search");
		setCloseShortcut(KeyCode.ESCAPE);

		Panel panel = new Panel();

		HorizontalLayout layout = new HorizontalLayout();
		layout.setMargin(true);

		final TextField textField = new TextField();
		layout.addComponent(textField);

		Button searchButton = new Button();
		Resource searchIcon = new ThemeResource(Constants.ICON_SEARCH);
		searchButton.setIcon(searchIcon);
		searchButton.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				textField.validate();
				searchString = textField.getValue();
				Notification.show("Searching for: " + searchString);
			}
		});

		layout.addComponent(searchButton);

		Button cancelButton = new Button();
		Resource Icon = new ThemeResource(Constants.ICON_CANCEL);
		cancelButton.setIcon(Icon);
		cancelButton.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				close();
			}
		});

		layout.addComponent(cancelButton);

		panel.setContent(layout);
		setContent(panel);
	}

	public String getSearchString() {
		return searchString;
	}

}
