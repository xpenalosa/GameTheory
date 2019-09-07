package items.prizes;

import org.junit.Test;

import items.actors.BaseActor;
import items.behaviours.BaseBehaviour;
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
	public void testAddActor() throws Exception {
		BasePrize bp = new BasePrize();
		BaseActor ba = new BaseActor();
		assertTrue("BasePrize.addActor() unexpected value with no actors", bp.addActor(ba));
		BaseActor ba2 = new BaseActor();
		assertTrue("BasePrize.addActor() unexpected value with 1 actor", bp.addActor(ba2));
		BaseActor ba3 = new BaseActor();
		assertFalse("BasePrize.addActor() unexpected value with 2 actors", bp.addActor(ba3));
	}

	@Test
	public void testUpdateActors() throws Exception {
		BasePrize bp = new BasePrize();		
		// No actors, no modifications
		bp.updateActors();
		
		BaseActor ba = new BaseActor("Name", 1, 0.0d);
		ba.setBehaviour(new BaseBehaviour());
		bp.addActor(ba);
		
		bp.updateActors();
		// One actor, BaseBehaviour takes the prize and adds 1 energy.
		assertEquals("BasePrize.updateActors() unexpected energy value with 1 actor", ba.getEnergy(), 1.0d, 0.0d);
		
		BaseActor ba2 = new BaseActor("NameOther", 1, 0.0d);
		ba2.setBehaviour(new BaseBehaviour());
		bp.addActor(ba2);
		
		bp.updateActors();
		// Two actors, BaseBehaviour fights for the whole prize and no energy is obtained.
		assertEquals("BasePrize.updateActors() unexpected energy value with 2 actors (1)", ba.getEnergy(), 1.0d, 0.0d);
		assertEquals("BasePrize.updateActors() unexpected energy value with 2 actors (2)", ba2.getEnergy(), 0.0d, 0.0d);
		
		ba2.setBehaviour(null);
		bp.updateActors();
		// Two actors, BaseBehaviour fights for the whole prize and null behaviour does nothing. Actor 1 gets all the energy.
		assertEquals("BasePrize.updateActors() unexpected energy value with 2 actors (1)", ba.getEnergy(), 2.0d, 0.0d);
		assertEquals("BasePrize.updateActors() unexpected energy value with 2 actors (2)", ba2.getEnergy(), 0.0d, 0.0d);
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