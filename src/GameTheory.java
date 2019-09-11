import java.util.logging.Level;
import java.util.logging.Logger;

import games.BaseGame;

public class GameTheory {

	/**
	 * Logger
	 */
	protected static Logger logger = Logger.getLogger(GameTheory.class.getName());

	/**
	 * Application entry point. Expects three arguments:
	 * 
	 * <ul>
	 * <li>Number of iterations to perform.</li>
	 * <li>Number of prizes to add.</li>
	 * <li>Encoded string containing the initial population.</li>
	 * </ul>
	 * 
	 * @param args List of arguments
	 */
	public static void main(String[] args) {
		if (args.length != 3) {
			logger.log(Level.WARNING, "\n\tTake a look at the README file to see the usage.");
			return;
		}

		// Parse args
		int iterations = Integer.valueOf(args[0]);
		int prizeCount = Integer.valueOf(args[1]);
		String codedPopulation = args[2];

		// Instantiate game and run all the iterations
		BaseGame game = new BaseGame(prizeCount, codedPopulation);
		for (int i = 0; i < iterations; i++) {
			game.executeRound();
		}
		// Output summary
		game.printGameSummary();
	}
}
