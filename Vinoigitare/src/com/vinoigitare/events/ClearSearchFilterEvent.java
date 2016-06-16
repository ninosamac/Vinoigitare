package com.vinoigitare.events;

import com.vinoigitare.eventbus.Event;

/**
 * Indicates that user has cancelled searching for specific song and components
 * should return to initial state.
 * 
 * @author nino.samac
 *
 */
public class ClearSearchFilterEvent implements Event {

	private final static Class<ClearSearchFilterEvent> type = ClearSearchFilterEvent.class;

	public ClearSearchFilterEvent() {
	}

	@Override
	public Class<?> getType() {
		return type;
	}
}
