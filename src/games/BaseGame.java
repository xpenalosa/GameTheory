package games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import games.utils.GameUtils;
import items.actors.BaseActor;
import items.prizes.BasePrize;

public class BaseGame {

	protected static Logger logger = Logger.getLogger(BaseGame.class.getName());

	protected List<BasePrize> prizes;

	protected List<BaseActor> population;

	public BaseGame(int prizeSize, String codedPopulationString) {
		this.prizes = new ArrayList<BasePrize>();
		for (int i = 0; i < prizeSize; i++) {
			this.prizes.add(new BasePrize());
		}
		this.population = GameUtils.decodePopulationString(codedPopulationString);
	}

	public void executeRound() {
		this.assignToPrizes();
		this.updatePopulation();
	}

	protected void assignToPrizes() {
		// Clear previous assignations
		prizes.parallelStream().forEach(prize -> prize.clear());
		// Randomize assignations
		Collections.shuffle(this.population);

		// Assign to prizes
		int unassignedPopulation = 0;
		for (int i = 0; i < this.population.size(); i++) {
			if (!prizes.get(i % prizes.size()).addActor(this.population.get(i))) {
				unassignedPopulation++;
			}
		}
		BaseGame.logger.log(Level.INFO, String.format("Unassigned actors: %d", unassignedPopulation));
	}

	protected void updatePopulation() {
		// Resolve all conflicts and update energies for every actor
		this.prizes.parallelStream().forEach(prize -> prize.updateActors());
		// Remove population without energy to survive
		this.population.removeIf(actor -> !actor.canSurvive());
		// Add new population based on the surviving population with enough energy
		List<BaseActor> newActors = new ArrayList<BaseActor>();
		for (BaseActor actor : this.population) {
			if (actor.canReproduce()) {
				newActors.add(new BaseActor(actor));
			}
		}
		this.population.addAll(newActors);
		// Reset population energy
		this.population.parallelStream().forEach(actor->actor.reduceEnergy(actor.getEnergy()));
	}

	public void printPopulations() {
		// Collect data into BehaviourType:count map
		Map<String, Long> populationCount = this.population.stream()
				.collect(Collectors.groupingBy(actor -> actor.getBehaviourName(), Collectors.counting()));
		// Log data
		BaseGame.logger.log(Level.INFO, String.format("Current populations: %s", populationCount.toString()));
	}

}
