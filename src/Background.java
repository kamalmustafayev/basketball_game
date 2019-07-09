import acm.graphics.GCompound;

/**
 * 
 * Background class holds all backgrounds of the game
 * 
 * @author Kamal Mustafayev
 *
 */
public class Background extends GCompound implements Constants {

	/**
	 * intro background
	 * 
	 * @param width
	 *            of the screen
	 * @param height
	 *            of the screen
	 */
	public Background(double width, double height) {
		intro.setSize(width, height);
		add(intro);
	}

	/**
	 * background that is during the game
	 * 
	 * @param score
	 *            that player has
	 * @param ballsLeft
	 *            to fall
	 * @param width
	 *            of the screen
	 * @param height
	 *            of the screen
	 */
	public Background(int score, int ballsLeft, double width, double height) {
		background.setSize(width, height);
		add(background);
		Score scores = new Score(score, ballsLeft);
		add(scores);

		speeding();
	}

	/**
	 * ending background
	 * 
	 * @param score
	 *            final score of the player
	 * @param width
	 *            of the screen
	 * @param height
	 *            of the screen
	 */
	public Background(int score, double width, double height) {
		ending.setSize(width, height);
		add(ending);
		Score finalScore = new Score(score, width, height);
		add(finalScore);
		Score.zeroing();
	}

	/**
	 * high-score table
	 * 
	 * @param height
	 *            of the screen
	 */
	public Background(double height) {
		Table table = new Table(height);
		add(table);
	}

	/**
	 * speeding the game balls are falling faster
	 */
	private void speeding() {
		Basketball_Game.randMin -= 3;
		Basketball_Game.randMax -= 3;
	}
}
