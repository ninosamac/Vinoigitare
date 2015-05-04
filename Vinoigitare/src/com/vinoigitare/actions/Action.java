package com.vinoigitare.actions;

import com.vinoigitare.Vinoigitare;

/**
 * Interface for UI Actions.
 */
public interface Action extends Comparable<Action> {

	/**
	 * @return Action identifier - must be unique in system.
	 */
	String getId();

	/**
	 * Used in menus for grouping actions under a sub menu.
	 * 
	 * @return name of top menu item this action belongs to
	 */
	String getGroupId();

	/**
	 * Caption for this action.
	 * 
	 * @return caption
	 */
	String getCaption();

	/**
	 * @return description
	 */
	String getDescription();

	/**
	 * @return theme relative URL of icon for this action
	 */
	String getIconUrl();

	/**
	 * @return type of parameter object.
	 */
	Class<?> getParameterType();

	/**
	 * Execution method.
	 * 
	 * @param vinoigitare
	 *            Vinoigitare application
	 * @param param
	 *            Parameter(s) for this action
	 */
	void execute(Vinoigitare vinoigitare, Object param);

}
