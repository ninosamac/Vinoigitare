package com.vinoigitare.components.menu;

import java.util.ArrayList;

import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.MenuBar;
import com.vinoigitare.Vinoigitare;
import com.vinoigitare.actions.Action;
import com.vinoigitare.actions.ActionRegistry;
import com.vinoigitare.eventbus.EventBus;
import com.vinoigitare.eventbus.EventHandler;
import com.vinoigitare.events.SongSelectedEvent;
import com.vinoigitare.model.Song;

@SuppressWarnings({ "serial" })
public class LeftMenu extends MenuBar implements
		EventHandler<SongSelectedEvent> {

	private Song selectedSong = null;

	public LeftMenu(final Vinoigitare vinoigitare) {

		EventBus eventBus = vinoigitare.getEventBus();
		eventBus.registerForEvents(SongSelectedEvent.class, this);

		ActionRegistry actionRegistry = vinoigitare.getActionRegistry();
		ArrayList<String> actionGroupIds = actionRegistry.getActionGroupIds();

		for (String groupId : actionGroupIds) {
			MenuItem topItem = addItem(groupId, null, null);

			ArrayList<Action> actions = actionRegistry
					.getActionsForGroup(groupId);

			for (Action action : actions) {

				createMenuItem(topItem, action);

			}
		}

	}

	private MenuItem createMenuItem(MenuItem topItem, Action action) {

		String caption = action.getCaption();
		MenuItem menuItem = topItem.addItem(caption, null);

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
					// actions on selected song.
					Vinoigitare vinoigitare = (Vinoigitare) getUI();
					action.execute(vinoigitare, selectedSong);
				} else {
					Vinoigitare vinoigitare = (Vinoigitare) getUI();
					action.execute(vinoigitare, null);
				}

			}
		};
		return command;
	}

	@Override
	public void onEvent(SongSelectedEvent event) {
		selectedSong = event.getSong();
	}

}
