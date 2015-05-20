package com.vinoigitare.storage.file.text;

import com.vinoigitare.model.Song;

public class TestHelper {
	public Song getTestSong() {
		String artist = "Mišo Kovaè";
		Song song = new Song(artist, "Dalmacija u mom oku",
				"Pitaju me zasto pjevam\n" + "o tom moru, buri, jugu\n"
						+ "govore mi kako sanjam\n" + "obecanu zemlju drugu\n"
						+ "\n" + "Govore mi da se trgnem\n"
						+ "dok je vrijeme, dok se moze\n"
						+ "k'o da mogu isprat kise\n"
						+ "onu davnu sol sa koze\n" + "\n" + "Ref.\n"
						+ "Vidim jata onih istih ptica\n"
						+ "istu zvijezdu previsoku\n"
						+ "opet zivi stotinama lica\n"
						+ "Dalmacija u mom oku\n" + "\n"
						+ "Znam da nije bila samo moja\n"
						+ "mozda sam u snu duboku\n"
						+ "ali zivi stotinama boja\n" + "Dalmacija u mom oku\n"
						+ "\n" + "Govore mi cemu price\n"
						+ "ljudi drugi zivot zive\n"
						+ "odvezane sve su barke\n"
						+ "i brodovi s' tvoje rive\n" + "\n"
						+ "Ali negdje zvona zvone\n" + "i skidaju ljudi kape\n"
						+ "i dok djecji plac se cuje\n"
						+ "sastaju se nove klape\n" + "\n" + "Ref.");
		return song;
	}
}
