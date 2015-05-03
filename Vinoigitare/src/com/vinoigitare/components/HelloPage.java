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
						+ "Zbirka akorda Vino i Gitare nastala je kao rezultat moje te�nje da na jednom mjestu prikupim i slo�im akorde na�ene u raznim arhivama na Internetu. Postoji �itav niz web site-ova na kojima se, redovito ili ne, osvje�avaju ove arhive, i pretpostavljam da nije lako imati zbirku akorda koja je ujedno i potpuna i uredna. Svaki gitarista koji se bavi zabavnim pjesmama s podru�ja nekada�nje Jugoslavije znat �e cijeniti papirnu verziju akorda i tekstova svojih omiljenih pjesama, naro�ito u onim trenucima tuluma ili ga�e kad se problem prisjetiti �ak i vlastitog repertoara, a kamoli akorda za doti�ne pjesme. Mislim da �e ova knjiga, koja osim akorda i tekstova za brojne pjesme sadr�i i tablicu hvatova za naj�e��e kori�tene akorde, umnogome pomo�i mnogim gitaristima, kako po�etnicima, tako i onim iskusnijim, te im omogu�iti br�e savladavanje novog repertoara, kao i opu�teniji nastup pred uvijek kriti�nom publikom. \r\n"
						+ "</p>\r\n"
						+ "<p>\r\n"
						+ "U prvom redu moram se zahvaliti svim onim vrijednim ljudima koji su skidali akorde za pjesme koje ovdje mo�ete na�i i zatim ih nesebi�no podijelili s Internet populacijom gitarista. Tako�er se zahvaljujem webmasterima koji te akorde prikupljaju i nude na svojim site-ovima u obliku ure�enih arhiva koje sam ja, kao �to se mo�e vidjeti, vrijedno obi�ao.\r\n"
						+ "</p>\r\n"
						+ "<p>\r\n"
						+ "Ovo je, mogli bismo re�i, radna verzija knjige i nadam se da �e knjiga vremenom biti redovno upotpunjavana i ispravljana. Naravno da to ne ovisi samo o mome trudu u skidanju pjesama i prikupljanju slobodno objavljenih akorda na Internetu, nego prvenstveno o prilozima, primjedbama, ispravkama i sugestijama koje �e mi korisnici ove knjige upu�ivati. Naravno, molim vas da svaku misao, kritiku, pohvalu ili prilog posve�en ovoj knjizi po�aljete na moju e-mail adresu. Ne �aljite nikakve viruse, bombe, bojne otrove, prijetnje i uvrede, �ovinisti�ke i nacionalisti�ke parole i sl. jer vam za takvu vrstu problema ne mogu ponuditi stru�nu pomo�. \r\n"
						+ "</p>\r\n"
						+ "<p>\r\n"
						+ "Jo� jednom se zahvaljujem ljudima koji nesebi�no dijele sa svijetom svoje znanje i \r\n"
						+ "dobru volju, a na osnovu �ega je i bilo mogu�e napraviti ovu knjigu i tako i dalje pridonijeti kulturi dru�enja i muziciranja na ovim prostorima. Naravno, ovo knjigu mo�ete po volji kopirati i dijeliti s drugima. \r\n"
						+ "</p>\r\n" + "<p><b>\r\n" + "Srda�no Va�, \r\n"
						+ "<br>Nino Samac \r\n" + "</b></p>");

		text.setContentMode(ContentMode.HTML);

		layout.addComponent(title);
		layout.addComponent(author);
		layout.addComponent(text);

		setContent(layout);
	}

}
