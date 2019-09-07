package items.behaviours.implementations;

import items.actions.ActionUtils.Actions;
import items.behaviours.BaseBehaviour;

/**
 * Implementation of BaseBehaviour that shares with the opponent.
 * 
 * @author Xavier Peñalosa
 */
public class ShareBehaviour extends BaseBehaviour {

	public ShareBehaviour() {
		super();
	}

	@Override
	public Actions getAction() {
		return Actions.SHARE;
	}

}
