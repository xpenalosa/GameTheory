package items.behaviours.implementations;

import items.actions.ActionUtils.Actions;
import items.behaviours.BaseBehaviour;

/**
 * Implementation of BaseBehaviour that copies the behaviour of the last
 * opponent.
 * 
 * @author Xavier Peñalosa
 */
public class CopycatBehaviour extends BaseBehaviour {

	/**
	 * Last faced action.
	 */
	protected Actions lastAction;

	public CopycatBehaviour() {
		super();
		this.lastAction = Actions.SHARE;
	}

	@Override
	public Actions getAction() {
		return this.lastAction;
	}

	@Override
	public void prepareNextRound(Actions opponentAction) {
		this.lastAction = opponentAction;
	}

}
