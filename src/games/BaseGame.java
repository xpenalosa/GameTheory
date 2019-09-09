package games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import games.utils.GameUtils;
import items.actors.BaseActor;
import items.prizes.BasePrize;

public class BaseGame {

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
		if (this.population.size() == 0) {
			return;
		}
		this.assignToPrizes();
		this.updatePopulation();
	}

	protected void assignToPrizes() {
		// Clear previous assignations
		prizes.parallelStream().forEach(prize -> prize.clear());
		// Randomize assignations
		Collections.shuffle(this.population);

		// Assign to prizes
		for (int i = 0; i < this.population.size(); i++) {
			BasePrize prize = this.prizes.get(i % this.prizes.size());
			prize.addActor(this.population.get(i));
		}
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
		System.out.println(String.format("%s", populationCount.toString()));
	}

}
