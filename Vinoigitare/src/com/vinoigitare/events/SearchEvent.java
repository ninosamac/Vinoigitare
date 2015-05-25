package com.vinoigitare.events;

import com.vinoigitare.eventbus.Event;

public class SearchEvent implements Event {

	private final static Class<SearchEvent> type = SearchEvent.class;

	private String searchText;

	public SearchEvent(String searchText) {
		this.searchText = searchText;
	}

	public String getSearchText() {
		return searchText;
	}

	@Override
	public Class<?> getType() {
		return type;
	}
}
