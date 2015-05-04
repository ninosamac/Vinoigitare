package com.vinoigitare.actions;

/**
 * Abstract implementation of UI Action.<BR>
 * Defines default methods for {@link Action} which do not need to be
 * implemented.
 * 
 */
public abstract class AbstractAction implements Action {

	
	/**
	 * Not meant to be called - Abstract class.
	 */
	public AbstractAction() {
		// Empty
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
		sb.append("\t"); //$NON-NLS-1$
		sb.append(getParameterType());
		sb.append("\t"); //$NON-NLS-1$
		sb.append(getDescription());
		return sb.toString();

	}

}
