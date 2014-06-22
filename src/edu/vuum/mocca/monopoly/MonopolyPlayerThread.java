package edu.vuum.mocca.monopoly;

import edu.vuum.mocca.GameKeeper;
import edu.vuum.mocca.PlayerThread;
import edu.vuum.mocca.monopoly.MonopolyBoard.*;

public class MonopolyPlayerThread extends PlayerThread {

	private final String token;
	private final MonopolyBoard board;
	private Position position;

	public MonopolyPlayerThread(int number, String token, int maxTurns, MonopolyBoard board, GameKeeper keeper) {
		super(number, maxTurns, keeper);
		this.token = token;
		this.board = board;
		position = board.startPosition();
	}

	@Override
	protected void executeTurn(int turn) {
		say("It's my " + turn(turn) + " turn");
		say("I'm currently on " + position);
		
		Dice dice = board.rollDice();
		say("I've rolled: " + dice);
		
		position = board.advance(dice, position);
		if (position.hasPassedGo()) {
			say("Passed through Go, collected $200");
		}
		say("I've landed on " + position + "\n");
	}
	
	private void say(String what) {
		System.out.println(token + ": " + what);
	}
	
	private String turn(int turn) {
		if (turn % 10 == 1) {
			return turn + "st";
		} else {
			if (turn % 10 == 2) {
				return turn + "nd";
			} else if (turn % 10 == 3) {
				return turn + "rd";
			} else return turn + "th";
		}
	}
}