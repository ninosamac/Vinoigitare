package com.vinoigitare.components.search;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vaadin.data.Validator;
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

	private static final Log log = LogFactory.getLog(SearchDialog.class);

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
				try {
					Validator validator = new Validator() {
						@Override
						public void validate(Object value)
								throws InvalidValueException {
							String text = ((String) value).trim();
							if (text.length() < 3)
								throw new InvalidValueException(
										"Please enter at least three characters.");
						}
					};
					textField.addValidator(validator);
					textField.validate();
					textField.removeAllValidators();
					searchString = textField.getValue();
					Notification.show("Search for: " + searchString);
					log.debug("Search for: " + searchString);
				} catch (Exception e) {
					Notification.show("Please fill the search field properly.");
					e.printStackTrace();
				}

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
