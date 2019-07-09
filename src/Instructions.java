import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;

/**
 * Showing instructions on the screen with labels
 * 
 * @author Kamal Mustafayev
 *
 */

public class Instructions extends GCompound implements Constants {

	/**
	 * writing instructions with labels
	 * 
	 * @param width
	 *            of the screen
	 * @param height
	 *            of the screen
	 */
	public Instructions(int width, int height) {
		GRect black = new GRect(width, height);
		black.setFilled(true);
		add(black);

		GLabel instructions = new GLabel("Instructions : ");
		locator(instructions, 0);
		add(instructions, 50, 50);

		GLabel balls1 = new GLabel("100 balls will fall from the sky!");
		locator(balls1, 1);
		add(balls1);

		GLabel balls2 = new GLabel("For every 25 caught balls You will get 25 extra balls");
		locator(balls2, 1.3);
		add(balls2);

		GLabel bombs1 = new GLabel("Random number of “Bombs” will fall from the sky!");
		locator(bombs1, 2);
		add(bombs1);

		GLabel bombs2 = new GLabel("For every touched “Bomb” You will lose 30 balls that could have fallen");
		locator(bombs2, 2.3);
		add(bombs2);

		GLabel slomos1 = new GLabel("Random number of “Golden Balls” will fall from the sky!");
		locator(slomos1, 3);
		add(slomos1);

		GLabel slomos2 = new GLabel("For every caught “Golden Ball” You will get “Slow Motion” effect for 15 balls");
		locator(slomos2, 3.3);
		add(slomos2);

		ball.setSize(SIZE_OF_THE_OBJECT, SIZE_OF_THE_OBJECT);
		bomb.setSize(SIZE_OF_THE_OBJECT, SIZE_OF_THE_OBJECT);
		goldenBall.setSize(SIZE_OF_THE_OBJECT, SIZE_OF_THE_OBJECT);
		add(ball, 650, 130);
		add(bomb, 650, 260);
		add(goldenBall, 650, 390);
	}

	/**
	 * locates lines on the screen
	 * 
	 * @param label
	 *            instructions line
	 * @param i
	 *            helps to select Y location to the label
	 */
	private void locator(GLabel label, double i) {
		label.setColor(Color.ORANGE);
		label.setFont("TIMES-BOLD-18");
		label.setLocation(20, 130 * i);
	}
}
