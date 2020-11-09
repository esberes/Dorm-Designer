/**
 * This class implements various methods to drag and draw (redraw) the furniture
 * at the specified position.
 */
public class Furniture {

	private PApplet processing;
	private PImage image;
	private float[] position;
	private boolean isDragging;
	private int rotations;
	private String furnitureType;

	/**
	 * This is a constructor for the Furniture class. It initializes all of the
	 * variables to their specified values.
	 * 
	 * @param furniture
	 *            The type of furniture the user wishes to use in the room
	 * @param processing
	 */
	public Furniture(String furniture, PApplet processing) {
		this.processing = processing;
		furnitureType = furniture;
		image = processing.loadImage("images/" + furnitureType + ".png");
		position = new float[2];
		position[0] = processing.width / 2;
		position[1] = processing.height / 2;
		isDragging = false;
		rotations = 0;

	}

	/**
	 * This is a second constructor for the CreateSofaButton class. It initializes
	 * all of the variables to their specified values.
	 * 
	 * @param furniture
	 *            The type of furniture the user wishes to use in the room
	 * @param positionX
	 *            The x position of the mouse
	 * @param positionY
	 *            The y position of the mouse
	 * @param rotations
	 *            The number of rotations a piece of furniture has made
	 * @param processing
	 */
	public Furniture(String furniture, float positionX, float positionY, int rotations, PApplet processing) {
		this.processing = processing;
		furnitureType = furniture;
		image = processing.loadImage("images/" + furnitureType + ".png");
		position = new float[2];
		position[0] = positionX;
		position[1] = positionY;
		isDragging = false;
		this.rotations = rotations;

	}

	/**
	 * This method continuously updates the position of the piece of furniture,
	 * depending on where it has been dragged and if it has been rotated.
	 * 
	 */
	public void update() {
		processing.image(image, position[0], position[1], rotations * PApplet.PI / 2);

		if (isDragging == true) {
			position[0] = processing.mouseX;
			position[1] = processing.mouseY;
		}
	}

	/**
	 * This method assigns the location of the mouse to the new positions of the bed
	 * after being dragged.
	 */
	public void mouseDown() {
		if (isMouseOver() == true) {
			isDragging = true;
			position[0] = processing.mouseX;
			position[1] = processing.mouseY;
		}
	}

	/**
	 * This method is used to indicated that the mouse is no longer clicking down on
	 * an object.
	 */
	public void mouseUp() {
		isDragging = false;
	}

	/**
	 * This method returns true if the mouse is over the specified area, else false.
	 * This method takes into account the possibility of a bed being in the same
	 * position, but rotated.
	 * 
	 * @return boolean
	 */
	public boolean isMouseOver() {
		// position of furniture with odd number of rotations 
		if (rotations % 2 == 1) {
			if (processing.mouseX >= position[0] - image.height / 2
					&& processing.mouseY >= position[1] - image.width / 2
					&& processing.mouseX <= position[0] + image.height / 2
					&& processing.mouseY <= position[1] + image.width / 2) {
				return true;
			}
		// position of furniture with even number of rotations or no rotations 
		} else if (rotations % 2 == 0 || rotations == 0) {
			if (processing.mouseX >= position[0] - image.width / 2
					&& processing.mouseY >= position[1] - image.height / 2
					&& processing.mouseX <= position[0] + image.width / 2
					&& processing.mouseY <= position[1] + image.height / 2) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method determines how many times the piece of furniture has been
	 * rotated.
	 */
	public void rotate() {
		rotations += 1;
	}

	/**
	 * This method returns the furniture type of the object.
	 * 
	 * @return String
	 */
	public String getFurnitureType() {
		return furnitureType;
	}

	/**
	 * This method returns the number of rotations the piece of furniture has
	 * performed.
	 * 
	 * @return int
	 */
	public int getNumRotations() {
		return rotations;
	}

	/**
	 * This method returns the x position of the object.
	 * 
	 * @return float
	 */
	public float getPositionX() {
		return position[0];
	}

	/**
	 * This method returns the y position of the object.
	 * 
	 * @return float
	 */
	public float getPositionY() {
		return position[1];
	}

}
