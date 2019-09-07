package items.behaviours.implementations;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import items.actions.ActionUtils.Actions;

public class VengefulBehaviourTest {

	@Test
	public void testVengativeBehaviour() throws Exception {
		VengefulBehaviour vb = new VengefulBehaviour();
		assertNotNull("VengativeBehaviour is null", vb);
	}

	@Test
	public void testGetAction() throws Exception {
		VengefulBehaviour vb = new VengefulBehaviour();
		assertNotNull("VengativeBehaviour.getAction() is null", vb.getAction());
		assertSame("VengativeBehaviour.getAction() unexpected value", vb.getAction(), Actions.SHARE);
	}

	@Test
	public void testPrepareNextRound() throws Exception {
		VengefulBehaviour vb = new VengefulBehaviour();
		assertSame("VengativeBehaviour.getAction() unexpected value", vb.getAction(), Actions.SHARE);

		vb.prepareNextRound(Actions.GIVE);
		assertSame("VengativeBehaviour.getAction() unexpected value", vb.getAction(), Actions.SHARE);
		// After facing a TAKE action, vengative will start using TAKE every turn 
		vb.prepareNextRound(Actions.TAKE);
		assertSame("VengativeBehaviour.getAction() unexpected value", vb.getAction(), Actions.TAKE);

		vb.prepareNextRound(Actions.SHARE);
		assertSame("VengativeBehaviour.getAction() unexpected value", vb.getAction(), Actions.TAKE);
	}

}
