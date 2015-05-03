package com.vinoigitare.components;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vinoigitare.Constants;

@SuppressWarnings("serial")
public class HelloPage extends Panel {

	public HelloPage() {

		VerticalLayout layout = new VerticalLayout();

		layout.setSizeFull();
		layout.setMargin(true);

		Label title = new Label("Vino i gitare");
		title.addStyleName(Constants.STYLE_SONG_TITLE);

		Label author = new Label("Nino Samac");
		author.addStyleName(Constants.STYLE_SONG_ARTIST);

		Label text = new Label(
				"<p>\r\n"
						+ "Zbirka akorda Vino i Gitare nastala je kao rezultat moje težnje da na jednom mjestu prikupim i složim akorde naðene u raznim arhivama na Internetu. Postoji èitav niz web site-ova na kojima se, redovito ili ne, osvježavaju ove arhive, i pretpostavljam da nije lako imati zbirku akorda koja je ujedno i potpuna i uredna. Svaki gitarista koji se bavi zabavnim pjesmama s podruèja nekadašnje Jugoslavije znat æe cijeniti papirnu verziju akorda i tekstova svojih omiljenih pjesama, naroèito u onim trenucima tuluma ili gaže kad se problem prisjetiti èak i vlastitog repertoara, a kamoli akorda za dotiène pjesme. Mislim da æe ova knjiga, koja osim akorda i tekstova za brojne pjesme sadrži i tablicu hvatova za najèešæe korištene akorde, umnogome pomoæi mnogim gitaristima, kako poèetnicima, tako i onim iskusnijim, te im omoguæiti brže savladavanje novog repertoara, kao i opušteniji nastup pred uvijek kritiènom publikom. \r\n"
						+ "</p>\r\n"
						+ "<p>\r\n"
						+ "U prvom redu moram se zahvaliti svim onim vrijednim ljudima koji su skidali akorde za pjesme koje ovdje možete naæi i zatim ih nesebièno podijelili s Internet populacijom gitarista. Takoðer se zahvaljujem webmasterima koji te akorde prikupljaju i nude na svojim site-ovima u obliku ureðenih arhiva koje sam ja, kao što se može vidjeti, vrijedno obišao.\r\n"
						+ "</p>\r\n"
						+ "<p>\r\n"
						+ "Ovo je, mogli bismo reæi, radna verzija knjige i nadam se da æe knjiga vremenom biti redovno upotpunjavana i ispravljana. Naravno da to ne ovisi samo o mome trudu u skidanju pjesama i prikupljanju slobodno objavljenih akorda na Internetu, nego prvenstveno o prilozima, primjedbama, ispravkama i sugestijama koje æe mi korisnici ove knjige upuæivati. Naravno, molim vas da svaku misao, kritiku, pohvalu ili prilog posveæen ovoj knjizi pošaljete na moju e-mail adresu. Ne šaljite nikakve viruse, bombe, bojne otrove, prijetnje i uvrede, šovinistièke i nacionalistièke parole i sl. jer vam za takvu vrstu problema ne mogu ponuditi struènu pomoæ. \r\n"
						+ "</p>\r\n"
						+ "<p>\r\n"
						+ "Još jednom se zahvaljujem ljudima koji nesebièno dijele sa svijetom svoje znanje i \r\n"
						+ "dobru volju, a na osnovu èega je i bilo moguæe napraviti ovu knjigu i tako i dalje pridonijeti kulturi druženja i muziciranja na ovim prostorima. Naravno, ovo knjigu možete po volji kopirati i dijeliti s drugima. \r\n"
						+ "</p>\r\n" + "<p><b>\r\n" + "Srdaèno Vaš, \r\n"
						+ "<br>Nino Samac \r\n" + "</b></p>");

		text.setContentMode(ContentMode.HTML);

		layout.addComponent(title);
		layout.addComponent(author);
		layout.addComponent(text);

		setContent(layout);
	}

}
