package items.actors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import items.actions.ActionUtils.Actions;
import items.behaviours.utils.BehaviourFactory;

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
		assertSame("BaseActor.getPerformedAction() unexpected value", ba.getPerformedAction(), Actions.GIVE);
		assertEquals("BaseActor.getEnergy() unexpected value", ba.getEnergy(), 0.0d, 0.0d);
	}

	@Test
	public void testBaseActorBaseBehaviour() throws Exception {
		BaseActor ba = new BaseActor(BehaviourFactory.create("IdleBehaviour"));
		assertNotNull("BaseActor is null", ba);
		assertSame("BaseActor.getPerformedAction() unexpected value", ba.getPerformedAction(), Actions.GIVE);
		assertEquals("BaseActor.getEnergy() unexpected value", ba.getEnergy(), 0.0d, 0.0d);
	}

	@Test
	public void testBaseActorBaseBehaviourDouble() throws Exception {
		BaseActor ba = new BaseActor(BehaviourFactory.create("IdleBehaviour"), 1.0d);
		assertNotNull("BaseActor is null", ba);
		assertSame("BaseActor.getPerformedAction() unexpected value", ba.getPerformedAction(), Actions.GIVE);
		assertEquals("BaseActor.getEnergy() unexpected value", ba.getEnergy(), 1.0d, 0.0d);
	}

	@Test
	public void testBaseActorBaseActor() throws Exception {
		BaseActor ba = new BaseActor();
		BaseActor ba2 = new BaseActor(ba);
		assertNotNull("BaseActor copy is null", ba2);
		assertSame("BaseActor.getPerformedAction() unexpected value", ba2.getPerformedAction(), Actions.GIVE);
		assertEquals("BaseActor.getEnergy() unexpected value", ba2.getEnergy(), 0.0d, 0.0d);

		BaseActor ba3 = new BaseActor(BehaviourFactory.create("TakeBehaviour"), BaseActor.SURVIVAL_ENERGY);
		BaseActor ba4 = new BaseActor(ba3);
		assertNotNull("BaseActor copy is null", ba4);
		assertSame("BaseActor.getPerformedAction() unexpected value", ba4.getPerformedAction(), Actions.TAKE);
		assertEquals("BaseActor.getEnergy() unexpected value", ba4.getEnergy(), 0.0d, 0.0d);
	}

	@Test
	public void testGetEnergy() {
		BaseActor ba = new BaseActor(BehaviourFactory.create("IdleBehaviour"), BaseActor.SURVIVAL_ENERGY);
		Double baEnergy = ba.getEnergy();
		assertNotNull("BaseActor.actorEnergy is null", baEnergy);
		assertEquals("BaseActor.actorEnergy does not match", baEnergy, BaseActor.SURVIVAL_ENERGY, 0.0d);
	}

	@Test
	public void testCanSurvive() {
		BaseActor ba = new BaseActor(BehaviourFactory.create("IdleBehaviour"), BaseActor.SURVIVAL_ENERGY);
		assertTrue("BaseActor.canSurvive() unexpected value for energy = 0.5d", ba.canSurvive());
		BaseActor ba2 = new BaseActor(BehaviourFactory.create("IdleBehaviour"), BaseActor.SURVIVAL_ENERGY - 0.1d);
		assertFalse("BaseActor.canSurvive() unexpected value for energy = 0.0d", ba2.canSurvive());
	}

	@Test
	public void testCanReproduce() {
		BaseActor ba = new BaseActor(BehaviourFactory.create("IdleBehaviour"), BaseActor.REPRODUCTIVE_ENERGY);
		assertTrue("BaseActor.canReproduce() unexpected value", ba.canReproduce());
		BaseActor ba2 = new BaseActor(BehaviourFactory.create("IdleBehaviour"), BaseActor.REPRODUCTIVE_ENERGY - 0.1d);
		assertFalse("BaseActor.canReproduce() unexpected value", ba2.canReproduce());
	}

	@Test
	public void testIncreaseEnergy() throws Exception {
		BaseActor ba = new BaseActor(BehaviourFactory.create("IdleBehaviour"), 1.0d);
		Double baEnergy = ba.getEnergy();
		ba.increaseEnergy(0.1d);
		assertEquals("BaseActor.actorEnergy unexpected value after increaseEnergy()", baEnergy + 0.1d, ba.getEnergy(),
				0.0d);
	}

	@Test
	public void testReduceEnergy() {
		BaseActor ba = new BaseActor(BehaviourFactory.create("IdleBehaviour"), 1.0d);
		Double baEnergy = ba.getEnergy();
		ba.reduceEnergy(0.1d);
		assertEquals("BaseActor.actorEnergy unexpected value after reduceEnergy()", baEnergy - 0.1d, ba.getEnergy(),
				0.0d);
	}

	@Test
	public void testToString() {
		BaseActor ba = new BaseActor(BehaviourFactory.create("IdleBehaviour"), 1.0d);
		String baToString = ba.toString();
		assertNotNull("BaseActor.toString() returns null", baToString);
		assertEquals("BaseActor.toString() does not match", baToString, "IdleBehaviour: 1.00");
	}

	@Test
	public void testSurvivalIteration() {
		BaseActor ba = new BaseActor(BehaviourFactory.create("IdleBehaviour"), 1.0d);
		assertTrue("BaseActor.canSurvive() unexpected value for energy = 1.0d", ba.canSurvive());
		ba.reduceEnergy(1.0d);
		assertFalse("BaseActor.canSurvive() unexpected value after reduceEnergy()", ba.canSurvive());
	}

	@Test
	public void testReproductiveIteration() {
		BaseActor ba = new BaseActor(BehaviourFactory.create("IdleBehaviour"), BaseActor.REPRODUCTIVE_ENERGY);
		assertTrue("BaseActor.canSurvive() unexpected value for energy", ba.canSurvive());
		assertTrue("BaseActor.canReproduce() unexpected value for energy", ba.canReproduce());
		ba.reduceEnergy(BaseActor.REPRODUCTIVE_ENERGY / 2.0d);
		assertTrue("BaseActor.canSurvive() unexpected value after reduceEnergy()", ba.canSurvive());
		assertFalse("BaseActor.canReproduce() unexpected value after reduceEnergy()", ba.canReproduce());
	}

	@Test
	public void testGetPerformedAction() throws Exception {
		BaseActor ba = new BaseActor(BehaviourFactory.create("IdleBehaviour"), 1.0d);
		assertEquals("BaseActor.getDesiredPortion() unexpected value for null behaviour", ba.getPerformedAction(),
				Actions.GIVE);
	}

}
