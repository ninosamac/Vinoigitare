package com.vinoigitare;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vinoigitare.actions.EditSongAction;
import com.vinoigitare.actions.NewSongAction;
import com.vinoigitare.actions.RemoveSongAction;
import com.vinoigitare.components.MainLayout;
import com.vinoigitare.eventbus.EventBus;
import com.vinoigitare.services.SongService;
import com.vinoigitare.services.TextFileSongService;
import com.vinoigitare.settings.api.SettingsService;

@SuppressWarnings("serial")
@Theme("vinoigitare")
public class VinoigitareUI extends UI implements Vinoigitare {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = com.vinoigitare.VinoigitareUI.class)
	public static class Servlet extends VaadinServlet {
	}

	private static final EventBus eventBus = new EventBus();
	private ActionRegistry actionRegistry = new ActionRegistry();
	private SettingsService settings;
	private TextFileSongService textFileSongService;
	private MainLayout layout;

	public VinoigitareUI() {
		textFileSongService = new TextFileSongService(eventBus);

		actionRegistry.registerAction(new NewSongAction());
		actionRegistry.registerAction(new EditSongAction());
		actionRegistry.registerAction(new RemoveSongAction());

	}

	public void bindSettingsService(SettingsService service) {
		this.settings = service;
	}

	@Override
	protected void init(VaadinRequest request) {
		layout = new MainLayout(this);
		setSizeFull();
		setContent(layout);
	}

	@Override
	public final EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public final SongService getSongService() {
		return textFileSongService;
	}

	@Override
	public final SettingsService getSettings() {
		return settings;
	}

	@Override
	public final ActionRegistry getActionRegistry() {
		return actionRegistry;
	}

	@Override
	public void show(Component component) {
		layout.show(component);
	}

}