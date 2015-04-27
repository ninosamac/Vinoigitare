package com.vinoigitare.components.songeditor;

import com.vaadin.ui.Window;
import com.vinoigitare.Vinoigitare;
import com.vinoigitare.eventbus.EventBus;
import com.vinoigitare.eventbus.EventHandler;
import com.vinoigitare.model.Song;

@SuppressWarnings("serial")
public class EditWindow extends Window implements
		EventHandler<SongEditCanceledEvent> {

	public EditWindow(Vinoigitare vinoigitare, Song song) {
		
		EventBus eventBus = vinoigitare.getEventBus();
		eventBus.registerForEvents(SongEditCanceledEvent.class, this);

		setSizeFull();
		center();
		SongEditor songEditor = new SongEditor(song);
		setContent(songEditor);

	}

	@Override
	public void onEvent(SongEditCanceledEvent event) {
		this.close();
	}

}
