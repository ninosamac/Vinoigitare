package com.vinoigitare.filestorage.text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ninosamac.storage.file.util.FolderUtil;
import com.vinoigitare.model.Artist;
import com.vinoigitare.model.Song;
import com.vinoigitare.services.api.DataService;
import com.vinoigitare.services.api.DataServiceException;

public class SongTextFileStorage implements DataService<Song> {

	private static final String FILE_EXTENSION = ".txt";
	private FolderUtil util;

	public SongTextFileStorage(String folder) {
		util = new FolderUtil(folder);
	}

	@Override
	public void store(Song song) throws DataServiceException {
		String fileName = song.getId() + FILE_EXTENSION;
		String content = song.getChords();
		try {
			util.storeTextual(fileName, content);
		} catch (IOException e) {
			throw new DataServiceException(e.getMessage(), e);
		}

	}

	@Override
	public void remove(Song song) throws DataServiceException {
		String fileName = song.getId() + FILE_EXTENSION;
		try {
			util.removeFile(fileName);
		} catch (FileNotFoundException e) {
			throw new DataServiceException(e.getMessage(), e);
		}

	}

	@Override
	public boolean exists(Song song) throws DataServiceException {
		String fileName = song.getId() + FILE_EXTENSION;
		return util.fileExists(fileName);
	}

	@Override
	public Song load(Object id) throws DataServiceException {
		String fileName = id.toString() + FILE_EXTENSION;
		String chords = null;
		try {
			chords = util.loadTextual(fileName);
		} catch (IOException e) {
			throw new DataServiceException(e.getMessage(), e);
		}

		String[] tokens = fileName.split("\\s-\\s");
		String artist = tokens[0];
		String title = tokens[1].replace(FILE_EXTENSION, "");

		Song song = new Song(new Artist(artist), title, chords);
		return song;
	}

	@Override
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

	@Override
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

}
