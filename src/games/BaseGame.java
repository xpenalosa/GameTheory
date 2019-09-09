package games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import items.actors.BaseActor;
import items.prizes.BasePrize;

public class BaseGame {

	protected static Logger logger = Logger.getLogger(BaseGame.class.getName());

	protected List<BasePrize> prizes;

	protected List<BaseActor> population;

	public BaseGame(int prizeSize, int startingActors) {
		this.prizes = new ArrayList<BasePrize>();
		for (int i = 0; i < prizeSize; i++) {
			this.prizes.add(new BasePrize());
		}
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
		this.population.parallelStream().forEach(actor->actor.reduceEnergy(1.0d));
	}

	public void printPopulations() {
		// Collect data into actorId:count map
		Map<Integer, Long> populationCount = this.population.stream()
				.collect(Collectors.groupingBy(actor -> actor.getId(), Collectors.counting()));
		// Log data
		BaseGame.logger.log(Level.INFO, String.format("Current populations: %s", populationCount.toString()));
	}

}
