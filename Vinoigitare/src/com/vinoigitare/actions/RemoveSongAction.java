package com.vinoigitare.actions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vinoigitare.Vinoigitare;
import com.vinoigitare.model.Song;
import com.vinoigitare.services.api.DataService;
import com.vinoigitare.services.api.DataServiceException;

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
		return "icons/Cancel.gif";
	}

	@Override
	public void execute(Vinoigitare vinoigitare, Object param) {
		Song song = (Song) param;

		DataService<Song> songService = vinoigitare.getSongService();
		try {
			songService.remove(song);
		} catch (DataServiceException e) {
			log.error(e);
		}

	}

}
