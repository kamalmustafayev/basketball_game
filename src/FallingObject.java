import acm.graphics.GImage;

/**
 * 
 * Abstract Class that is father of all falling objects
 * 
 * @author Kamal Mustafayev
 *
 */
abstract public class FallingObject extends GImage implements Runnable {

	/** Constructor that sends image to GImage and sets size */
	public FallingObject(String name, double size) {
		super(name);
		setSize(size, size);
	}

	abstract void bounce();

	abstract boolean checkForScore();
}
