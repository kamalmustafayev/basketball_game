
/**
 * Actions class that runs main actions of the game
 * 
 * @author Kamal Mustafayev
 */
public class Actions implements Constants {

	/**
	 * slowing down everything for slomoTime
	 */
	static void slowing() {
		GBall.PauseTime = 40;
		GBomb.PauseTime = 40;
		Basketball_Game.randMin = 1100;
		Basketball_Game.randMax = 1300;
		Basketball_Game.isSlomo = false;
	}

	/**
	 * brings normal speed to the game
	 */
	static void normalizeSpeed() {
		GBall.PauseTime = 12;
		GBomb.PauseTime = 12;
		Basketball_Game.randMin = 700;
		Basketball_Game.randMax = 900;
	}

	/**
	 * 
	 * @param i
	 *            taken from for loop that interacts with random number
	 * @return is bomb going to fall
	 */
	static boolean isBomb(int i) {
		if (i % Basketball_Game.numOfBombs == 0 && Basketball_Game.ballsLeft != NUM_OF_BALLS)
			return true;
		return false;
	}

	/**
	 * randomize dropping location
	 * 
	 * @param width
	 *            of the screen
	 */
	static void randomize(int width) {
		Basketball_Game.dropLocationX = rgen.nextInt(width - SIZE_OF_THE_OBJECT);
		Basketball_Game.randomWow = rgen.nextInt(1, 6);
		Basketball_Game.randomTime = rgen.nextInt(Basketball_Game.randMin, Basketball_Game.randMax);
	}
}
