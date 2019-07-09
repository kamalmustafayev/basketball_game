import java.awt.Color;
import java.io.*;
import java.util.*;

import acm.graphics.*;

/**
 * Table class reads the highScore from the file
 * 
 * @author Kamal Mustafayev
 *
 */
public class Table extends GCompound {

	/** shows top 10 players */
	static int listSize = 10;
	/** y coordinate location for name */
	static int LocationY = 35;
	/** height of the screen */
	static double height;
	/** labels that show names */
	static GLabel labels;
	/** list of names */
	ArrayList<String> list = new ArrayList<String>();

	/**
	 * Reading names from the file and sorting in decreasing order
	 * 
	 * @param height
	 *            of the screen
	 */
	public Table(double height) {
		Table.height = height;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("table.txt"));
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				list.add(line);
			}
		} catch (Exception e) {
		}
		list.sort(Collections.reverseOrder()); // sorting
		myPrint(list);
	}

	/**
	 * printing list on the screen with labels
	 * 
	 * @param list
	 *            of names
	 */
	private void myPrint(ArrayList<String> list) {
		GRect black = new GRect(3 * height, 3 * height);
		black.setFilled(true);
		add(black);

		if (listSize > list.size())
			listSize = list.size();
		for (int i = 0; i < listSize; i++) {
			labels = new GLabel(list.get(i), 200, LocationY);
			labels.setFont("CALIBRI-BOLD-32");
			labels.setColor(Color.orange);
			LocationY += height / 10;
			add(labels);
		}
	}
}
