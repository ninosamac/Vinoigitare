package com.vinoigitare.components.navigator;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vinoigitare.Vinoigitare;
import com.vinoigitare.model.Song;
import com.vinoigitare.services.SongService;
import com.vinoigitare.services.SongServiceException;

@SuppressWarnings({ "serial" })
public class Navigator extends Panel {

	private SongService songService;
	private SongTree songTree;

	private final static Log log = LogFactory.getLog(Navigator.class);

	public Navigator(Vinoigitare vinoigitare) {

		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		layout.setMargin(true);

		songService = vinoigitare.getSongService();

		setSizeFull();

		Collection<Song> songs = new ArrayList<Song>();
		try {
			songs = songService.loadAll();
		} catch (SongServiceException e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		songTree = new SongTree(vinoigitare, songs);
		layout.addComponent(songTree);

		setContent(layout);
	}

}
