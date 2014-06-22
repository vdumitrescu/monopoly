package edu.vuum.mocca.monopoly;

import edu.vuum.mocca.GameKeeper;
import edu.vuum.mocca.PlayerThread;


public class MonopolyGame {

	private final GameKeeper keeper;
	private final PlayerThread[] players;
	private final int playerCount;
	private final MonopolyBoard board;
	
	private final String[] tokens = { "Wheelbarrow", "Battleship", "Racecar", "Thimble", "Boot",
			"Dog", "Top hat", "Iron", "Cannon", "Train" };

	public MonopolyGame(int playerCount, int maxTurns) {
		if (playerCount > tokens.length) {
			throw new RuntimeException("Too many players, maximum is " + tokens.length);
		}
		
		this.playerCount = playerCount;
		keeper = new GameKeeper(playerCount);
		board = new MonopolyBoard();
		players = new PlayerThread[playerCount];
		for (int i = 0; i < playerCount; i++) {
			players[i] = new MonopolyPlayerThread(i, tokens[i], maxTurns, board, keeper);
		}
	}
	
	public void run() {
		for (int i = 0; i < playerCount; i++) {
			players[i].start();
		}
		
		try {
			keeper.awaitGameOver();
		} catch (InterruptedException e) {
			// game over
		}
	}
	
	public static void main(String args[]) {
		new MonopolyGame(6, 10).run();
	}
}
