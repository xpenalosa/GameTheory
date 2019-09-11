package items.actions;

import java.sql.Time;
import java.time.Instant;
import java.util.Random;

/**
 * Utility class for actions.
 * 
 * @author Xavier Peñalosa
 */
public abstract class ActionUtils {

	/**
	 * The different actions an actor can take.
	 */
	public static enum Actions {
		GIVE, SHARE, TAKE;

		/**
		 * The energy relations between two actions
		 */
		public static double[][] energyRelations = {
				{ 0.0, 0.0, 0.0 }, // Give
				{ 1.0, 1.0, 0.0 }, // Share
				{ 2.0, 1.5, -0.5 } // Take
		};
	}

	/**
	 * Obtain a random action from the list of available actions.
	 * 
	 * @return An action.
	 */
	public static Actions getRandomAction() {
		Random rand = new Random(Time.from(Instant.now()).getTime());
		return Actions.values()[rand.nextInt(3)];
	}

}
