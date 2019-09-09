package items.actions;

import java.sql.Time;
import java.time.Instant;
import java.util.Random;

public abstract class ActionUtils { 
	
	public static enum Actions {
		GIVE, SHARE, TAKE;
		
		public static double[][] energyRelations = {
			{0.0, 0.0, 0.0}, // Give
			{0.5, 0.5, 0.0}, // Share
			{1.0, 1.0, 0.0}  // Take
		};
	}

	public static Actions getRandomAction() {
		Random rand = new Random(Time.from(Instant.now()).getTime());
		return Actions.values()[rand.nextInt(3)];
	}

}
