package com.vinoigitare.actions;

import com.vaadin.ui.UI;
import com.vinoigitare.Constants;
import com.vinoigitare.Vinoigitare;
import com.vinoigitare.components.search.SearchDialog;

@SuppressWarnings("serial")
public class SearchAction extends AbstractAction {

	@Override
	public String getId() {
		return "Search";
	}

	@Override
	public String getGroupId() {
		return "View";
	}

	@Override
	public String getCaption() {
		return "Search";
	}

	@Override
	public String getDescription() {
		return "Search songs for a piece of text";
	}

	@Override
	public String getIconUrl() {
		return Constants.ICON_SEARCH;
	}

	@Override
	public Class<?> getParameterType() {
		return String.class;
	}

	@Override
	public void execute(Vinoigitare vinoigitare, Object param) {

		final SearchDialog searchDialog = new SearchDialog();
		((UI) vinoigitare).addWindow(searchDialog);

	}

}
