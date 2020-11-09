import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This class implements various methods to save the position of the furniture
 * placed in the dorm.
 * 
 */
public class SaveButton {

	private static final int WIDTH = 96;
	private static final int HEIGHT = 32;

	private PApplet processing;
	private float[] position;
	private String label;

	/**
	 * This is a constructor for the LoadButton class. It initializes all of the
	 * variables to their specified values.
	 * 
	 * @param x
	 *            The x position of the mouse
	 * @param y
	 *            The y position of the mouse
	 * @processing
	 */
	public SaveButton(float x, float y, PApplet processing) {
		this.processing = processing;
		position = new float[2];
		position[0] = x;
		position[1] = y;
		label = "Save Room";
	}

	/**
	 * This method continuously updates the color and position of the of the "Save
	 * Room" button.
	 * 
	 */
	public void update() {
		if (isMouseOver() == true) {
			processing.fill(100);
		}

		else {
			processing.fill(200);
		}

		processing.rect(position[0] - (WIDTH / 2), position[1] + (HEIGHT / 2), position[0] + (WIDTH / 2),
				position[1] - (HEIGHT / 2));
		processing.fill(0);
		processing.text(label, position[0], position[1]);

	}

	/**
	 * This method creates and writes to a file name, "RoomData.ddd". The method
	 * writes the furniture type, the number of rotations, and the x and y position
	 * of the furniture to a file.
	 * 
	 * @param furniture
	 *            The same furniture array created in Main
	 */
	public void mouseDown(Furniture[] furniture) {
		String fileName = "RoomData.ddd";

		FileOutputStream fileByteStream = null;
		PrintWriter outFS = null;

		// try to open file
		try {
			fileByteStream = new FileOutputStream(fileName);
			outFS = new PrintWriter(fileByteStream);
		} catch (FileNotFoundException excpt) {
			System.out.print("WARNING: Could not save room contents to file RoomData.ddd.");
		}

		// file is now open and valid
		// can now write to file
		for (int i = furniture.length - 1; i >= 0; i--) {
			if (furniture[i] != null) { // the furniture has been place somewhere- has a position in room
				String type = furniture[i].getFurnitureType();
				int rotations = furniture[i].getNumRotations();
				float positionX = furniture[i].getPositionX();
				float positionY = furniture[i].getPositionY();
				// print to the file 
				outFS.println(type + ":" + positionX + "," + positionY + "," + rotations);
			}

			else {
				outFS.println();
			}
			outFS.flush();
		}
		outFS.close();

		// done with file, so try to close it
		try {
			fileByteStream.close();
		} catch (IOException e) {
		}
	}

	/**
	 * This method returns true if the mouse is over the specified area, else false.
	 * 
	 * @return boolean
	 */
	public boolean isMouseOver() {
		if (processing.mouseX >= position[0] - WIDTH / 2 && processing.mouseY >= position[1] - HEIGHT / 2
				&& processing.mouseX <= position[0] + WIDTH / 2 && processing.mouseY <= position[1] + 
				HEIGHT / 2) {
			return true;
		} else {
			return false;
		}
	}

}