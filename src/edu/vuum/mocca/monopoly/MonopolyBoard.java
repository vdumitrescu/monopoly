package edu.vuum.mocca.monopoly;

import java.security.SecureRandom;

public class MonopolyBoard {
	
	private final SecureRandom random = new SecureRandom();
	private static final String[] squares = {
		"Go", "Mediterranean Avenue", "Community Chest (1)", "Baltic Avenue", "Income Tax",
		"Reading Railroad", "Oriental Avenue", "Chance (1)", "Vermont Avenue", "Connecticut Avenue",
		"Jail / Visiting", "St Charles Place", "Electric Company", "States Avenue", "Virginia Avenue",
		"Pennsylvania Railroad", "St James Place", "Community Chest (2)", "Tennessee Avenue", "New York Avenue",
		"Free Parking", "Kentucky Avenue", "Chance (2)", "Indiana Avenue", "Illinois Avenue",
		"B&O Railroad", "Atlantic Avenue", "Ventnor Avenue", "Water Works", "Marvin Gardens",
		"Go to jail", "Pacific Avenue", "North Carolina Avenue", "Community Chest (3)", "Pennsylvania Avenue",
		"Short Line", "Chance (3)", "Park Place", "Luxury Tax", "BoardWalk"
	};
	
	public Dice rollDice() {
		return new Dice();
	}

	public class Dice {
		private final int one, two;
		
		public Dice() {
			one = 1 + random.nextInt(6);
			two = 1 + random.nextInt(6);
		}
		
		@Override
		public String toString() {
			return one + "-" + two;
		}
		
		public int value() {
			return one + two;
		}

		public boolean isDouble() {
			return one == two;
		}
	}
	
	public Position startPosition() {
		return new Position(0, false);
	}
	
	public Position advance(Dice dice, Position currentPosition) {
		boolean passedGo = false;
		int newLocation = currentPosition.location + dice.value();
		if (newLocation >= squares.length) {
			newLocation = newLocation % squares.length;
			passedGo = true;
		}
		return new Position(newLocation, passedGo);
	}
	
	public class Position {
		private final int location;
		private final boolean passedGo;
		
		public Position(int location, boolean passedGo) {
			this.location = location;
			this.passedGo = passedGo;
		}
		
		@Override
		public String toString() {
			return squares[location];
		}
		
		public boolean hasPassedGo() {
			return passedGo;
		}
	}
}
