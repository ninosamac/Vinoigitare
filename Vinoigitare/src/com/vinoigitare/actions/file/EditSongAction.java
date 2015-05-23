package com.vinoigitare.actions.file;

import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vinoigitare.Constants;
import com.vinoigitare.Vinoigitare;
import com.vinoigitare.actions.AbstractAction;
import com.vinoigitare.components.songeditor.SongEditor;
import com.vinoigitare.model.Song;

@SuppressWarnings("serial")
public class EditSongAction extends AbstractAction {

	@Override
	public String getId() {
		return "EditSong";
	}

	@Override
	public String getGroupId() {
		return "File";
	}

	@Override
	public String getDescription() {
		return "Edit selected song.";
	}

	@Override
	public Class<?> getParameterType() {
		return Song.class;
	}

	@Override
	public String getCaption() {
		return "Edit";
	}

	@Override
	public String getIconUrl() {
		return Constants.ICON_EDIT_SONG;
	}

	@Override
	public void execute(Vinoigitare vinoigitare, Object param) {

		Song song = (Song) param;
		Window songEditor = new SongEditor(song);
		UI ui = (UI) vinoigitare;
		ui.addWindow(songEditor);

	}

}
