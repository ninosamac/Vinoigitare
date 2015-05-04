package com.vinoigitare.components.menu;

import java.util.ArrayList;

import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Notification;
import com.vinoigitare.ActionRegistry;
import com.vinoigitare.Vinoigitare;
import com.vinoigitare.actions.Action;
import com.vinoigitare.actions.RemoveSongAction;

@SuppressWarnings("serial")
public class MainMenu extends MenuBar {

	public MainMenu(Vinoigitare vinoigitare) {

		ActionRegistry actionRegistry = vinoigitare.getActionRegistry();
		ArrayList<String> actionGroupIds = actionRegistry.getActionGroupIds();

		for (String groupId : actionGroupIds) {
			MenuItem topMenuItem = addItem(groupId, null, null);

			ArrayList<Action> actions = actionRegistry
					.getActionsForGroup(groupId);

			for (Action action : actions) {

				String caption = action.getCaption();
				MenuItem menuItem = topMenuItem.addItem(caption, null);
				Command command = getCommand(action);
				menuItem.setCommand(command);
				String description = action.getDescription();
				menuItem.setDescription(description);
				String resourceId = action.getIconUrl();
				Resource icon = new ThemeResource(resourceId);
				menuItem.setIcon(icon);
				
			}
		}

//		MenuItem fileMenuItem = addItem("File", null, null);
//
//		Command newSongCommand = getNewSongCommand();
//		MenuItem newSongItem = fileMenuItem.addItem("New", newSongCommand);
//		newSongItem.setDescription("Add new song");
//
//		Command editSongCommand = getEditSongCommand();
//		MenuItem editSongItem = fileMenuItem.addItem("Edit", editSongCommand);
//		editSongItem.setDescription("Edit the song");
//
//		Action action = new RemoveSongAction();
//		Command command = getCommand(action);
//		String caption = action.getCaption();
//		ThemeResource icon = new ThemeResource(action.getIconUrl());
//		MenuItem removeSongItem = fileMenuItem.addItem(caption, icon, command);
//		String description = action.getDescription();
//		removeSongItem.setDescription(description);

		// Command removeSongCommand = getRemoveSongCommand();
		// MenuItem removeSongItem = fileMenuItem.addItem("Remove",
		// removeSongCommand);
		// removeSongItem.setDescription("Remove the song");
		//

		MenuItem aboutMenuItem = addItem("About", null, null);

	}

	private Command getNewSongCommand() {
		Command command = new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				Notification.show(selectedItem.getDescription());

			}

		};
		return command;
	}

	private Command getEditSongCommand() {
		Command command = new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				Notification.show(selectedItem.getDescription());

			}

		};
		return command;
	}

	private void createMenuItemForAction(Action action) {
		Command command = getCommand(action);
	}

	private Command getCommand(Action action) {
		Command command = new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				Notification.show(selectedItem.getDescription());

			}

		};
		return command;
	}

}
