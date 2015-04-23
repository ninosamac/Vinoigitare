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
	 * @return actionGroup name of action group this action belongs to
	 */
	String getActionGroup();

	/**
	 * @return description 
	 */
	String getDescription();

	/**
	 * @return theme relative URL to icon for this action
	 */
	String getIconUrl();


	/**
	 * @return type of object this action can be executed on
	 */
	Class<?> getSupportedClass();

	/**
	 * Execution method - called when user selects action from a context menu.
	 * 
	 * @param vinoigitare
	 *            reference to Vinoigitare application
	 * @param object
	 *            reference to object on which to execute this action
	 */
	void execute(Vinoigitare vinoigitare, Object object);

}
