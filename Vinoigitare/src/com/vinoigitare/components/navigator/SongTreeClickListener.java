package com.vinoigitare.components.navigator;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.shared.MouseEventDetails.MouseButton;
import com.vaadin.ui.Notification;
import com.vinoigitare.eventbus.EventBus;
import com.vinoigitare.eventbus.SongSelected;
import com.vinoigitare.model.Artist;
import com.vinoigitare.model.Song;

@SuppressWarnings("serial")
public class SongTreeClickListener implements ItemClickListener {

	private EventBus eventBus;

	public SongTreeClickListener(EventBus eventBus) {
		super();
		this.eventBus = eventBus;
	}

	@Override
	public void itemClick(ItemClickEvent event) {

		Object itemId = event.getItemId();

		if (event.getButton() == MouseButton.LEFT) {

			if (itemId instanceof Song) {
				System.out.println("Song: " + itemId);
				Song song = (Song) itemId;
				SongSelected songSelected = new SongSelected(song);
				eventBus.onEvent(songSelected);
			}

			else if (itemId instanceof Artist) {
				System.out.println("Artist: " + itemId);
			}
		} else if (event.getButton() == MouseButton.RIGHT) {
			Notification.show("CONTEXT MENU!");
		}
	}

}
