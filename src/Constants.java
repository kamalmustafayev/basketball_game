import javax.swing.JLabel;
import javax.swing.JTextField;

import acm.graphics.GImage;
import acm.util.RandomGenerator;

/**
 * Constants interface holds constants that are used in the game
 * 
 * @author Kamal Mustafayev
 */
public interface Constants {

	/** image of the basket */
	static GImage basket = new GImage("basket.png");
	/** image of the playing background */
	static GImage background = new GImage("background.jpg");
	/** image of the net */
	static GImage net = new GImage("net.png");
	/** image of the introBackground */
	static GImage intro = new GImage("intro.jpg");
	/** image of the endingBackground */
	static GImage ending = new GImage("ending.jpg");
	/** image of the explosion */
	static GImage boom = new GImage("boom.gif");
	/** image of the sign (-30) */
	static GImage penalty = new GImage("30.png");
	/** image of the sign (+25) */
	static GImage plus = new GImage("25.png");
	/** image of the slow motion sign */
	static GImage slow = new GImage("slow.png");
	/** image of the bomb */
	static GImage bomb = new GImage("bomb.gif");
	/** image of the ball */
	static GImage ball = new GImage("ball.gif");
	/** image of the goldenBall */
	static GImage goldenBall = new GImage("SlomoBall.gif");

	/** textfield for entering the name */
	static JTextField nameField = new JTextField(10);
	/** JLabel that asks for the name */
	static JLabel label = new JLabel("Please Enter Your Name");

	RandomGenerator rgen = RandomGenerator.getInstance();

	/** Constant representing number of balls that will fall */
	static final int NUM_OF_BALLS = 100;
	/** Constant representing size of falling objects in pixels */
	static final int SIZE_OF_THE_OBJECT = 50;
	/** Constant representing duration of slow motion in balls */
	static final int SLOMO_DURATION = 15;

	/** Constant representing width of basket */
	static final double WIDTH_OF_THE_BASKET = 150;
	/** Constant representing height of basket */
	static final double HEIGHT_OF_THE_BASKET = 142;
	/** Constant representing width of net */
	static final double WIDTH_OF_THE_NET = 71.5;
	/** Constant representing height of net */
	static final double HEIGHT_OF_THE_NET = 59;
	/**
	 * Constant representing number of balls that player will lose in case of
	 * catching bomb
	 */
	static final int PENALTY_POINTS = 30;
}
