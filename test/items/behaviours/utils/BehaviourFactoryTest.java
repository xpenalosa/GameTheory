package items.behaviours.utils;

import org.junit.Test;

import items.behaviours.BaseBehaviour;
import items.behaviours.implementations.CopycatBehaviour;
import items.behaviours.implementations.IdleBehaviour;
import items.behaviours.implementations.RandomBehaviour;
import items.behaviours.implementations.ShareBehaviour;
import items.behaviours.implementations.TakeBehaviour;
import items.behaviours.implementations.VengefulBehaviour;
import junit.framework.TestCase;

public class BehaviourFactoryTest extends TestCase {

	@Test
	public void testCreate() throws Exception {

		assertTrue("Default case mismatch", BehaviourFactory.create("TestCase") instanceof IdleBehaviour);
		
		assertTrue("BaseBehaviour mismatch", BehaviourFactory.create("BaseBehaviour") instanceof BaseBehaviour);
		assertTrue("IdleBehaviour mismatch", BehaviourFactory.create("IdleBehaviour") instanceof IdleBehaviour);
		assertTrue("ShareBehaviour mismatch", BehaviourFactory.create("ShareBehaviour") instanceof ShareBehaviour);
		assertTrue("TakeBehaviour mismatch", BehaviourFactory.create("TakeBehaviour") instanceof TakeBehaviour);
		assertTrue("CopycatBehaviour mismatch", BehaviourFactory.create("CopycatBehaviour") instanceof CopycatBehaviour);
		assertTrue("RandomBehaviour mismatch", BehaviourFactory.create("RandomBehaviour") instanceof RandomBehaviour);
		assertTrue("VengefulBehaviour mismatch", BehaviourFactory.create("VengefulBehaviour") instanceof VengefulBehaviour);
	}

}
