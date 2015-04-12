package com.vinoigitare;

import java.util.Collection;

import com.vaadin.ui.Tree;

public class SongTree extends Tree {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Collection<Song> songs;

	public SongTree(Collection<Song> songs) {
		super();
		this.songs = songs;
	}

	@Override
	public void attach() {
		super.attach();
		init();
	}

	protected void init() {

		setSizeFull();
		populateItems();

	}

	private void populateItems() {
		addItem("Families");
		addItem("The Jackson");
		addItem("The Simpsons");
		addItem("The Rothschilds");
		addItem("The Hapsburgs");
		addItem("The Addams");
		setParent("The Jackson", "Families");
		setParent("The Simpsons", "Families");
		setParent("The Rothschilds", "Families");
		setParent("The Hapsburgs", "Families");
		setParent("The Addams", "Families");
		setChildrenAllowed("The Jackson", false);
		setChildrenAllowed("The Simpsons", false);
		setChildrenAllowed("The Rothschilds", false);
		setChildrenAllowed("The Hapsburgs", false);
		setChildrenAllowed("The Addams", false);
		expandItemsRecursively("Families");

	}
}
