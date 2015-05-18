package com.vinoigitare.eventbus;

public interface EventHandler<T extends Event<?>> {

	public void onEvent(T event);

}
