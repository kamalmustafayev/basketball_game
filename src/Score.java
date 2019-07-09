import java.awt.Color;
import java.io.*;
import acm.graphics.*;

/**
 * Class that sends score labels to the background and writes information to the
 * file
 * 
 * @author Kamal Mustafayev
 *
 */
public class Score extends GCompound implements Constants {

	/** label that shows the score */
	GLabel GScore;
	/** label that shows the how many balls left */
	GLabel GBallsLeft;
	/** label that shows the record */
	GLabel GRecord;
	/** label that shows the final score */
	GLabel GFinalScore;
	/** white color */
	Color color = Color.WHITE;
	/** x coordinate location for labels */
	int xLocation = 20;
	/** y coordinate location for labels */
	int yLocation = 40;
	/** record of the player */
	static int record;
	/** final score of the player */
	static int finalScore;
	/** string that used when player scored less that 10 */
	static String zero;

	/**
	 * adding labels to GCompound during to game
	 * 
	 * @param score
	 *            of the player
	 * @param ballsLeft
	 *            to fall
	 */
	public Score(int score, int ballsLeft) {
		if (score > record)
			Score.record = score;

		GScore = new GLabel("Your score is: " + score);
		GScore.setColor(color);
		GScore.setLocation(xLocation, yLocation);
		yLocation += 40;

		GBallsLeft = new GLabel("Balls left: " + ballsLeft);
		GBallsLeft.setColor(color);
		GBallsLeft.setLocation(xLocation, yLocation);
		yLocation += 40;

		GRecord = new GLabel("The record is: " + record);
		GRecord.setColor(color);
		GRecord.setLocation(xLocation, yLocation);
		yLocation = 40;

		add(GScore);
		add(GBallsLeft);
		add(GRecord);

	}

	/**
	 * prints final score of the player in ending background
	 * 
	 * @param finalScore
	 *            of the player
	 * @param width
	 *            of the screen
	 * @param height
	 *            of the screen
	 */
	public Score(int finalScore, double width, double height) {
		Score.finalScore = finalScore;
		GFinalScore = new GLabel(Basketball_Game.playerName + ", YOUR SCORE IS " + finalScore + " BALLS");
		GFinalScore.setFont("TIMES-BOLD-25");
		GFinalScore.setColor(Color.orange);
		GFinalScore.setLocation(width / 3, height / 2);
		add(GFinalScore);
	}

	/**
	 * writing score and name to the file
	 */
	static void writer() {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("table.txt", true));
			if (Score.finalScore < 10)
				zero = "0";
			else
				zero = "";
			pw.println(zero + Score.finalScore + " points - " + Basketball_Game.playerName);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * zeroing all variables to start the game again
	 */
	static void zeroing() {
		Table.LocationY = 35;
		Basketball_Game.isScored = false;
		Basketball_Game.isExploded = false;
		Basketball_Game.score = 0;
		Basketball_Game.randMin = 900;
		Basketball_Game.randMax = 1100;
		Basketball_Game.ballsLeft = NUM_OF_BALLS;
		Actions.normalizeSpeed();
	}

}
