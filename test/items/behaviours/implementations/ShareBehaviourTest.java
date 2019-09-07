package items.behaviours.implementations;

import static org.junit.Assert.*;

import org.junit.Test;

import items.actions.ActionUtils.Actions;

public class ShareBehaviourTest {

	@Test
	public void testShareBehaviour() throws Exception {
		ShareBehaviour sb = new ShareBehaviour();
		assertNotNull("ShareBehaviour is null", sb);
	}

	@Test
	public void testGetAction() throws Exception {
		ShareBehaviour sb = new ShareBehaviour();
		assertNotNull("ShareBehaviour.getAction() is null", sb.getAction());
		assertSame("ShareBehaviour.getAction() unexpected value", sb.getAction(), Actions.SHARE);
	}

	@Test
	public void testPrepareNextRound() throws Exception {
		ShareBehaviour sb = new ShareBehaviour();
		assertSame("ShareBehaviour.getAction() unexpected value", sb.getAction(), Actions.SHARE);

		sb.prepareNextRound(Actions.GIVE);
		assertSame("ShareBehaviour.getAction() unexpected value", sb.getAction(), Actions.SHARE);

		sb.prepareNextRound(Actions.TAKE);
		assertSame("ShareBehaviour.getAction() unexpected value", sb.getAction(), Actions.SHARE);

		sb.prepareNextRound(Actions.SHARE);
		assertSame("ShareBehaviour.getAction() unexpected value", sb.getAction(), Actions.SHARE);
	}

}
