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
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import com.vinoigitare.Constants;
import com.vinoigitare.Vinoigitare;
import com.vinoigitare.eventbus.EventBus;
import com.vinoigitare.events.ClearSearchFilterEvent;
import com.vinoigitare.events.SearchEvent;

@SuppressWarnings("serial")
public class SearchDialog extends Window {

	private static final Log log = LogFactory.getLog(SearchDialog.class);

	protected String searchString;

	private TextField textField;

	private Button searchButton;

	private Button cancelButton;

	public SearchDialog() {

		center();
		setWidth(25, Unit.EM);

		setCaption("Search");
		setCloseShortcut(KeyCode.ESCAPE);

		Layout layout = createLayout();
		setContent(layout);
		textField.focus();
	}

	private Layout createLayout() {

		HorizontalLayout layout = new HorizontalLayout();
		layout.setMargin(true);

		textField = new TextField();
		Validator validator = new SearchStringValidator();
		textField.addValidator(validator);

		layout.addComponent(textField);

		searchButton = new Button();
		Resource searchIcon = new ThemeResource(Constants.ICON_SEARCH);
		searchButton.setIcon(searchIcon);
		searchButton.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					textField.validate();
					searchString = textField.getValue();

					EventBus eventBus = ((Vinoigitare) getUI()).getEventBus();
					eventBus.publish(new SearchEvent(searchString));

					log.debug("Search for: " + searchString);
				} catch (Exception e) {
					Notification.show(e.getMessage());
				}

			}
		});
		layout.addComponent(searchButton);

		cancelButton = new Button();
		Resource Icon = new ThemeResource(Constants.ICON_CANCEL);
		cancelButton.setIcon(Icon);
		cancelButton.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				EventBus eventBus = ((Vinoigitare) getUI()).getEventBus();
				eventBus.publish(new ClearSearchFilterEvent());

				textField.clear();

				log.debug("Clear search filter.");
			}
		});
		layout.addComponent(cancelButton);

		return layout;
	}

	public String getSearchString() {
		return searchString;
	}

}

class SearchStringValidator implements Validator {

	private static final long serialVersionUID = 1L;

	@Override
	public void validate(Object value) throws InvalidValueException {

		String text = ((String) value).trim();

		// clear criteria
		if (".".equals(text))
			return;

		// invalid search text
		if (text.length() < 3)
			throw new InvalidValueException(
					"Please enter at least three characters.");

	}
};