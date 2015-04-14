package com.vinoigitare.event;

public interface EventDispatcher<T> {
	
	public void dipatch(T event);

}
