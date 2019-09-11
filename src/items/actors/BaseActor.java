package items.actors;

import items.actions.ActionUtils.Actions;
import items.behaviours.BaseBehaviour;
import items.behaviours.utils.BehaviourFactory;

/**
 * Base entity of the game theory population. Each entity defines its energy
 * consumption required for surviving a round and reproducing.
 * 
 * @author Xavier Peñalosa
 */
public class BaseActor {

	/**
	 * Energy required to survive a round.
	 */
	public static final double SURVIVAL_ENERGY = 0.5d;
	/**
	 * Energy required to reproduce in a round.
	 */
	public static final double REPRODUCTIVE_ENERGY = 2.0d;
	
	/**
	 * Behaviour of the actor in front of other actors.
	 */
	protected BaseBehaviour actorBehaviour;

	/**
	 * Energy of the actor in the current round. Influences survivability and the
	 * possibility of reproducing.
	 */
	protected Double actorEnergy;

	/**
	 * Empty constructor.
	 */
	public BaseActor() {
		this.actorBehaviour = BehaviourFactory.create("IdleBehaviour");
		this.actorEnergy = 0.0d;
	}

	/**
	 * Constructor.
	 */
	public BaseActor(BaseBehaviour behaviour) {
		this.actorBehaviour = behaviour;
		this.actorEnergy = 0.0d;
	}

	/**
	 * Constructor.
	 */
	public BaseActor(BaseBehaviour behaviour, Double energy) {
		this.actorBehaviour = behaviour;
		this.actorEnergy = energy;
	}

	/**
	 * Copy constructor.
	 * 
	 * @param other The BaseActor to copy.
	 */
	public BaseActor(BaseActor other) {
		this.actorBehaviour = BehaviourFactory.create(other.getBehaviourName());
		this.actorEnergy = 0.0d;
	}

	/**
	 * Get the name of the actor's behaviour.
	 * 
	 * @return The behaviour's name
	 */
	public String getBehaviourName() {
		return this.actorBehaviour.toString();
	}

	/**
	 * Get the actor's energy.
	 * 
	 * @return {@linkplain #actorEnergy}
	 */
	public Double getEnergy() {
		return this.actorEnergy;
	}

	/**
	 * Checks whether the actor has enough energy to survive the round.
	 * 
	 * @return True if the actor can survive. False otherwise.
	 */
	public boolean canSurvive() {
		return this.actorEnergy >= SURVIVAL_ENERGY;
	}

	/**
	 * Checks whether the actor has enough energy to reproduce and create another
	 * actor of its same type.
	 * 
	 * @return True if the actor can reproduce. False otherwise.
	 */
	public boolean canReproduce() {
		return this.actorEnergy >= REPRODUCTIVE_ENERGY;
	}

	/**
	 * Increase the actor's energy.
	 * 
	 * @param increasedAmount The amount of energy obtained.
	 */
	public void increaseEnergy(Double increasedAmount) {
		this.actorEnergy = Math.min(BaseActor.REPRODUCTIVE_ENERGY, this.actorEnergy + increasedAmount);
	}

	/**
	 * Reduce the actor's energy.
	 * 
	 * @param reducedAmount The amount of energy lost.
	 */
	public void reduceEnergy(Double reducedAmount) {
		this.actorEnergy = this.actorEnergy - reducedAmount;
	}

	/**
	 * When fighting another actor for a prize, determine the proportion of the
	 * price that the actor wants to obtain.
	 * 
	 * @return The portion of the prize that this actor wants to obtain from the
	 *         fight.
	 */
	public Actions getPerformedAction() {
		Actions actionTaken = Actions.GIVE;
		// Failsafe. No behaviour means actor will eventually die due to lack of energy
		if (this.actorBehaviour != null) {
			actionTaken = this.actorBehaviour.getAction();
		}
		return actionTaken;
	}

	@Override
	public String toString() {
		return String.format("%s: %.2f", this.actorBehaviour, this.actorEnergy);
	}

}
