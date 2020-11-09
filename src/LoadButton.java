import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

/**
 * This class implements various methods to place furniture items in the exact
 * positions they were in at the time the "Save Room" button was clicked.
 * 
 */
public class LoadButton {

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
	 *     The x position of the mouse 
	 * @param y
	 *     The y position of the mouse
	 * @processing
	 */
	public LoadButton(float x, float y, PApplet processing) {
		this.processing = processing;
		position = new float[2];
		position[0] = x;
		position[1] = y;
		label = "Load Room";
	}

	/**
	 * This method continuously updates the color and position of the of the "Load
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
	 * This method reads the contents of the RoomData.ddd file previously created.
	 * The method stores the contents of the file into an array. The contents are
	 * later used to create a new Furniture object.
	 * 
	 * @param furniture
	 *     The same furniture array created in Main
	 */
	public void mouseDown(Furniture[] furniture) {

		FileInputStream fileByteStream = null; // file input stream
		Scanner inFS = null; // Scanner object

		// Try to open file
		try {
			int number = furniture.length;
			for (int i = 0; i < furniture.length; ++i) {
				furniture[i] = null; // clear all previous position of furniture 
			}

			fileByteStream = new FileInputStream("RoomData.ddd");
			inFS = new Scanner(fileByteStream);

			// file is open and valid if we got this far
			int i = 0;
			while (inFS.hasNextLine()) {
				String line = inFS.nextLine();
				if (line.trim().length() == 0) { // empty line 
					continue;
				} else {
					if (furniture[i] == null) {
						String[] partOne = line.split(":"); // split into two element array 
						String[] partTwo = partOne[1].split(","); // split second element of first array into 3 
						String type = partOne[0].trim();
						float positionX = 0; // x position from file 
						float positionY = 0; // y position from file 
						int rotations = 0; // number of rotations retrieved from file 

						try {
							positionX = Float.parseFloat(partTwo[0].trim()); // turn string into float 
							positionY = Float.parseFloat(partTwo[1].trim()); 
							rotations = Integer.parseInt(partTwo[2].trim()); // turn int into float 
						} catch (NumberFormatException excpt) {
							System.out.print("WARNING: Found incorrectly formatted line in file <" + line + ">");
							continue;
						}

						File imageFile = new File("images/" + type + ".png"); // replicate image file 
						if (!imageFile.exists()) { // check existence 
							System.out.print(
									"WARNING: Could not find an image for a furniture object of type: <" + type + ">");
						}
						// add new object back into the array 
						Furniture newFurniture = new Furniture(type, positionX, positionY, rotations, processing);
						furniture[i] = newFurniture;
					}
					i++;

					if (inFS.hasNextLine() && i >= furniture.length - 1) { //ensures array will not be larger than 6
						System.out.print("WARNING: Unable to upload more furniture.");
						break;
					}
				}
			}

			// done with file, try to close it
			if (fileByteStream != null) {
				fileByteStream.close();
			}

		} catch (IOException excpt) {
			System.out.print("WARNING: Could not load room contents from file RoomData.ddd.");
		}
	}

	/**
	 * This method returns true if the mouse is over the specified area, else false.
	 * 
	 * @return boolean
	 */
	public boolean isMouseOver() {
		if (processing.mouseX >= position[0] - WIDTH / 2 && processing.mouseY >= position[1] - HEIGHT / 2
				&& processing.mouseX <= position[0] + WIDTH / 2 && processing.mouseY <= position[1] + HEIGHT / 2) {
			return true;
		} else {
			return false;
		}
	}

}
