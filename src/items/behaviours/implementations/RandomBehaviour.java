package items.behaviours.implementations;

import items.actions.ActionUtils;
import items.actions.ActionUtils.Actions;
import items.behaviours.BaseBehaviour;

/**
 * Implementation of BaseBehaviour that takes a random action every turn.
 * 
 * @author Xavier Peñalosa
 */
public class RandomBehaviour extends BaseBehaviour {

	/**
	 * Constructor.
	 */
	public RandomBehaviour() {
		super();
	}

	@Override
	public Actions getAction() {
		return ActionUtils.getRandomAction();
	}

}
