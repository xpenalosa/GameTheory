package items.behaviours;

/**
 * Behaviour of an actor when fighting with another actor over a prize.
 * 
 * @author Xavier Peñalosa
 */
public class BaseBehaviour {

	/**
	 * Empty constructor.
	 */
	public BaseBehaviour() {
	}

	/**
	 * When fighting another actor for a prize, determine the proportion of the
	 * price that the actor wants to obtain.
	 * 
	 * @param otherId The type identifier for the opposing actor.
	 * @return The portion of the prize that this actor wants to obtain from the
	 *         fight.
	 */
	public double getDesiredPortion(Integer otherId) {
		return 1.0d;
	}

	/**
	 * Alter behaviour between rounds.
	 */
	public void prepareNextRound() {
	}

}