package items.actors;

import com.sun.istack.internal.Nullable;

import items.behaviours.BaseBehaviour;

/**
 * Base entity of the game theory population. Each entity defines its energy
 * consumption required for surviving a round and reproducing.
 * 
 * @author Xavier Peñalosa
 */
public class BaseActor {

	/**
	 * Name to identify the actor
	 */
	protected String actorName;

	/**
	 * Actor identifier. Used to determine their behaviour.
	 */
	protected Integer actorId;

	/**
	 * Energy of the actor in the current round. Influences survivability and the
	 * possibility of reproducing.
	 */
	protected Double actorEnergy;

	/**
	 * Behaviour of the actor in front of other actors.
	 */
	protected BaseBehaviour actorBehaviour;

	/**
	 * Empty constructor.
	 */
	public BaseActor() {
		this.actorName = "BaseActor";
		this.actorId = -1;
		this.actorEnergy = 1.0d;
	}

	/**
	 * Constructor.
	 */
	public BaseActor(String name, Integer id) {
		this.actorName = name;
		this.actorId = id;
		this.actorEnergy = 1.0d;
	}

	/**
	 * Constructor.
	 */
	public BaseActor(String name, Integer id, Double energy) {
		this.actorName = name;
		this.actorId = id;
		this.actorEnergy = energy;
	}

	/**
	 * Assign a behaviour to the actor.
	 */
	public void setBehaviour(BaseBehaviour behaviour) {
		this.actorBehaviour = behaviour;
	}

	/**
	 * Get the actor's name.
	 * 
	 * @return {@linkplain #actorName}
	 */
	public String getName() {
		return this.actorName;
	}

	/**
	 * Get the actor's behaviour identifier.
	 * 
	 * @return {@linkplain #actorId}
	 */
	public Integer getId() {
		return this.actorId;
	}

	/**
	 * Get the actor's name.
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
		return this.actorEnergy >= 1.0d;
	}

	/**
	 * Checks whether the actor has enough energy to reproduce and create another
	 * actor of its same type.
	 * 
	 * @return True if the actor can reproduce. False otherwise.
	 */
	public boolean canReproduce() {
		return this.actorEnergy >= 2.0d;
	}

	/**
	 * Increase the actor's energy.
	 * 
	 * @param increasedAmount The amount of energy obtained.
	 */
	public void increaseEnergy(Double increasedAmount) {
		this.actorEnergy = this.actorEnergy + increasedAmount;
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
	 * @param other The opposing actor.
	 * @return The portion of the prize that this actor wants to obtain from the
	 *         fight.
	 */
	public double getDesiredPortion(@Nullable BaseActor other) {
		double desiredPortion = 0.0d;
		// Failsafe. No behaviour means actor will eventually die due to lack of energy
		if (this.actorBehaviour != null) {
			desiredPortion = this.actorBehaviour.getDesiredPortion(other != null ? other.getId() : -1);
		}
		return desiredPortion;
	}

	@Override
	public String toString() {
		return String.format("(%d) %s: %.2f", this.actorId, this.actorName, this.actorEnergy);
	}

}
