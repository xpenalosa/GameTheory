package items.behaviours.implementations;

import items.actions.ActionUtils.Actions;
import items.behaviours.BaseBehaviour;

/**
 * Implementation of BaseBehaviour that acts like {@linkplain ShareBehaviour}
 * until an opponent takes all the energy. After that, it starts acting like
 * {@linkplain TakeBehaviour}.
 * 
 * @author Xavier Peñalosa.
 */
public class VengefulBehaviour extends BaseBehaviour {

	/**
	 * Whether the actor should try to take all energy.
	 */
	protected boolean isVengative;

	public VengefulBehaviour() {
		super();
		this.isVengative = false;
	}

	@Override
	public Actions getAction() {
		if (this.isVengative) {
			return Actions.TAKE;
		} else {
			return Actions.SHARE;
		}
	}

	@Override
	public void prepareNextRound(Actions opponentAction) {
		if (opponentAction == Actions.TAKE) {
			this.isVengative = true;
		}
	}

}
