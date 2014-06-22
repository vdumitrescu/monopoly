package edu.vuum.mocca;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class GameKeeper {

	/**
	 * Number of players in the game
	 */
	private final int numPlayers;
	
	/**
	 * The lock and its conditions, one per player 
	 */
	private final ReentrantLock lock;
	private final Condition conditions[];
	
	/**
	 * A latch to count players exiting the game
	 */
	private final CountDownLatch latch;

	/**
	 * This is the number of the current player
	 */
	private volatile int currentPlayer;

	/**
	 * Initialization of the Game Keeper
	 * 
	 * @param numPlayers  number of players in game
	 */
	public GameKeeper(int numPlayers) {
		this.numPlayers = numPlayers;
		
		// initialize the latch with number of players
		this.latch = new CountDownLatch(numPlayers);
		
		// create a fair lock
		lock = new ReentrantLock(true);
		
		// allocate the conditions for each player
		conditions = new Condition[numPlayers];
		for (int index = 0; index < numPlayers; ++index) {
			conditions[index] = lock.newCondition();
		}
		
		// First player is selected
		currentPlayer = 0;
	}
	
	// Instead of exposing the lock, we wrap lock/unlock
	public void lock() {
		lock.lock();
	}
	public void unlock() {
		lock.unlock();
	}
	
	// Instead of exposing the latch, we wrap countDown/await
	public void gameOver() {
		latch.countDown();
	}
	public void awaitGameOver() throws InterruptedException {
		latch.await();
	}
	
	/**
	 * A player thread will call this to wait its turn
	 * 
	 * @param player   the number of the player waiting
	 * @throws InterruptedException
	 */
	public void awaitTurn(int player) throws InterruptedException {
		while (player != currentPlayer) {
			conditions[player].await();
		}
	}
	
	/**
	 * A player thread will call this to signal end of turn
	 * 
	 * @param player  the player whose turn has ended
	 */
	public void endTurn(int player) {
		currentPlayer = (currentPlayer + 1) % numPlayers;
		conditions[currentPlayer].signal();
	}
}
