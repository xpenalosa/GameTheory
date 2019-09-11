package items.behaviours.implementations;

import items.actions.ActionUtils.Actions;
import items.behaviours.BaseBehaviour;

/**
 * Implementation of BaseBehaviour that always tries to obtain all the energy.
 * 
 * @author Xavier Peñalosa
 */
public class TakeBehaviour extends BaseBehaviour {

	/**
	 * Constructor.
	 */
	public TakeBehaviour() {
		super();
	}

	@Override
	public Actions getAction() {
		return Actions.TAKE;
	}
}
