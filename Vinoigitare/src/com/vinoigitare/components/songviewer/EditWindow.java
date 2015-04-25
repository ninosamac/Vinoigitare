package com.vinoigitare.components.songviewer;

import com.vaadin.ui.Window;
import com.vinoigitare.components.songeditor.SongEditor;
import com.vinoigitare.model.Song;

@SuppressWarnings("serial")
public class EditWindow extends Window {

	public EditWindow(Song song) {
		
		center();
		setCaption("Edit: "+song.getId());
		SongEditor songEditor = new SongEditor(song);
		setContent(songEditor);

	}

}
