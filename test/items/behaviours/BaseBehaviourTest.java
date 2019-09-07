package items.behaviours;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BaseBehaviourTest {

	@Test
	public void testBaseBehaviour() throws Exception {
		BaseBehaviour bb = new BaseBehaviour();
		assertNotNull("BaseBehaviour is null", bb);
	}

	@Test
	public void testGetDesiredPortion() throws Exception {
		BaseBehaviour bb = new BaseBehaviour();
		assertNotNull("BaseBehaviour is null", bb.getDesiredPortion(1));
		assertEquals("BaseBehaviour.getDesiredPortion() is null", bb.getDesiredPortion(1), 1.0d, 0.0d);
	}

	@Test
		public void testPrepareNextRound() throws Exception {
			BaseBehaviour bb = new BaseBehaviour();
			bb.prepareNextRound();
			assertTrue(true);
		}

}
