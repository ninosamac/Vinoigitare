package com.vinoigitare.services;

import com.vinoigitare.model.Artist;
import com.vinoigitare.model.Song;

public class TestSongServiceData {
	
	public void populateTestSongs(){
		
	}

	public Song getDalmacijaUMomOku(){
		Song song = new Song();
		
		Artist misoKovac = new Artist("Mišo Kovaè");
		song.setArtist(misoKovac );
		song.setTitle("Dalmacija u mom oku");
		song.setChords("G                                      D7\r\n" + 
				"Pitaju me za{to pjevam o tom moru buri jugu,\r\n" + 
				"D7                                     G\r\n" + 
				"govore mi kako sanjam obecanu zemlju drugu.\r\n" + 
				"G                              E7              a\r\n" + 
				"Govore mi da se trgnem, dok je vrijeme, dok se moze,\r\n" + 
				"       C           G        D7           G\r\n" + 
				"k'o da mogu isprat kise onu davnu sol sa koze.\r\n" + 
				"\r\n" + 
				"   G G7    C\r\n" + 
				"     Vidim jata onih istih ptica\r\n" + 
				"     G\r\n" + 
				"     istu zvijezdu previsoku...\r\n" + 
				"          D7\r\n" + 
				"     opet zivi stotinama lica\r\n" + 
				"     C         D7    G\r\n" + 
				"    Dalmacija u mom oku.\r\n" + 
				"\r\n" + 
				"     Znam da nije bila samo moja\r\n" + 
				"     mozda sam u snu duboku...\r\n" + 
				"     ali zivi stotinama boja\r\n" + 
				"     Dalmacija u mom oku.\r\n" + 
				"\r\n" + 
				"Govore mi cemu price, ljudi drugi zivot zive.\r\n" + 
				"Odvezane sve su barke i brodovi s moje rive.\r\n" + 
				"Ali negdje zvona zvone i skidaju ljudi kape\r\n" + 
				"i dok djecji plac se cuje sastaju se nove klape.");
		
		return song;
	}
	
	public Song getSamoJedanDanZivota(){
		Song song = new Song();
		
		Artist misoKovac = new Artist("Mišo Kovaè");
		song.setArtist(misoKovac );
		song.setTitle("Samo jedan dan života");
		song.setChords("G                 D\r\n" + 
				"SAMO JEDAN DAN ŽIVOTA\r\n" + 
				"          G             C\r\n" + 
				"JOŠ JEDNU ÈAŠU MI DAJTE SAD\r\n" + 
				"       G\r\n" + 
				"JER JA SUTRA NISAM S VAMA\r\n" + 
				"          D        G\r\n" + 
				"JER SUTRA UMRIJEÆU MLAD\r\n" + 
				"           D               G\r\n" + 
				"       MOJ ŽIVOT JE KRATAK BIO\r\n" + 
				"          D           G\r\n" + 
				"       I PROLAZAN KAO SAN\r\n" + 
				"            C          G\r\n" + 
				"       A JA ŽELIM SAMO JEDNO\r\n" + 
				"                D         G\r\n" + 
				"       DA ŽIVIM JOŠ JEDAN DAN\r\n" + 
				"           D               G\r\n" + 
				"       MOJ ŽIVOT JE KRATAK BIO\r\n" + 
				"          D           G\r\n" + 
				"       I PROLAZAN KAO SAN\r\n" + 
				"            C          G\r\n" + 
				"       A JA ŽELIM SAMO JEDNO\r\n" + 
				"                D         G\r\n" + 
				"       DA ŽIVIM JOŠ JEDAN DAN\r\n" + 
				"\r\n" + 
				"NEKA STARO DRUŠTVO DOÐE \r\n" + 
				"K*O NEKAD DAVNO U KRAJU MOM\r\n" + 
				"NEK SE ÈUJE STARA PJESMA\r\n" + 
				"TAJ ZADNJI GLAS SRCA MOG\r\n" + 
				"\r\n" + 
				"SAMO BUKET BIJELIH RUŽA\r\n" + 
				"NEK BUDE VJEÈNO NA GROBU MOM\r\n" + 
				"SAMO VJETAR NEKA PRIÈA\r\n" + 
				"TU TAJNU ŽIVOTA MOG");
		
		return song;
	}
	
	public Song getSviPjevajuJaNeÈujem(){
		Song song = new Song();
		
		Artist misoKovac = new Artist("Mišo Kovaè");
		song.setArtist(misoKovac );
		song.setTitle("Svi pjevaju, ja ne èujem");
		song.setChords("   D        A   D   \r\n" + 
				"Sad kad živim u tuðini\r\n" + 
				"                       A\r\n" + 
				"moje srce samo vene\r\n" + 
				"\r\n" + 
				"pa mi doðe da te pitam\r\n" + 
				"       G    A        D \r\n" + 
				"da li slièi bar na mene.\r\n" + 
				"\r\n" + 
				"         D      A G A    D\r\n" + 
				"ref: Svi pjevaju ja neèujem\r\n" + 
				"       A\r\n" + 
				"       nikome se ne radujem\r\n" + 
				"                     G   D\r\n" + 
				"       samo tebe oèekujem.\r\n" + 
				"\r\n" + 
				"Znam da grešnik ja sam bio\r\n" + 
				"sad mi sude uspomene\r\n" + 
				"oprosti, odgovori\r\n" + 
				"da li slièi bar na mene.");
		
		return song;
	}
}
