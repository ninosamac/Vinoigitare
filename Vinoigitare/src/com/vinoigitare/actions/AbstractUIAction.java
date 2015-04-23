package com.vinoigitare.actions;

/**
 * Abstract implementation of UI Action.<BR>
 * Defines default methods for {@link Action} which do not need to be
 * implemented.
 * 
 */
public abstract class AbstractUIAction implements Action {

	private String caption = ""; //$NON-NLS-1$

	/**
	 * Not meant to be called - Abstract class.
	 */
	public AbstractUIAction() {
		// Empty
	}

	/**
	 * Sets caption text for this action.<BR>
	 * This is used internally in system to set localized text for this action.
	 * 
	 * @param captionText
	 *            text to be displayed on UI for this action.
	 */
	public void setCaption(String captionText) {
		caption = captionText;
	}

	/**
	 * This is used internally in system to get caption text for this action.<BR>
	 * Caption text is internally localized and set before this method is meant
	 * to be called.
	 * 
	 * @return caption text for this action in UI.
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getIconUrl() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(Action other) {
		return getId().compareTo(other.getId());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getId());
		sb.append("\n\t"); //$NON-NLS-1$
		sb.append(getSupportedClass());
		sb.append("\n\t\t"); //$NON-NLS-1$
		sb.append(getDescription());
		return sb.toString();

	}

}
