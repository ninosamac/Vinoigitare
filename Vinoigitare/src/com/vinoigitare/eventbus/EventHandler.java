package com.vinoigitare.eventbus;

public interface EventHandler<T> {

	public void onEvent(T event);

}
