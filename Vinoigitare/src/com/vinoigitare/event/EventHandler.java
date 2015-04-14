package com.vinoigitare.event;

public interface EventHandler<T> {
	
	public void onEvent(T event);

}
