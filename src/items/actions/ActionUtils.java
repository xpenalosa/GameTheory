package items.actions;

import java.sql.Time;
import java.time.Instant;
import java.util.Random;

import javafx.util.Pair;

public abstract class ActionUtils { 
	
	public static enum Actions {
		GIVE, SHARE, TAKE;
	}
	
	public static Actions getRandomAction() {
		Random rand = new Random(Time.from(Instant.now()).getTime());
		return Actions.values()[rand.nextInt(3)];
	}
	
	public static Pair<Double,Double> getEnergyFromActions(Actions a1, Actions a2){
		if (a1.equals(a2)) {
			switch(a1) {
			case SHARE:
				// Share, Share
				return new Pair<Double, Double>(0.5d, 0.5d);
			default:
				// Take, Take
				// Give, Give
				return new Pair<Double, Double>(0.0d, 0.0d);
			}
		} else if (a1 == Actions.TAKE) {
			// Take, Share
			// Take, Give
			return new Pair<Double,Double>(1.0d, 0.0d);
		} else if (a2 == Actions.TAKE) {
			// Share, Take
			// Give, Take
			return new Pair<Double,Double>(0.0d, 1.0d);
		} else if (a1 == Actions.SHARE) {
			// Share, Give
			return new Pair<Double,Double>(0.5d, 0.0d);
		} else {
			// Give, Share
			return new Pair<Double,Double>(0.0d, 0.5d);
		}
	}

}
