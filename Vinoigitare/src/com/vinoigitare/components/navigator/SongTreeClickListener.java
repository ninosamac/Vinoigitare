package com.vinoigitare.components.navigator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.shared.MouseEventDetails.MouseButton;
import com.vaadin.ui.Notification;
import com.vinoigitare.eventbus.EventBus;
import com.vinoigitare.events.SongSelected;
import com.vinoigitare.model.Artist;
import com.vinoigitare.model.Song;

@SuppressWarnings("serial")
public class SongTreeClickListener implements ItemClickListener {

	private EventBus eventBus;
	private final static Log log = LogFactory.getLog(SongTreeClickListener.class.getName());

	public SongTreeClickListener(EventBus eventBus) {
		super();
		this.eventBus = eventBus;
	}

	@Override
	public void itemClick(ItemClickEvent event) {

		Object itemId = event.getItemId();

		if (event.getButton() == MouseButton.LEFT) {

			if (itemId instanceof Song) {
				log.trace("Song selected: " + itemId);
				Song song = (Song) itemId;
				SongSelected songSelected = new SongSelected(song);
				eventBus.onEvent(songSelected);
			}

			else if (itemId instanceof Artist) {
				log.trace("Artist selected: " + itemId);
			}
		} else if (event.getButton() == MouseButton.RIGHT) {
			Notification.show("CONTEXT MENU!");
		}
	}

}
