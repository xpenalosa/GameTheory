package games.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import items.actors.BaseActor;
import items.behaviours.utils.BehaviourFactory;

public abstract class GameUtils {

	/**
	 * Parse a coded string and create a list of actors with specific
	 * behaviours.</br>
	 * </br>
	 * The coded string is a concatenation of "{@code <BehaviourType> <Count>}"
	 * separated by semicolons.</br>
	 * e.g. "{@code IdleBehaviour 50;TakeBehaviour 20;RandomBehaviour 20}"
	 * 
	 * @param codedPopulationString The string to decode.
	 * @return A list of {@linkplain BaseActor actors} that conform a population.
	 */
	public static List<BaseActor> decodePopulationString(String codedPopulationString) {
		String[] populationTypes = codedPopulationString.split(";");

		List<BaseActor> population = new ArrayList<BaseActor>();
		for (String populationType : populationTypes) {
			String populationBehaviourName = populationType.split(" ")[0];
			Integer populationCount = Integer.valueOf(populationType.split(" ")[1]);
			for (int i = 0; i < populationCount; i++) {
				BaseActor actor = new BaseActor(BehaviourFactory.create(populationBehaviourName));
				population.add(actor);
			}
		}

		return population;
	}

	/**
	 * Update the historical data map using the population of a round.
	 * 
	 * @param map        The map to update.
	 * @param population The population to count.
	 */
	public static void updateMapFromPopulation(Map<String, List<Integer>> map, List<BaseActor> population) {
		// Count and group all the population members by their type
		Map<String, Long> populationCount = population.stream()
				.collect(Collectors.groupingBy(actor -> actor.getBehaviourName(), Collectors.counting()));

		/**
		 * Add new count to its type
		 */
		for (Entry<String, Long> entry : populationCount.entrySet()) {
			if (map.containsKey(entry.getKey())) {
				map.get(entry.getKey()).add(entry.getValue().intValue());
			} else {
				map.put(entry.getKey(), new ArrayList<Integer>(entry.getValue().intValue()));
			}
		}
	}

}
