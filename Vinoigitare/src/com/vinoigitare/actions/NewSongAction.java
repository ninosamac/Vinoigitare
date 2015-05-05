package com.vinoigitare.actions;

import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vinoigitare.Constants;
import com.vinoigitare.Vinoigitare;
import com.vinoigitare.components.songeditor.EditWindow;
import com.vinoigitare.model.Song;

@SuppressWarnings("serial")
public class NewSongAction extends AbstractAction {

	@Override
	public String getId() {
		return "NewSong";
	}

	@Override
	public String getGroupId() {
		return "File";
	}

	@Override
	public String getDescription() {
		return "Create a new song.";
	}

	@Override
	public Class<?> getParameterType() {
		return Song.class;
	}

	@Override
	public String getCaption() {
		return "New";
	}

	@Override
	public String getIconUrl() {
		return Constants.ICON_NEW_SONG;
	}

	@Override
	public void execute(Vinoigitare vinoigitare, Object param) {

		Window editWindow = new EditWindow(null);
		UI ui = (UI) vinoigitare;
		ui.addWindow(editWindow);

	}

}
