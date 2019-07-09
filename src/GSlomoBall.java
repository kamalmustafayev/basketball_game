/**
 * GSlomoBall class inherited from Falling object that moves the slomoBalls
 * during the game
 * 
 * @author Kamal Mustafayev
 *
 */
public class GSlomoBall extends FallingObject {

	/** moving slomoBall by y-axis */
	private double moveY;
	/** moving slomoBall by x-axis */
	private double moveX;
	/** height of the screen */
	private double screenHeight;
	/** height of the screen */
	private double screenWidth;
	/** pause time between every movement */
	static int PauseTime = 12;
	/** size of the slomoBall */
	double size;
	/** changing location while bouncing */
	int dyLocation = 25;
	/** moving while bouncing */
	int dyMove = -2;

	/**
	 * sending size and image name to the father class
	 * 
	 * @param size
	 *            of the slomoBall
	 * @param screenWidth
	 *            width of the screen
	 * @param screenHeight
	 *            height of the screen
	 */
	public GSlomoBall(double size, double screenWidth, double screenHeight) {
		super("SlomoBall.gif", size);
		this.size = size;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}

	/***
	 * whole process of falling the slomoBall
	 */
	@Override
	public void run() {
		while (getY() < Basketball_Game.basket.getY() + Basketball_Game.HEIGHT_OF_THE_BASKET) {
			fall();
			wallCheker();
			if (checkForScore()) {
				Basketball_Game.isSlomo = true;
			} else if (checkForRightCollision()) {
				moveY = -4;
				moveX = 1;
			} else if (checkForLeftCollision()) {
				moveY = -4;
				moveX = -1;
			}
		}
		rightOrLeft();
		bounce();
		setVisible(false);
	}

	/**
	 * slomoBall falling with increasing speed
	 */
	private void fall() {
		moveY += 0.1;
		move(0, moveY);
		pause(PauseTime);
	}

	/**
	 * the slomoBall goes to the right if ball falls on the right side of the screen
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
	 * slomoBall bouncing after touching the ground
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

			moveY = 1;
			while (getY() < screenHeight) {
				move(moveX, moveY);
				pause(PauseTime);
				size += 0.2;
				setSize(size, size);
			}
		}
	}

	/**
	 * check touch with left side of the ring
	 */
	private boolean checkForLeftCollision() {
		if (Math.abs(Basketball_Game.net.getY() - (getY() + getHeight())) <= 5) {
			if ((getX() <= Basketball_Game.net.getX() && getX() + getWidth() / 2 > Basketball_Game.net.getX())
					|| (getX() + getWidth() / 2 <= Basketball_Game.net.getX()
							&& getX() + getWidth() >= Basketball_Game.net.getX()))
				return true;
		}
		return false;
	}

	/**
	 * check touch with right side of the ring
	 */
	private boolean checkForRightCollision() {
		if (Math.abs(Basketball_Game.net.getY() - (getY() + getHeight())) <= 5) {
			if ((getX() + getWidth() / 2 > Basketball_Game.net.getX() + Basketball_Game.net.getWidth()
					&& getX() < Basketball_Game.net.getX() + Basketball_Game.net.getWidth())
					|| (Basketball_Game.net.getX() + Basketball_Game.net.getWidth() > getX() + getWidth() / 2
							&& Basketball_Game.net.getX() + Basketball_Game.net.getWidth() < getX() + getWidth()))
				return true;
		}
		return false;
	}

	/**
	 * check for goals by comparing locations
	 */
	@Override
	public boolean checkForScore() {
		if (Math.abs(Basketball_Game.net.getY() - (getY() + getHeight())) < 4.2) {
			if ((getX() > Basketball_Game.net.getX())
					&& (getX() + getWidth() < Basketball_Game.net.getX() + Basketball_Game.net.getWidth()))
				return true;
		}
		return false;
	}

	/**
	 * checks touches with the walls
	 */
	private void wallCheker() {
		if (getX() < 0) {
			moveY = -2.4;
			moveX = 1;
		}
		if (getX() + getWidth() > screenWidth) {
			moveY = -2.4;
			moveX = -1;
		}
	}
}
