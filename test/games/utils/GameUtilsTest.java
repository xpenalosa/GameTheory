package games.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import items.actors.BaseActor;
import items.behaviours.implementations.IdleBehaviour;

public class GameUtilsTest {

	@Test
	public void testDecodePopulationString() {
		String populationType = IdleBehaviour.class.getSimpleName();
		int populationCount = 40;
		String populationString = String.format("%s %d", populationType, populationCount);

		List<BaseActor> actorList = GameUtils.decodePopulationString(populationString);
		assertNotNull("actorList is null", actorList);
		assertEquals("actorList size does not match", actorList.size(), populationCount);

		for (int i = 0; i < actorList.size(); i++) {
			BaseActor actor = actorList.get(i);
			assertEquals(String.format("Actor %d type mismatch", i), actor.getBehaviourName(), populationType);
		}

		String populationType2 = IdleBehaviour.class.getSimpleName();
		int populationCount2 = 20;
		String populationString2 = String.format("%s %d;%s %d", populationType, populationCount, populationType2,
				populationCount2);

		List<BaseActor> actorList2 = GameUtils.decodePopulationString(populationString2);
		assertNotNull("actorList2 is null", actorList2);
		assertEquals("actorList size does not match", actorList2.size(), populationCount + populationCount2);

		for (int i = 0; i < actorList.size(); i++) {
			BaseActor actor = actorList.get(i);
			assertEquals(String.format("Actor2 %d type mismatch", i), actor.getBehaviourName(), populationType);
		}
		for (int i = actorList.size(); i < actorList2.size(); i++) {
			BaseActor actor = actorList2.get(i);
			assertEquals(String.format("Actor2 %d type mismatch", i), actor.getBehaviourName(), populationType2);
		}

	}

	@Test
	public void testUpdateMapFromPopulation() {
		String populationType = IdleBehaviour.class.getSimpleName();
		int populationCount = 40;
		String populationString = String.format("%s %d", populationType, populationCount);
		List<BaseActor> population = GameUtils.decodePopulationString(populationString);

		Map<String, List<Integer>> populationMap = new HashMap<String, List<Integer>>();
		GameUtils.updateMapFromPopulation(populationMap, population);
		assertTrue("Map does not contain key", populationMap.containsKey(populationType));
		assertEquals("Map population list is empty", populationMap.get(populationType).size(), 1);
		assertEquals("Map population list unexpected value", populationMap.get(populationType).get(0).intValue(),
				population.size());
		
		population.remove(0);
		GameUtils.updateMapFromPopulation(populationMap, population);
		assertEquals("Map population list is empty", populationMap.get(populationType).size(), 2);
		assertEquals("Map population list unexpected value", populationMap.get(populationType).get(1).intValue(),
				population.size());
	}

}
