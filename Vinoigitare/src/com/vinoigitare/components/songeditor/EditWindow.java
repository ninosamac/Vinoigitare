package com.vinoigitare.components.songeditor;

import com.vaadin.ui.Window;
import com.vinoigitare.model.Song;

@SuppressWarnings("serial")
public class EditWindow extends Window  {

	public EditWindow(Song song) {
		
		setSizeFull();
		center();
		SongEditor songEditor = new SongEditor(song);
		songEditor.setWindow(this);
		setContent(songEditor);

	}

}
