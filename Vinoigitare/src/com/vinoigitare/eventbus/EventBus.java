package com.vinoigitare.eventbus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.vaadin.external.org.slf4j.Logger;
import com.vaadin.external.org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class EventBus implements Serializable, SongSelectedHandler {

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
		log.info("register eventHandler " + eventHandler + " for " + eventType);
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
			for (EventHandler handler : targetHandlers) {
				try {
					handler.onEvent(event);
				} catch (Exception ex) {
					log.error("Error firing event", ex);
				}
			}

		} else {
			log.warn("No registererd handlers found for " + event);
		}

	}

	@Override
	public void onEvent(SongSelected event) {
		fireEvent(event);
	}

}
