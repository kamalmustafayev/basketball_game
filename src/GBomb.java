
/**
 * GBombl class inherited from Falling object that moves the bombs during the
 * game
 * 
 * @author Kamal Mustafayev
 *
 */
public class GBomb extends FallingObject {

	/** moving ball by y-axis */
	private double moveY;
	/** moving ball by x-axis */
	private double moveX;
	/** height of the screen */
	private double screenHeight;
	/** height of the screen */
	private double screenWidth;
	/** pause time between every movement */
	static int PauseTime = 12;
	/** size of the ball */
	double size;
	/** changing location while bouncing */
	int dyLocation = 25;
	/** moving while bouncing */
	int dyMove = -2;

	/***
	 * sending size and image name to the father class
	 * 
	 * @param size
	 *            of the bomb
	 * @param screenWidth
	 *            width of the screen
	 * @param screenHeight
	 *            height of the screen
	 */
	public GBomb(double size, double width, double height) {
		super("bomb.gif", size);
		this.size = size;
		this.screenWidth = width;
		this.screenHeight = height;
	}

	/**
	 * whole process of falling the bomb
	 */
	@Override
	public void run() {
		while (getY() < Basketball_Game.basket.getY() + Basketball_Game.HEIGHT_OF_THE_BASKET) {
			fall();
			if (checkForScore()) {
				Basketball_Game.isExploded = true;
				setVisible(false);
			}
		}
		rightOrLeft();
		bounce();
		setVisible(false);
	}

	/**
	 * bomb falling with increasing speed
	 */
	private void fall() {
		moveY += 0.1;
		move(0, moveY);
		pause(PauseTime);
	}

	/**
	 * check for touch by comparing locations
	 */
	@Override
	boolean checkForScore() {
		if (getY() + getHeight() >= Basketball_Game.net.getY()) {
			if ((getX() + getWidth() >= Basketball_Game.net.getX())
					&& (getX() <= Basketball_Game.net.getX() + Basketball_Game.net.getWidth()))
				return true;
		}
		return false;
	}

	/**
	 * the ball goes to the right if ball falls on the right side of the screen
	 */
	private void rightOrLeft() {
		if (getX() <= screenWidth / 2) {
			moveY = -2;
			moveX = -0.5;
		} else {
			moveY = -2;
			moveX = 0.5;
		}
	}

	/**
	 * bomb bouncing after touching the ground
	 */
	@Override
	void bounce() {
		for (int i = 0; i < 3; i++) {
			while (getY() < Basketball_Game.basket.getY() + Basketball_Game.HEIGHT_OF_THE_BASKET + dyLocation) {
				moveY += 0.1;
				move(moveX, moveY);
				pause(PauseTime);
				size += 0.2;
				setSize(size, size);
			}
			dyLocation += 25;
			dyMove += 0.5;
			moveY = dyMove;
		}
		moveY = 1;
		while (getY() < screenHeight) {
			move(moveX, moveY);
			pause(PauseTime);
			size += 0.2;
			setSize(size, size);
		}
	}

}
