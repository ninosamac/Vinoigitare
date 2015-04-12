package com.example.vinoigitare;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("vinoigitare")
public class VinoigitareUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = com.example.vinoigitare.VinoigitareUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

		Label title = new Label("The Song");
		layout.addComponent(title);

		Label artist = new Label("John Doe");
		layout.addComponent(artist);

		Label text = new Label();
		text.setContentMode(ContentMode.PREFORMATTED);
		text.setValue(getLoremIpsum());
		layout.addComponent(text);

	}

	private String getLoremIpsum() {
		return "Queen\r\n"
				+ "A Kind Of Magic\r\n"
				+ "\r\n"
				+ "Words and music by Roger Taylor\r\n"
				+ "\r\n"
				+ "Transcribed by Damien Foster (d_foster@hilbert.maths.utas.edu.au) \r\n"
				+ "N.C. (no chord!)\r\n"
				+ "Its a kind of magic, its a kind of magic, its a kind of magic\r\n"
				+ "\r\n"
				+ "    A                    B9\r\n"
				+ "One dream, one soul, one prize, one goal\r\n"
				+ "\r\n"
				+ "    Dmaj7            A\r\n"
				+ "One golden glance, of what should be (its a kind of magic)\r\n"
				+ "\r\n"
				+ "                        B9                Dmaj7          A\r\n"
				+ "One shaft of light that shows the way, no mortal man can win this day.\r\n"
				+ "\r\n"
				+ "                     B9                   Dmaj7          A\r\n"
				+ "The bell that rings inside your mind, is challenging the doors of time\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "    F#m7          D                  F#m7              E\r\n"
				+ "The waiting seems eternity here, the day will dawn, on sanity\r\n"
				+ "\r\n"
				+ "   D              A                                   D           A\r\n"
				+ "Is this a kind of magic, (its a kind of magic), there can be only one\r\n"
				+ "\r\n"
				+ "     E         G       D                           E\r\n"
				+ "This rage that lasts a thousand years will soon be gone\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "     A                  B9             Dmaj7          A\r\n"
				+ "This flame that burns inside of me, Im hearing secret harmonies\r\n"
				+ "[ Tab from: http://www.guitartabs.cc/tabs/q/queen/a_kind_of_magic_tab.html ]\r\n"
				+ "                        B9               Dmaj7          A\r\n"
				+ "The bell that rings inside your mind, is challening the doors of time\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "D  A   D   A   D   A  D  A  \r\n"
				+ "\r\n"
				+ "\r\n"
				+ "     E         G       D                           E\r\n"
				+ "This rage that lasts a thousand years will soon be done\r\n"
				+ "\r\n"
				+ "     D            A            D           A\r\n"
				+ "This is a kind of magic, there can be only one\r\n"
				+ "\r\n"
				+ "     E         G       D                            E11  E7\r\n"
				+ "This rage that lasts a thousand years, will soon be done\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "D A D A D A  etc and fade out.\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "Some chord diagrams that might be useful:\r\n"
				+ "\r\n"
				+ "       A     B9   Dmaj7 F#m7   D     E\r\n"
				+ "\r\n"
				+ "E -----0-----2-----2-----2-----2-----0------------------------------------------\r\n"
				+ "B -----2-----2-----2-----5-----3-----0------------------------------------------\r\n"
				+ "G -----2-----2-----2-----2-----2-----1------------------------------------------\r\n"
				+ "D -----2-----1-----0-----2-----0-----2------------------------------------------\r\n"
				+ "A -----0-----2-----x-----4-----x-----2------------------------------------------\r\n"
				+ "E -----x-----x-----x-----2-----x-----0------------------------------------------";

	}
}