package com.vinoigitare.components;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vinoigitare.Constants;

@SuppressWarnings("serial")
public class ConfirmationDialog extends Window {

	private String okCaption = "OK";
	private String cancelCaption = "Cancel";
	private String title;
	private String subtitle;
	protected boolean confirmed = false;

	public ConfirmationDialog(String title, String subtitle,
			CloseListener listener) {

		this.title = title;
		this.subtitle = subtitle;

		setWidth("800px");
		setHeight("200px");
		center();

		Layout layout = createLayout();
		setContent(layout);

		addCloseListener(listener);

	}

	public final boolean isConfirmed() {
		return confirmed;
	}

	private Layout createLayout() {
		VerticalLayout layout = new VerticalLayout();

		Label titleLabel = new Label(title);
		titleLabel.addStyleName(Constants.STYLE_DIALOG_TITLE);
		layout.addComponent(titleLabel);

		Label subtitleLabel = new Label(subtitle);
		subtitleLabel.addStyleName(Constants.STYLE_DIALOG_SUBTITLE);
		layout.addComponent(subtitleLabel);

		HorizontalLayout buttons = new HorizontalLayout();

		Button okButton = createOkButton();
		buttons.addComponent(okButton);

		Button cancelButton = createCancelButton();
		buttons.addComponent(cancelButton);

		layout.addComponent(buttons);
		return layout;
	}

	private Button createOkButton() {
		Button button = new Button(okCaption);
		ClickListener listener = new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				confirmed = true;
				close();
			}

		};

		button.addClickListener(listener);
		return button;
	}

	private Button createCancelButton() {
		Button button = new Button(cancelCaption);
		ClickListener listener = new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				confirmed = false;
				close();
			}

		};

		button.addClickListener(listener);
		return button;
	}

}
