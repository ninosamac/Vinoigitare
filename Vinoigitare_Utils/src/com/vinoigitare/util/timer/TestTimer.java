package com.vinoigitare.util.timer;

import java.util.concurrent.Callable;

public class TestTimer implements Timer {

	private long time;
	
	public void setTime(long time) {
		this.time = time;
	}

	@Override
	public long now() {
	return time;
	}

	@Override
	public void increase(long millis) {
		time = time + millis;
	}

	@Override
	public void decrease(long millis) {
		time = time - millis;
	}

	@Override
	public void schedule(Callable<?> callable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scheduleLater(Callable<?> callable, long startAt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scheduleRepeating(Callable<?> callable, long delayInMillis, int numRepeats) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scheduleRepeatingLater(Callable<?> callable, long startTime, long delayInMillis, int numRepeats) {
		// TODO Auto-generated method stub
		
	}

}
