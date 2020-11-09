/**
 * This class implements various methods to create a sofa button, which upon
 * clicking, creates a new furniture sofa object.
 * 
 */
public class CreateSofaButton {

	private static final int WIDTH = 96;
	private static final int HEIGHT = 32;

	private PApplet processing;
	private float[] position;
	private String label;

	/**
	 * This is a constructor for the CreateSofaButton class. It initializes all of
	 * the variables to their specified values.
	 * 
	 * @param x
	 *            The x position of the mouse
	 * @param y
	 *            The y position of the mouse
	 * @param processing
	 */
	public CreateSofaButton(float x, float y, PApplet processing) {
		this.processing = processing;
		position = new float[2];
		position[0] = x;
		position[1] = y;
		label = "Create Sofa";
	}

	/**
	 * This method continuously updates the color and position of the of the "Create
	 * Sofa" button.
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
	 * This method creates a new furniture object when the mouse is clicked down.
	 * 
	 * @return Furniture The new furniture object that has been created
	 * 
	 */
	public Furniture mouseDown() {
		Furniture newSofa = new Furniture("sofa", processing);
		return newSofa;
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
