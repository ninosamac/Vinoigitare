package com.vinoigitare.eventbus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings({ "serial", "rawtypes", })
public class EventBus implements Serializable {

	private static final Log log = LogFactory.getLog(EventBus.class.getName());

	/**
	 * Collection of event handlers grouped by event type.
	 */
	private Hashtable<Class<?>, List<EventHandler>> eventHandlers;

	/**
	 * Default constructor.
	 */
	public EventBus() {
		eventHandlers = new Hashtable<Class<?>, List<EventHandler>>();
	}

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
	@SuppressWarnings({ "unchecked" })
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

	
	public void publish(Event event) {
		fireEvent(event);
	}

}
