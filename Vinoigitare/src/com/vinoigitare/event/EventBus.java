package com.vinoigitare.event;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.vaadin.external.org.slf4j.Logger;
import com.vaadin.external.org.slf4j.LoggerFactory;

public class EventBus implements SongSelectedHandler {

	private static final Logger log = LoggerFactory.getLogger(EventBus.class
			.getName());

	/**
	 * Collection of event handlers grouped by event type.
	 */
	@SuppressWarnings("rawtypes")
	private Hashtable<Class<?>, List<EventHandler>> eventHandlers;

	/**
	 * Default constructor.
	 */
	@SuppressWarnings("rawtypes")
	public EventBus() {
		eventHandlers = new Hashtable<Class<?>, List<EventHandler>>();
	}

	@SuppressWarnings("rawtypes")
	public void registerForEvents(Class<?> eventType,
			EventHandler<?> eventHandler) {
		 log.info("register eventHandler " + eventHandler + " for " +
		 eventType);
		// Synchronized to disable changing of event handlers list while sending
		// events
		synchronized (eventHandlers) {
			if (!eventHandlers.containsKey(eventType)) {
				eventHandlers.put(eventType, new ArrayList<EventHandler>());
			}
			eventHandlers.get(eventType).add(eventHandler);
		}
	}

	public void unRegister(Class<?> eventType, EventHandler<?> eventHandler) {
		 log.info("unregister eventHandler " + eventHandler + " from "
		 + eventType);
		// Synchronized to disable changing of event handlers list while sending
		// events
		synchronized (eventHandlers) {
			if (eventHandlers.containsKey(eventType)) {
				eventHandlers.get(eventType).remove(eventHandler);
			}
		}
	}

	/**
	 * Fire wrapped event to listeners.
	 * 
	 * @param event
	 *            event to be sent to listeners
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected final void fireEvent(Event<?> event) {
		// log.trace("fire " + event + " to eventhandlers");
		// Synchronized to disable changing of event handlers list while sending
		// events
		// synchronized (eventHandlers) {
		List<EventHandler> targetHandlers = eventHandlers.get(event.getClass());
		if (targetHandlers != null) {
			// log.trace("firing " + event + " to " + targetHandlers.size()
			// + " eventhandlers");
			for (EventHandler handler : targetHandlers) {
				try {
					handler.onEvent(event);

					// CHECKSTYLE:OFF
				} catch (Exception ex) {
					// CHECKSTYLE:ON
					// Catching all exceptions to ensure that event handling
					// error on listener
					// does not break process of sending events to other
					// listeners
					// log.error("Error firing event", ex);
				}
			}

		} else {
			// log.trace("No registererd handlers found for " + event);
		}

	}

	@Override
	public void onEvent(SongSelected event) {
		fireEvent(event);
	}

}
