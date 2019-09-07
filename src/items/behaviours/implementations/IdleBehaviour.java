package items.behaviours.implementations;

import items.actions.ActionUtils.Actions;
import items.behaviours.BaseBehaviour;

/**
 * Implementation of BaseBehaviour that does not fight for energy.
 * 
 * @author Xavier Peñalosa
 */
public class IdleBehaviour extends BaseBehaviour {

	public IdleBehaviour() {
		super();
	}
	
	@Override
	public Actions getAction() {
		return Actions.GIVE;
	};

}
