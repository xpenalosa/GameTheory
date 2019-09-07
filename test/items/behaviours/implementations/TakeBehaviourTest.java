package items.behaviours.implementations;

import org.junit.Test;

import items.actions.ActionUtils.Actions;
import junit.framework.TestCase;

public class TakeBehaviourTest extends TestCase {

	@Test
	public void testTakeBehaviour() throws Exception {
		TakeBehaviour tb = new TakeBehaviour();
		assertNotNull("TakeBehaviour is null", tb);
	}

	@Test
	public void testGetAction() throws Exception {
		TakeBehaviour tb = new TakeBehaviour();
		assertNotNull("TakeBehaviour.getAction() is null", tb.getAction());
		assertEquals("TakeBehaviour.getAction() unexpected value", tb.getAction(), Actions.TAKE);
	}

	@Test
	public void testPrepareNextRound() throws Exception {
		TakeBehaviour tb = new TakeBehaviour();
		assertSame("TakeBehaviour.getAction() unexpected value", tb.getAction(), Actions.TAKE);

		tb.prepareNextRound(Actions.GIVE);
		assertSame("TakeBehaviour.getAction() unexpected value", tb.getAction(), Actions.TAKE);

		tb.prepareNextRound(Actions.TAKE);
		assertSame("TakeBehaviour.getAction() unexpected value", tb.getAction(), Actions.TAKE);

		tb.prepareNextRound(Actions.SHARE);
		assertSame("TakeBehaviour.getAction() unexpected value", tb.getAction(), Actions.TAKE);
	}
}
