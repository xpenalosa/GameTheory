package items.prizes;

import items.actions.ActionUtils.Actions;
import items.actors.BaseActor;

/**
 * Prize used by the actors to obtain one unit of energy. When two actors try to
 * obtain the same prize, they fight and distribute portions of the prize,
 * affected by their behaviour.
 * 
 * @author Xavier Peñalosa.
 */
public class BasePrize {

	/**
	 * Actor fighting for the prize.
	 */
	protected BaseActor a1;

	/**
	 * Actor fighting for the prize.
	 */
	protected BaseActor a2;

	/**
	 * Amount of actors fighting for this prize in the current round.
	 */
	protected int actorCount;

	public BasePrize() {
		this.a1 = null;
		this.a2 = null;

		this.actorCount = 0;
	}

	public boolean canAddActor() {
		return this.actorCount < 2;
	}

	public void addActor(BaseActor actor) {
		switch (this.actorCount) {
		case 0:
			this.a1 = actor;
			this.actorCount++;
			break;
		case 1:
			this.a2 = actor;
			this.actorCount++;
			break;
		default:
			break;
		}
	}

	public void updateActors() {

		Actions action1 = this.a1 != null ? this.a1.getPerformedAction() : Actions.GIVE;
		Actions action2 = this.a2 != null ? this.a2.getPerformedAction() : Actions.GIVE;
		
		if (this.a1 != null) {
			this.a1.increaseEnergy(Actions.energyRelations[action1.ordinal()][action2.ordinal()]);
		}
		if (this.a2 != null) {
			this.a2.increaseEnergy(Actions.energyRelations[action2.ordinal()][action1.ordinal()]);
		}
	}

	public void clear() {
		this.a1 = null;
		this.a2 = null;
		this.actorCount = 0;
	}

}
