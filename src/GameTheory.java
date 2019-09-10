import java.util.logging.Level;
import java.util.logging.Logger;

import games.BaseGame;

public class GameTheory {

	protected static Logger logger = Logger.getLogger(GameTheory.class.getName());

	public static void main(String[] args) {
		if (args.length != 3) {
			logger.log(Level.WARNING, "\n\tTake a look at the README file to see the usage.");
			return;
		}

		int iterations = Integer.valueOf(args[0]);
		int prizeCount = Integer.valueOf(args[1]);
		String codedPopulation = args[2];

		BaseGame game = new BaseGame(prizeCount, codedPopulation);
		for (int i = 0; i < iterations; i++) {
			game.executeRound();
		}
		game.printGameSummary();
	}
}
