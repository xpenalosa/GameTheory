package items.behaviours.utils;

import com.sun.istack.internal.Nullable;

import items.behaviours.BaseBehaviour;
import items.behaviours.implementations.CopycatBehaviour;
import items.behaviours.implementations.IdleBehaviour;
import items.behaviours.implementations.RandomBehaviour;
import items.behaviours.implementations.ShareBehaviour;
import items.behaviours.implementations.TakeBehaviour;
import items.behaviours.implementations.VengefulBehaviour;

public abstract class BehaviourFactory {

	public static BaseBehaviour create(@Nullable String behaviourName) {
		switch(behaviourName) {
		case "BaseBehaviour":
			return new BaseBehaviour();
		case "IdleBehaviour":
			return new IdleBehaviour();
		case "ShareBehaviour":
			return new ShareBehaviour();
		case "TakeBehaviour":
			return new TakeBehaviour();
		case "CopycatBehaviour":
			return new CopycatBehaviour();
		case "RandomBehaviour":
			return new RandomBehaviour();
		case "VengefulBehaviour":
			return new VengefulBehaviour();
		default:
			return new IdleBehaviour();
		}
	}
}
