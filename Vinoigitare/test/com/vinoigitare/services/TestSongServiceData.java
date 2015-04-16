package com.vinoigitare.services;

import com.vinoigitare.model.Artist;
import com.vinoigitare.model.Song;

public class TestSongServiceData {
	
	public void populateTestSongs(){
		
	}

	public Song getDalmacijaUMomOku(){
		Song song = new Song();
		
		Artist misoKovac = new Artist("Mi�o Kova�");
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
		
		Artist misoKovac = new Artist("Mi�o Kova�");
		song.setArtist(misoKovac );
		song.setTitle("Samo jedan dan �ivota");
		song.setChords("G                 D\r\n" + 
				"SAMO JEDAN DAN �IVOTA\r\n" + 
				"          G             C\r\n" + 
				"JO� JEDNU �A�U MI DAJTE SAD\r\n" + 
				"       G\r\n" + 
				"JER JA SUTRA NISAM S VAMA\r\n" + 
				"          D        G\r\n" + 
				"JER SUTRA UMRIJE�U MLAD\r\n" + 
				"           D               G\r\n" + 
				"       MOJ �IVOT JE KRATAK BIO\r\n" + 
				"          D           G\r\n" + 
				"       I PROLAZAN KAO SAN\r\n" + 
				"            C          G\r\n" + 
				"       A JA �ELIM SAMO JEDNO\r\n" + 
				"                D         G\r\n" + 
				"       DA �IVIM JO� JEDAN DAN\r\n" + 
				"           D               G\r\n" + 
				"       MOJ �IVOT JE KRATAK BIO\r\n" + 
				"          D           G\r\n" + 
				"       I PROLAZAN KAO SAN\r\n" + 
				"            C          G\r\n" + 
				"       A JA �ELIM SAMO JEDNO\r\n" + 
				"                D         G\r\n" + 
				"       DA �IVIM JO� JEDAN DAN\r\n" + 
				"\r\n" + 
				"NEKA STARO DRU�TVO DO�E \r\n" + 
				"K*O NEKAD DAVNO U KRAJU MOM\r\n" + 
				"NEK SE �UJE STARA PJESMA\r\n" + 
				"TAJ ZADNJI GLAS SRCA MOG\r\n" + 
				"\r\n" + 
				"SAMO BUKET BIJELIH RU�A\r\n" + 
				"NEK BUDE VJE�NO NA GROBU MOM\r\n" + 
				"SAMO VJETAR NEKA PRI�A\r\n" + 
				"TU TAJNU �IVOTA MOG");
		
		return song;
	}
	
	public Song getSviPjevajuJaNe�ujem(){
		Song song = new Song();
		
		Artist misoKovac = new Artist("Mi�o Kova�");
		song.setArtist(misoKovac );
		song.setTitle("Svi pjevaju, ja ne �ujem");
		song.setChords("   D        A   D   \r\n" + 
				"Sad kad �ivim u tu�ini\r\n" + 
				"                       A\r\n" + 
				"moje srce samo vene\r\n" + 
				"\r\n" + 
				"pa mi do�e da te pitam\r\n" + 
				"       G    A        D \r\n" + 
				"da li sli�i bar na mene.\r\n" + 
				"\r\n" + 
				"         D      A G A    D\r\n" + 
				"ref: Svi pjevaju ja ne�ujem\r\n" + 
				"       A\r\n" + 
				"       nikome se ne radujem\r\n" + 
				"                     G   D\r\n" + 
				"       samo tebe o�ekujem.\r\n" + 
				"\r\n" + 
				"Znam da gre�nik ja sam bio\r\n" + 
				"sad mi sude uspomene\r\n" + 
				"oprosti, odgovori\r\n" + 
				"da li sli�i bar na mene.");
		
		return song;
	}
}
