package items.behaviours;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import items.actions.ActionUtils.Actions;

public class BaseBehaviourTest {

	@Test
	public void testBaseBehaviour() throws Exception {
		BaseBehaviour bb = new BaseBehaviour();
		assertNotNull("BaseBehaviour is null", bb);
	}

	@Test
		public void testGetAction() throws Exception {
			BaseBehaviour bb = new BaseBehaviour();
			assertNotNull("BaseBehaviour.getAction() is null", bb.getAction());
			assertEquals("BaseBehaviour.getAction() unexpected value", bb.getAction(), Actions.GIVE);
		}

	@Test
	public void testPrepareNextRound() throws Exception {
		BaseBehaviour bb = new BaseBehaviour();
		assertSame("BaseBehaviour.getAction() unexpected value", bb.getAction(), Actions.GIVE);

		bb.prepareNextRound(Actions.GIVE);
		assertSame("BaseBehaviour.getAction() unexpected value", bb.getAction(), Actions.GIVE);

		bb.prepareNextRound(Actions.TAKE);
		assertSame("BaseBehaviour.getAction() unexpected value", bb.getAction(), Actions.GIVE);

		bb.prepareNextRound(Actions.SHARE);
		assertSame("BaseBehaviour.getAction() unexpected value", bb.getAction(), Actions.GIVE);
	}

}
