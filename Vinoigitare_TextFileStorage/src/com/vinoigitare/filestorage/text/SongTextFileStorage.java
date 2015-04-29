package com.vinoigitare.filestorage.text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ninosamac.storage.file.util.FolderUtil;
import com.vinoigitare.model.Artist;
import com.vinoigitare.model.Song;
import com.vinoigitare.services.api.DataService;
import com.vinoigitare.services.api.DataServiceException;

public class SongTextFileStorage implements DataService<Song>{

	private static final Log log = LogFactory.getLog(SongTextFileStorage.class
			.getName());

	private static final String FILE_EXTENSION = ".txt";
	private FolderUtil util;

	protected SongTextFileStorage(String folder) {
		util = new FolderUtil(folder);
		log.info("SongTextFileStorage created for folder: " + folder);
	}

	public void store(Song song) throws DataServiceException {
		String fileName = song.getId() + FILE_EXTENSION;
		String content = song.getChords();
		try {
			util.storeTextual(fileName, content);
			log.trace("Stored song file: " + fileName);
		} catch (IOException e) {
			throw new DataServiceException(e.getMessage(), e);
		}

	}

	public void remove(Song song) throws DataServiceException {
		String fileName = song.getId() + FILE_EXTENSION;
		try {
			util.removeFile(fileName);
			log.trace("Removed song file: " + fileName);
		} catch (FileNotFoundException e) {
			throw new DataServiceException(e.getMessage(), e);
		}

	}

	public Song load(Comparable<?> id) throws DataServiceException {
		String fileName = id.toString() + FILE_EXTENSION;
		String chords = null;
		try {
			chords = util.loadTextual(fileName);
			log.trace("Loaded song from file: " + fileName);
		} catch (IOException e) {
			throw new DataServiceException(e.getMessage(), e);
		}

		String[] tokens = fileName.split("\\s-\\s");
		String artist = tokens[0];
		String title = tokens[1].replace(FILE_EXTENSION, "");

		Song song = new Song(new Artist(artist), title, chords);
		return song;
	}

	public List<Song> loadAll() throws DataServiceException {
		List<String> fileNames = util.listFileNames();
		ArrayList<String> ids = new ArrayList<String>();
		for (String fileName : fileNames) {
			if (fileName.endsWith(FILE_EXTENSION)) {
				ids.add(fileName.replace(FILE_EXTENSION, ""));
			}
		}
		ArrayList<Song> songs = new ArrayList<Song>();
		for (String id : ids) {
			Song song = load(id);
			songs.add(song);
		}
		return songs;
	}

	public List<?> listIds() throws DataServiceException {
		List<String> fileNames = util.listFileNames();
		for (String fileName : fileNames) {
			if (!fileName.endsWith(FILE_EXTENSION)) {
				fileNames.remove(fileName);
			}
		}
		ArrayList<String> ids = new ArrayList<String>();
		for (String fileName : fileNames) {
			String id = fileName.replace(FILE_EXTENSION, "");
			ids.add(id);
		}
		return ids;
	}

	@Override
	public boolean contains(Comparable<?> id) throws DataServiceException {
		String fileName = id + FILE_EXTENSION;
		return util.fileExists(fileName);	
	}

}
