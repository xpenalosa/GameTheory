package items.actors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import items.behaviours.BaseBehaviour;

/**
 * Test class for the BaseActor class.
 * 
 * @author Xavier Peñalosa
 */
public class BaseActorTest {

	@Test
	public void testBaseActor() {
		BaseActor ba = new BaseActor();
		assertNotNull("BaseActor is null", ba);
	}

	@Test
	public void testBaseActorStringInteger() {
		BaseActor ba = new BaseActor("Name", 1);
		assertNotNull("BaseActor is null", ba);
	}

	@Test
	public void testBaseActorStringIntegerDouble() throws Exception {
		BaseActor ba = new BaseActor("Name", 1, 1.0d);
		assertNotNull("BaseActor is null", ba);
	}

	@Test
	public void testSetBehaviour() throws Exception {
		BaseActor ba = new BaseActor("Name", 1, 1.0d);
		ba.setBehaviour(new BaseBehaviour());
		assertNotNull("BaseActor.actorBehaviour is null", ba.actorBehaviour);

	}

	@Test
	public void testGetName() {
		BaseActor ba = new BaseActor("Name", 1, 1.0d);
		String baName = ba.getName();
		assertNotNull("BaseActor.actorName is null", baName);
		assertEquals("BaseActor.actorName does not match", baName, "Name");
	}

	@Test
	public void testGetId() {
		BaseActor ba = new BaseActor("Name", 1, 1.0d);
		Integer baId = ba.getId();
		assertNotNull("BaseActor.actorId is null", baId);
		assertSame("BaseActor.actorId does not match", baId, Integer.valueOf(1));
	}

	@Test
	public void testGetEnergy() {
		BaseActor ba = new BaseActor("Name", 1, 1.0d);
		Double baEnergy = ba.getEnergy();
		assertNotNull("BaseActor.actorEnergy is null", baEnergy);
		assertEquals("BaseActor.actorEnergy does not match", baEnergy, 1.0d, 0.0d);
	}

	@Test
	public void testCanSurvive() {
		BaseActor ba = new BaseActor("Name", 1, 1.0d);
		assertTrue("BaseActor.canSurvive() unexpected value for energy = 1.0d", ba.canSurvive());
		BaseActor ba2 = new BaseActor("Name", 1, 0.9d);
		assertFalse("BaseActor.canSurvive() unexpected value for energy = 0.9d", ba2.canSurvive());
	}

	@Test
	public void testCanReproduce() {
		BaseActor ba = new BaseActor("Name", 1, 1.0d);
		assertFalse("BaseActor.canReproduce() unexpected value for energy = 1.0d", ba.canReproduce());
		BaseActor ba2 = new BaseActor("Name", 1, 2.0d);
		assertTrue("BaseActor.canReproduce() unexpected value for energy = 2.0d", ba2.canReproduce());
	}

	@Test
	public void testIncreaseEnergy() throws Exception {
		BaseActor ba = new BaseActor("Name", 1, 1.0d);
		Double baEnergy = ba.getEnergy();
		ba.increaseEnergy(0.1d);
		assertEquals("BaseActor.actorEnergy unexpected value after increaseEnergy()", baEnergy + 0.1d, ba.getEnergy(),
				0.0d);
	}

	@Test
	public void testReduceEnergy() {
		BaseActor ba = new BaseActor("Name", 1, 1.0d);
		Double baEnergy = ba.getEnergy();
		ba.reduceEnergy(0.1d);
		assertEquals("BaseActor.actorEnergy unexpected value after reduceEnergy()", baEnergy - 0.1d, ba.getEnergy(),
				0.0d);
	}

	@Test
	public void testToString() {
		BaseActor ba = new BaseActor("Name", 1, 1.0d);
		String baToString = ba.toString();
		assertNotNull("BaseActor.toString() returns null", baToString);
		assertEquals("BaseActor.toString() does not match", baToString, "(1) Name: 1.00");
	}

	@Test
	public void testSurvivalIteration() {
		BaseActor ba = new BaseActor("Name", 1, 1.0d);
		assertTrue("BaseActor.canSurvive() unexpected value for energy = 1.0d", ba.canSurvive());
		ba.reduceEnergy(1.0d);
		assertFalse("BaseActor.canSurvive() unexpected value after reduceEnergy()", ba.canSurvive());
	}

	@Test
	public void testReproductiveIteration() {
		BaseActor ba = new BaseActor("Name", 1, 2.0d);
		assertTrue("BaseActor.canSurvive() unexpected value for energy = 2.0d", ba.canSurvive());
		assertTrue("BaseActor.canReproduce() unexpected value for energy = 2.0d", ba.canReproduce());
		ba.reduceEnergy(1.0d);
		assertTrue("BaseActor.canSurvive() unexpected value after reduceEnergy()", ba.canSurvive());
		assertFalse("BaseActor.canReproduce() unexpected value after reduceEnergy()", ba.canReproduce());
	}

	@Test
	public void testGetDesiredPortion() throws Exception {
		BaseActor ba = new BaseActor("Name", 1, 1.0d);
		assertEquals("BaseActor.getDesiredPortion() unexpected value for null behaviour", ba.getDesiredPortion(null), 0.0d, 0.0d);
		ba.setBehaviour(new BaseBehaviour());
		
		double baseBehaviourDefaultVal = new BaseBehaviour().getDesiredPortion(0);
		assertEquals("BaseActor.getDesiredPortion() unexpected value for other = null", ba.getDesiredPortion(null), baseBehaviourDefaultVal, 0.0d);

		BaseActor baOther = new BaseActor("NameOther", 1, 1.0d);
		baOther.setBehaviour(new BaseBehaviour());
		assertEquals("BaseActor.getDesiredPortion() unexpected value after fight", ba.getDesiredPortion(baOther), baseBehaviourDefaultVal, 0.0d);
		
		
	}

}
