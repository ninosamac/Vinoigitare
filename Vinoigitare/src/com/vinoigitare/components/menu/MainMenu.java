package com.vinoigitare.components.menu;

import java.util.ArrayList;

import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.MenuBar;
import com.vinoigitare.ActionRegistry;
import com.vinoigitare.Constants;
import com.vinoigitare.Vinoigitare;
import com.vinoigitare.actions.Action;
import com.vinoigitare.eventbus.EventHandler;
import com.vinoigitare.events.SongSelected;
import com.vinoigitare.model.Song;
import com.vinoigitare.pages.HelloPage;

@SuppressWarnings({ "serial", "rawtypes" })
public class MainMenu extends MenuBar implements EventHandler {

	private Song selectedSong = null;

	public MainMenu(final Vinoigitare vinoigitare) {

		ActionRegistry actionRegistry = vinoigitare.getActionRegistry();
		ArrayList<String> actionGroupIds = actionRegistry.getActionGroupIds();

		for (String groupId : actionGroupIds) {
			MenuItem topMenuItem = addItem(groupId, null, null);

			ArrayList<Action> actions = actionRegistry
					.getActionsForGroup(groupId);

			for (Action action : actions) {

				createMenuItem(topMenuItem, action);

			}
		}

		Resource aboutIcon = new ThemeResource(Constants.ICON_ABOUT);
		Command aboutCommand = new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				Component aboutPage = new HelloPage();
				vinoigitare.show(aboutPage);
			}
		};
		MenuItem aboutMenuItem = addItem("About", aboutIcon, aboutCommand);

	}

	private MenuItem createMenuItem(MenuItem topMenuItem, Action action) {

		String caption = action.getCaption();
		MenuItem menuItem = topMenuItem.addItem(caption, null);

		Command command = getCommand(action);
		menuItem.setCommand(command);

		String description = action.getDescription();
		menuItem.setDescription(description);

		String resourceId = action.getIconUrl();
		Resource icon = new ThemeResource(resourceId);
		menuItem.setIcon(icon);

		return menuItem;
	}

	private Command getCommand(final Action action) {
		Command command = new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				if (action.getParameterType().equals(Song.class)) {
					// song related actions
					Vinoigitare vinoigitare = (Vinoigitare) getUI();
					action.execute(vinoigitare, selectedSong);
				}
			}
		};
		return command;
	}

	@Override
	public void onEvent(com.vinoigitare.eventbus.Event event) {
		if (event.getType().equals(SongSelected.class)) {
			SongSelected songSelected = (SongSelected) event;
			selectedSong = songSelected.getSong();
		}
	}

}
