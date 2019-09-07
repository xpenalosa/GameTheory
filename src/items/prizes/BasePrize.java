package items.prizes;

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

	public boolean addActor(BaseActor actor) {
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
			return false;
		}
		return true;
	}

	public void updateActors() {

		switch (this.actorCount) {
		case 1:
			this.a1.increaseEnergy(this.a1.getDesiredPortion(null));
			break;
		case 2:
			double energyA1 = this.a1.getDesiredPortion(this.a2);
			double energyA2 = this.a2.getDesiredPortion(this.a1);
			if (energyA1 + energyA2 > 1.0d) {
				// Actors are unable to share the prize; neither gets a portion
				return;
			} else {
				// Actors can share the price; both get their portions
				this.a1.increaseEnergy(energyA1);
				this.a2.increaseEnergy(energyA2);
			}
			break;
		default:
			return;
		}
	}

	public void clear() {
		this.a1 = null;
		this.a2 = null;
		this.actorCount = 0;
	}

}
