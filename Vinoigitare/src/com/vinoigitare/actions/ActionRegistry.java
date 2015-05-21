package com.vinoigitare.actions;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ActionRegistry {

	private ArrayList<Action> actions = new ArrayList<Action>();

	private final static Log log = LogFactory.getLog(ActionRegistry.class
			.getName());

	public ActionRegistry() {

	}

	public void registerAction(Action action) {
		if (action == null) {
			throw new NullPointerException("Can not register action: null");
		}

		actions.add(action);
		log.info("Action registered: " + action);
	}

	public void unRegisterAction(Action action) {
		if (action == null) {
			throw new NullPointerException("Can not unregister action: null");
		}

		actions.remove(action);
		log.info("Action unregistered: " + action);
	}

	public final ArrayList<String> getActionGroupIds() {
		ArrayList<String> result = new ArrayList<String>();
		for (Action action : actions) {
			String groupId = action.getGroupId();
			if (!result.contains(groupId)) {
				result.add(groupId);
			}
		}
		return result;
	}

	public final ArrayList<Action> getActions() {
		ArrayList<Action> result = new ArrayList<Action>();
		result.addAll(actions);
		return result;
	}

	public final ArrayList<Action> getActionsForGroup(String groupId) {
		ArrayList<Action> result = new ArrayList<Action>();
		for (Action action : actions) {
			if (action.getGroupId().equals(groupId))
				result.add(action);
		}
		return result;
	}
}
