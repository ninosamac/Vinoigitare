package com.vinoigitare.components.songpanel;

import com.vaadin.ui.Window;
import com.vinoigitare.components.songeditor.SongEditor;
import com.vinoigitare.model.Song;

@SuppressWarnings("serial")
public class EditWindow extends Window {

	public EditWindow(Song song) {

		setSizeFull();
		center();
		setCaption("Edit: "+song.getId());
		SongEditor songEditor = new SongEditor(song);
		setContent(songEditor);

	}

}
