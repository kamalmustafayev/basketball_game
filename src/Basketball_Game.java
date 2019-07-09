import java.awt.event.*;
import acm.program.*;

/**
 * Basketball_Game class runs the game
 * 
 * @author Kamal Mustafayev
 *
 */
public class Basketball_Game extends GraphicsProgram implements Constants {
	private static final long serialVersionUID = 1L;

	/**
	 * adding field to enter the name
	 */
	public void init() {
		addMouseListeners();
		add(label, SOUTH);
		add(nameField, SOUTH);
		nameField.addActionListener(this);
	}

	/**
	 * the game itself
	 */
	public void run() {
		addStartingBackground();
		while (true) {
			add(basket, -WIDTH_OF_THE_BASKET, (getHeight() - basket.getHeight()) / 2);
			add(net, -WIDTH_OF_THE_NET, (getHeight() / 2));

			numOfBombs = rgen.nextInt(7, 12);
			randomSlomo = rgen.nextInt(20, 40);

			for (int i = 0; true; i++) {
				addBackground();
				Actions.randomize(getWidth());
				pause(randomTime);

				if (ballsLeft == 0) {
					while (ball.isVisible())
						pause(300);
					break;
				}
				addingPoints();
				checkFallingObject(i);
				checkForReactions();
				remove(background);
			}
			addEndingBackground();
		}
	}

	/**
	 * adding starting Background in the beginning of the game and Instructions
	 */
	private void addStartingBackground() {
		background = new Background(getWidth(), getHeight());
		add(background);
		pause(3000);
		remove(background);
		instructions = new Instructions(getWidth(), getHeight());
		add(instructions);
		pause(5000);
		while (true) {
			if (playerName != null) {
				playerName = playerName.toUpperCase();
				label.setVisible(false);
				nameField.setVisible(false);
				break;
			}
			pause(1);
		}
		removeAll();
		basket.setSize(WIDTH_OF_THE_BASKET, HEIGHT_OF_THE_BASKET);
		net.setSize(WIDTH_OF_THE_NET, HEIGHT_OF_THE_NET);
	}

	/**
	 * adding Background during the game
	 */
	private void addBackground() {
		background = new Background(score, ballsLeft, getWidth(), getHeight());
		add(background);
		background.sendToBack();
	}

	/**
	 * adding Background at the end and High-score table
	 */
	private void addEndingBackground() {
		removeAll();
		background = new Background(score, getWidth(), getHeight());
		add(background);
		Score.writer();
		pause(4000);
		remove(background);

		highScoreTable = new Background(getHeight());
		add(highScoreTable);
		waitForClick();
		remove(highScoreTable);
	}

	/**
	 * checking which object fall this time
	 * 
	 * @param i
	 *            number connected with random values
	 */
	private void checkFallingObject(int i) {
		if (i % (randomSlomo + SLOMO_DURATION) == 0 || i == 0) {
			Actions.normalizeSpeed();
		}
		if (i % randomSlomo == 0 && i != 0)
			slomo();
		else if (Actions.isBomb(i)) {
			bombing();
		} else
			balling();
	}

	/**
	 * adding Slow Motion Ball
	 */
	private void slomo() {
		slomoBall = new GSlomoBall(SIZE_OF_THE_OBJECT, getWidth(), getHeight());
		thread = new Thread(slomoBall);
		add(slomoBall, dropLocationX, -slomoBall.getHeight());
		thread.start();
	}

	/**
	 * adding the bomb
	 */
	private void bombing() {
		bomb = new GBomb(SIZE_OF_THE_OBJECT, getWidth(), getHeight());
		thread = new Thread(bomb);
		add(bomb, dropLocationX, -bomb.getHeight());
		thread.start();
	}

	/**
	 * adding the ball
	 */
	private void balling() {
		ball = new GBall(SIZE_OF_THE_OBJECT, getWidth(), getHeight());
		thread = new Thread(ball);
		add(ball, dropLocationX, -ball.getHeight());
		thread.start();
		ballsLeft--;
	}

	/**
	 * calling reactions after every score
	 */
	private void checkForReactions() {
		if (isScored) {
			wowing();
		}
		if (isExploded) {
			booming();
		}
		if (isSlomo) {
			slomoing();
		}
	}

	/**
	 * show reactions to the scored ball
	 */
	private void wowing() {
		wow = new Wows(getWidth(), getHeight(), randomWow);
		add(wow);
		pause(500);
		remove(wow);
		score++;
		isGoingOn = true;
		isScored = false;
	}

	/**
	 * slow everything after catching slomoBall
	 */
	private void slomoing() {
		Actions.slowing();
		slow.setSize(750, 325);
		add(slow);
		pause(2000);
		remove(slow);
	}

	/**
	 * explode the basket
	 */
	private void booming() {
		add(boom, basket.getLocation());
		pause(800);
		remove(boom);
		penalty.setSize(100, 100);
		add(penalty, getWidth() - penalty.getWidth(), 50);
		pause(1000);
		remove(penalty);
		if (ballsLeft >= PENALTY_POINTS) {
			ballsLeft -= PENALTY_POINTS;
		} else {
			ballsLeft = 0;
		}
		isExploded = false;
	}

	/**
	 * give extra points for 25 caught balls
	 */
	private void addingPoints() {
		if (score % 25 == 0 && score != 0 && isGoingOn) {
			ballsLeft += 25;
			isGoingOn = false;
			plus.setSize(100, 100);
			add(plus, getWidth() - plus.getWidth(), 50);
			pause(1000);
			remove(plus);
		}
	}

	/**
	 * moving basket and net with respect to mouse location
	 */
	public void mouseMoved(MouseEvent e) {
		basket.setLocation(e.getX() - WIDTH_OF_THE_BASKET / 2, (getHeight() - basket.getHeight()) / 2);
		net.setLocation(e.getX() - WIDTH_OF_THE_NET / 2 + 2, (getHeight() / 2) + 12);
		net.sendToFront();
	}

	/**
	 * wait for entering the name
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nameField) {
			playerName = nameField.getText();
		}
	}

	/** instance of Instructions class */
	static Instructions instructions;
	/** String that holds player name */
	static String playerName;
	/** instance of the @FallingObject class - ball */
	static FallingObject ball;
	/** instance of the @FallingObject class - bomb */
	static FallingObject bomb;
	/** instance of the @FallingObject class - slomoBall */
	static FallingObject slomoBall;
	/** instance of the @Background class */
	static Background background;
	/** instance of the @Background class that represents highScoreTable */
	static Background highScoreTable;
	/** instance of the @Wows class */
	static Wows wow;
	/** number that holds information about score of the player */
	static int score;
	/** random number that represents amount of bombs in the game */
	static int numOfBombs;
	/** minimum number for waiting the next falling object */
	static int randMin = 900;
	/** maximum number for waiting the next falling object */
	static int randMax = 1100;
	/** random number that hold represents image from @Wows class */
	static int randomWow;
	/** x-axis location for falling objects */
	static int dropLocationX;
	/** random waiting time till next ball */
	static int randomTime;
	/** random number that represents slomoBall fall */
	static int randomSlomo;
	/** representing number of balls left */
	static int ballsLeft = NUM_OF_BALLS;
	/** boolean that shows scoring of the ball */
	static boolean isScored;
	/** boolean that shows scoring of the slomoBall */
	static boolean isSlomo;
	/** boolean that shows scoring of the bomb */
	static boolean isExploded;
	/** that helps to add extra points */
	static boolean isGoingOn;
	/** thread for balls, bombs and slomoBalls */
	Thread thread;

	public static void main(String[] args) {
		new Basketball_Game().start();
	}
}