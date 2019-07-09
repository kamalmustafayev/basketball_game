import acm.graphics.GImage;

/**
 * Wows class randomly picks reaction images to scored balls
 * 
 * @author Kamal Mustafayev
 */
public class Wows extends GImage implements Constants {

	/**
	 * @param width
	 *            of the screen
	 * @param height
	 *            of the screen
	 * @param random
	 *            image
	 */
	public Wows(double width, double height, int random) {
		super("wows/" + random + ".png");
		setSize(150, 150);
		setLocation(rgen.nextDouble(100, width - getWidth()), rgen.nextDouble(0, height / 4));
	}
}
