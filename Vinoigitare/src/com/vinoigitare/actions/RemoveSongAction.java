package com.vinoigitare.actions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;
import com.vinoigitare.Constants;
import com.vinoigitare.Vinoigitare;
import com.vinoigitare.components.ConfirmationDialog;
import com.vinoigitare.model.Song;
import com.vinoigitare.services.SongService;
import com.vinoigitare.services.SongServiceException;


@SuppressWarnings("serial")
public class RemoveSongAction extends AbstractAction {

	private final static Log log = LogFactory.getLog(RemoveSongAction.class
			.getName());

	@Override
	public String getId() {
		return "RemoveSong";
	}

	@Override
	public String getGroupId() {
		return "File";
	}

	@Override
	public String getDescription() {
		return "Remove selected song.";
	}

	@Override
	public Class<?> getParameterType() {
		return Song.class;
	}

	@Override
	public String getCaption() {
		return "Remove";
	}

	@Override
	public String getIconUrl() {
		return Constants.ICON_REMOVE_SONG;
	}

	@Override
	public void execute(final Vinoigitare vinoigitare, Object param) {
		final Song song = (Song) param;

		CloseListener listener = new CloseListener() {

			@Override
			public void windowClose(CloseEvent e) {
				ConfirmationDialog confirmationDialog = (ConfirmationDialog) e
						.getWindow();
				if (confirmationDialog.isConfirmed()) {
					removeSong(song);
				}

			}

			private void removeSong(Song song) {

				 SongService songService = vinoigitare.getSongService();
				try {
					songService.remove(song.getId());
				} catch (SongServiceException e) {
					log.error(e);
				}
				
				Notification.show("Song removed: "+song);
			}

		};
		ConfirmationDialog confirmationDialog = new ConfirmationDialog(
				"Remove:", song.toString(), listener);
		((UI) vinoigitare).addWindow(confirmationDialog);

	}

}
