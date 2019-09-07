package items.behaviours.implementations;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import items.actions.ActionUtils.Actions;

public class IdleBehaviourTest {

	@Test
	public void testIdleBehaviour() throws Exception {
		IdleBehaviour ib = new IdleBehaviour();
		assertNotNull("IdleBehaviour is null", ib);
	}

	@Test
	public void testGetAction() throws Exception {
		IdleBehaviour ib = new IdleBehaviour();
		assertNotNull("IdleBehaviour.getAction() is null", ib.getAction());
		assertSame("IdleBehaviour.getAction() unexpected value", ib.getAction(), Actions.GIVE);
	}

	@Test
	public void testPrepareNextRound() throws Exception {
		IdleBehaviour ib = new IdleBehaviour();
		assertSame("IdleBehaviour.getAction() unexpected value", ib.getAction(), Actions.GIVE);

		ib.prepareNextRound(Actions.GIVE);
		assertSame("IdleBehaviour.getAction() unexpected value", ib.getAction(), Actions.GIVE);

		ib.prepareNextRound(Actions.TAKE);
		assertSame("IdleBehaviour.getAction() unexpected value", ib.getAction(), Actions.GIVE);

		ib.prepareNextRound(Actions.SHARE);
		assertSame("IdleBehaviour.getAction() unexpected value", ib.getAction(), Actions.GIVE);
	}

}
