package items.behaviours.implementations;

import items.actions.ActionUtils;
import items.actions.ActionUtils.Actions;
import items.behaviours.BaseBehaviour;

public class RandomBehaviour extends BaseBehaviour {
	
	public RandomBehaviour() {
		super();
	}
	
	@Override
	public Actions getAction() {
		return ActionUtils.getRandomAction();
	}

}
