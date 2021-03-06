package com.vinoigitare.storage.file.text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vinoigitare.model.Song;
import com.vinoigitare.storage.api.Storage;
import com.vinoigitare.storage.api.StorageException;
import com.vinoigitare.util.file.FolderUtil;

@SuppressWarnings("serial")
public class SongTextFileStorage implements Storage<Song> {

	private static final Log log = LogFactory.getLog(SongTextFileStorage.class
			.getName());

	private FolderUtil util;

	String folder;
	String fileExtension;

	public SongTextFileStorage(String folder, String fileExtension) {
		this.folder = folder;
		this.fileExtension = fileExtension;

		util = new FolderUtil(folder);
		log.info("SongTextFileStorage started. Using folder: " + folder);
	}

	@Override
	public String store(Song song) throws StorageException {
		String id = song.getId();
		String fileName = getFilenameFromId(id);
		String content = song.getChords();

		try {
			util.storeTextual(fileName, content);
		} catch (IOException e) {
			throw new StorageException(e.getMessage(), e);
		}
		log.info("Stored song file: " + fileName);

		return id;
	}

	@Override
	public void remove(String id) throws StorageException {

		String fileName = getFilenameFromId(id);

		try {
			util.removeFile(fileName);
		} catch (FileNotFoundException e) {
			throw new StorageException(e.getMessage(), e);
		}
		log.info("Removed song file: " + fileName);
	}

	@Override
	public Song load(String id) throws StorageException {
		String fileName = getFilenameFromId(id);
		String chords = null;
		try {
			chords = util.loadTextual(fileName);
		} catch (IOException e) {
			throw new StorageException(e.getMessage(), e);
		}
		log.trace("Loaded song from file: " + fileName);

		String songId = getIdFromFileName(fileName);
		String[] tokens = songId.split("\\s-\\s");
		String artist = tokens[0];
		String title = tokens[1];

		Song song = new Song(artist, title, chords);
		return song;
	}

	@Override
	public List<Song> loadAll() throws StorageException {
		log.trace("Loading all songs... ");
		List<String> fileNames = util.listFileNames();
		ArrayList<String> ids = new ArrayList<String>();
		for (String fileName : fileNames) {
			if (fileName.endsWith(fileExtension)) {
				ids.add(getIdFromFileName(fileName));
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
	public List<String> listIds() throws StorageException {
		List<String> fileNames = util.listFileNames();
		ArrayList<String> ids = new ArrayList<String>();
		for (String fileName : fileNames) {
			if (fileName.endsWith(fileExtension)) {
				String id = getIdFromFileName(fileName);
				ids.add(id);
			}
		}

		return ids;
	}

	@Override
	public boolean contains(String id) throws StorageException {
		String fileName = getFilenameFromId(id);
		return util.fileExists(fileName);
	}

	private String getFilenameFromId(String id) {
		return id + "." + fileExtension;
	}

	private String getIdFromFileName(String fileName) {
		return fileName.substring(0, fileName.lastIndexOf("."));
	}
}
