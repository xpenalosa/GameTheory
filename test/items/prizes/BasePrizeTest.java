package items.prizes;

import org.junit.Test;

import items.actors.BaseActor;
import items.behaviours.utils.BehaviourFactory;
import junit.framework.TestCase;

public class BasePrizeTest extends TestCase {

	@Test
	public void testBasePrize() throws Exception {
		BasePrize bp = new BasePrize();
		assertNotNull("BaseActor is null", bp);
	}

	@Test
	public void testCanAddActor() throws Exception {
		BasePrize bp = new BasePrize();
		assertTrue("BasePrize.canAddActor() unexpected value with no actors", bp.canAddActor());
		BaseActor ba = new BaseActor();
		bp.addActor(ba);
		assertTrue("BasePrize.canAddActor() unexpected value with one actor", bp.canAddActor());
		BaseActor ba2 = new BaseActor();
		bp.addActor(ba2);
		assertFalse("BasePrize.canAddActor() unexpected value with two actors", bp.canAddActor());
	}

	@Test
	public void testUpdateActors() throws Exception {
		BasePrize bp = new BasePrize();		
		// No actors, no modifications
		bp.updateActors();

		BaseActor ba = new BaseActor(BehaviourFactory.create("ShareBehaviour"));
		bp.addActor(ba);
		bp.updateActors();
		// One actor, BaseBehaviour takes the prize and adds 1 energy.
		assertEquals("BasePrize.updateActors() unexpected energy value with 1 actor", ba.getEnergy(), 1.0d, 0.0d);
		ba.reduceEnergy(ba.getEnergy());
		
		BaseActor ba2 = new BaseActor(BehaviourFactory.create("ShareBehaviour"));
		bp.addActor(ba2);
		bp.updateActors();
		// Two actors, ShareBehaviour splits the prize and each actor obtains 0.5 energy.
		assertEquals("BasePrize.updateActors() unexpected energy value with 2 actors (1)", ba.getEnergy(), 0.5d, 0.0d);
		assertEquals("BasePrize.updateActors() unexpected energy value with 2 actors (2)", ba2.getEnergy(), 0.5d, 0.0d);
		ba.reduceEnergy(ba.getEnergy());
		
		BaseActor ba3 = new BaseActor(BehaviourFactory.create("TakeBehaviour"));
		bp.clear();
		bp.addActor(ba);
		bp.addActor(ba3);
		bp.updateActors();
		// Two actors, TakeBehaviour fights for the whole prize and ShareBehaviour accepts. Actor 1 gets all the energy.
		assertEquals("BasePrize.updateActors() unexpected energy value with 2 actors (1)", ba.getEnergy(), 0.0d, 0.0d);
		assertEquals("BasePrize.updateActors() unexpected energy value with 2 actors (2)", ba3.getEnergy(), 1.0d, 0.0d);
	}

	@Test
	public void testClear() throws Exception {
		BasePrize bp = new BasePrize();
		
		BaseActor ba = new BaseActor();
		bp.addActor(ba);
		BaseActor ba2 = new BaseActor();
		bp.addActor(ba2);

		assertFalse("BasePrize.canAddActor() unexpected value with two actor", bp.canAddActor());
		bp.clear();
		assertTrue("BasePrize.canAddActor() unexpected value with no actors", bp.canAddActor());
	}

}
