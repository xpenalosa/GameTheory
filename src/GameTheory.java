import java.util.logging.Level;

import com.sun.istack.internal.logging.Logger;

public class GameTheory {

	protected static Logger logger = Logger.getLogger(GameTheory.class);
	
	public static void main(String[] args) {
		if (args.length != 3) {
			logger.log(Level.WARNING, "Take a look at the README file to see the usage.");
		}
		// TODO
	}
}
