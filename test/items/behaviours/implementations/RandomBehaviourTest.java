package items.behaviours.implementations;

import org.junit.Test;

import items.actions.ActionUtils.Actions;
import junit.framework.TestCase;

public class RandomBehaviourTest extends TestCase {

	@Test
	public void testIdleBehaviour() throws Exception {
		RandomBehaviour rb = new RandomBehaviour();
		assertNotNull("RandomBehaviour is null", rb);
	}

	@Test
	public void testGetAction() throws Exception {
		RandomBehaviour rb = new RandomBehaviour();
		assertNotNull("RandomBehaviour.getAction() is null", rb.getAction());
		// Impossible to test for a specific action as it is selected randomly
	}

	@Test
	public void testPrepareNextRound() throws Exception {
		RandomBehaviour rb = new RandomBehaviour();
		assertNotNull("RandomBehaviour.getAction() is null", rb.getAction());
		rb.prepareNextRound(Actions.GIVE);
		assertNotNull("RandomBehaviour.getAction() is null after prepareNextRound()", rb.getAction());
	}
}
