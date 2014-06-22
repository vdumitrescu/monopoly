package edu.vuum.mocca;

public abstract class PlayerThread extends Thread {

	private final int number;
	private final GameKeeper keeper;
	private final int maxTurns;

	public PlayerThread(int number, int maxTurns, GameKeeper keeper) {
		this.number = number;
		this.keeper = keeper;
		this.maxTurns = maxTurns;
	}

	@Override
	public void run() {
		for (int i = 0; i < maxTurns; i++) {
			keeper.lock();
			try {
				keeper.awaitTurn(number);
				executeTurn(i+1);
				keeper.endTurn(number);
			} catch (InterruptedException e) {
				// game over!
			} finally {
				keeper.unlock();
			}
		}
		keeper.gameOver();
	}

	protected abstract void executeTurn(int turn);
}