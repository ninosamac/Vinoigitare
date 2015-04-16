package com.vinoigitare.eventbus;

public interface EventDispatcher<T> {

	public void dipatch(T event);

}
