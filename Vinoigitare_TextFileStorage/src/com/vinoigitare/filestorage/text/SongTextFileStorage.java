package com.vinoigitare.filestorage.text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ninosamac.storage.file.util.FolderUtil;
import com.vinoigitare.model.Song;
import com.vinoigitare.services.api.DataService;
import com.vinoigitare.services.api.DataServiceException;

@SuppressWarnings("serial")
public class SongTextFileStorage implements DataService<Song> {

	private static final Log log = LogFactory.getLog(SongTextFileStorage.class
			.getName());

	private static final String FILE_EXTENSION = ".txt";
	private FolderUtil util;

	protected SongTextFileStorage(String folder) {
		util = new FolderUtil(folder);
		log.info("SongTextFileStorage started. Using folder: " + folder);
	}

	@Override
	public String store(Song song) throws DataServiceException {
		String id = song.getId();
		String fileName = getFilenameFromId(id);
		String content = song.getChords();
		try {
			util.storeTextual(fileName, content);
			log.info("Stored song file: " + fileName);
		} catch (IOException e) {
			throw new DataServiceException(e.getMessage(), e);
		}
		return id;
	}

	@Override
	public Song remove(String id) throws DataServiceException {
		Song song = load(id);
		String fileName = getFilenameFromId(id);
		
		try {
			util.removeFile(fileName);
			log.info("Removed song file: " + fileName);
		} catch (FileNotFoundException e) {
			throw new DataServiceException(e.getMessage(), e);
		}
		return song;
	}

	@Override
	public Song load(String id) throws DataServiceException {
		String fileName = getFilenameFromId(id);
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

		Song song = new Song(artist, title, chords);
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
	public List<String> listIds() throws DataServiceException {
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
	public boolean contains(String id) throws DataServiceException {
		String fileName = getFilenameFromId(id);
		return util.fileExists(fileName);
	}

	private String getFilenameFromId(String id) {
		return id + FILE_EXTENSION;
	}
}
