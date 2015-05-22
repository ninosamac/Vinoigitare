package com.vinoigitare.actions;

import java.io.Serializable;

import com.vinoigitare.Vinoigitare;

/**
 * Interface for UI Actions.
 */
public interface Action extends Comparable<Action>, Serializable {

	/**
	 * @return Action identifier - must be unique in system.
	 */
	String getId();

	/**
	 * Used in menus for grouping actions under a menu. Menus are
	 * hierarchical, so a groupId has the form
	 * <code>MenuId/SubMenuId/SubMenuId/etc</code>
	 * 
	 * @return the action group id
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
