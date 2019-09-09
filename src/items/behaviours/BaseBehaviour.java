package items.behaviours;

import items.actions.ActionUtils.Actions;

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
	 * @return The action taken.
	 */
	public Actions getAction() {
		return Actions.GIVE;
	}

	/**
	 * Alter behaviour between rounds.
	 */
	public void prepareNextRound(Actions opponentAction) {
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

}