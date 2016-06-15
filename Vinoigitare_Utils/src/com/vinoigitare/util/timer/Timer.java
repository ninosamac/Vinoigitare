package com.vinoigitare.util.timer;

import java.util.concurrent.Callable;

public interface Timer {

	/**
	 * 
	 * @return current time in milliseconds
	 */
	long now();

	void setTime(long time);

	void increase(long millis);

	void decrease(long millis);

	/**
	 * schedules {@link Callable} object for immediate calling. If there are no callables waiting in queue for calling,
	 * this callable will be called upon
	 * 
	 * @param callable
	 *            the {@link Callable} object
	 */
	void schedule(Callable<?> callable);

	void scheduleLater(Callable<?> callable, long startAt);

	void scheduleRepeating(Callable<?> callable, long delayInMillis, int numRepeats);

	void scheduleRepeatingLater(Callable<?> callable, long startTime, long delayInMillis, int numRepeats);

}
