package com.vinoigitare.util.timer;

import java.util.concurrent.Callable;

public class RealTimer implements Timer {

	@Override
	public long now() {
		return System.currentTimeMillis();
	}

	@Override
	public void setTime(long time) {
		throw new UnsupportedOperationException(
				"Can not call this method on RealTimer. Use TestTimer instead.");
	}

	@Override
	public void increase(long millis) {
		throw new UnsupportedOperationException(
				"Can not call this method on RealTimer. Use TestTimer instead.");
	}

	@Override
	public void decrease(long millis) {
		throw new UnsupportedOperationException(
				"Can not call this method on RealTimer. Use TestTimer instead.");

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
	public void scheduleRepeating(Callable<?> callable, long delayInMillis,
			int numRepeats) {
		// TODO Auto-generated method stub

	}

	@Override
	public void scheduleRepeatingLater(Callable<?> callable, long startTime,
			long delayInMillis, int numRepeats) {
		// TODO Auto-generated method stub

	}

}
