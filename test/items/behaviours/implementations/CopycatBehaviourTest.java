package items.behaviours.implementations;

import org.junit.Test;

import items.actions.ActionUtils.Actions;
import junit.framework.TestCase;

public class CopycatBehaviourTest extends TestCase {

	@Test
	public void testCopycatBehaviour() throws Exception {
		CopycatBehaviour ccb = new CopycatBehaviour();
		assertNotNull("CopycatBehaviour is null", ccb);
	}

	@Test
	public void testGetAction() throws Exception {
		CopycatBehaviour ccb = new CopycatBehaviour();
		assertNotNull("CopycatBehaviour.getAction() is null", ccb.getAction());
		assertSame("CopycatBehaviour.getAction() unexpected value", ccb.getAction(), Actions.SHARE);
	}

	@Test
	public void testPrepareNextRound() throws Exception {
		CopycatBehaviour ccb = new CopycatBehaviour();
		assertSame("CopycatBehaviour.getAction() unexpected value", ccb.getAction(), Actions.SHARE);

		ccb.prepareNextRound(Actions.GIVE);
		assertSame("CopycatBehaviour.getAction() unexpected value", ccb.getAction(), Actions.GIVE);

		ccb.prepareNextRound(Actions.TAKE);
		assertSame("CopycatBehaviour.getAction() unexpected value", ccb.getAction(), Actions.TAKE);

		ccb.prepareNextRound(Actions.SHARE);
		assertSame("CopycatBehaviour.getAction() unexpected value", ccb.getAction(), Actions.SHARE);
	}

}
