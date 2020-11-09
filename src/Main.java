/**
 * This class implements various methods to use the DormDesigner 4000. When run, the 
 * user may add furniture items to the room by clicking on the specified create button. 
 * If the user chooses, they may rotate the furniture by pressing the "R" key or delete 
 * furniture by pressing the "D" key. If the user would like to save the current format of 
 * their dorm room, they may click on the save button, then later re-load the saved room by 
 * pressing the load button. 
 * 
 * @author Ellie Beres
 **/
public class Main {

	private PApplet processing;
	private PImage backgroundImage;
	private Furniture[] furniture;
	private CreateBedButton bedButton;
	private CreateSofaButton sofaButton;
	private SaveButton saveButton;
	private LoadButton loadButton;

	/**
	 * This is the main method for the DormDesigner 4000. This method starts the
	 * application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Utility.startApplication();
	}

	/**
	 * This is a constructor for the Main class. It initializes all of the 
	 * variables to their specified values. 
	 * 
	 * @param processing 
	 */
	public Main(PApplet processing) {
		this.processing = processing;
		backgroundImage = processing.loadImage("images/background.png"); 
		furniture = new Furniture[6]; // furniture array with 6 elements 
		for (int i = 0; i < furniture.length; ++i) {
			furniture[i] = null; //set each element to null 
		}
		bedButton = new CreateBedButton(50, 24, processing);
		sofaButton = new CreateSofaButton(150, 24, processing);
		saveButton = new SaveButton(650, 24, processing);
		loadButton = new LoadButton(750, 24, processing);

	}

	/**
	 * This method continuously updates the changing design of the room. In
	 * particular, this method draws the furniture and buttons
	 * to their specified position(s).
	 */
	public void update() {
		processing.background(100, 150, 250);
		processing.image(backgroundImage, processing.width / 2, processing.height / 2);
        
		// updates position and color of button
		bedButton.update();
		sofaButton.update();
		loadButton.update();
		saveButton.update();

		//updates position of furniture 
		for (int i = 0; i < furniture.length; ++i) {
			if (furniture[i] != null) {
				furniture[i].update();
			}
		}

	}

	/**
	 * This method determines what actions should be performed when
	 * the mouse is clicked down on a certain button. It also updates the 
	 * position of the furniture objects when the mouse is clicked down. 
	 * 
	 */
	public void mouseDown() {
		for (int i = furniture.length - 1; i >= 0; --i) {
			if (furniture[i] == null) { // methods only get called when position in array is null  
				if (bedButton.isMouseOver()) {
					furniture[i] = bedButton.mouseDown();
					break;
				} else if (sofaButton.isMouseOver()) {
					furniture[i] = sofaButton.mouseDown();
					break;
				} else if (saveButton.isMouseOver()) {
					saveButton.mouseDown(furniture);
					break;
				} else if (loadButton.isMouseOver()) {
					loadButton.mouseDown(furniture);
					break;
				}
			}
		}

		for (int j = furniture.length - 1; j >= 0; --j) {
			if (furniture[j] != null) {
				if (furniture[j].isMouseOver()) {
					furniture[j].mouseDown();
					break;
				}
			}
		}
	}

	/**
	 * This method is used to indicated that the mouse is no longer
	 * clicking down on an object. 
	 */
	public void mouseUp() {
		for (int i = 0; i < furniture.length; ++i) {
			if (furniture[i] != null) {
				furniture[i].mouseUp();
			}
		}
	}

	/**
	 * This method allows the user to rotate and delete beds by pressing 
	 * the "r" and "d" key, respectively. 
	 */
	public void keyPressed() {
		if (processing.key == 'd' || processing.key == 'D') {
			for (int i = 0; i < furniture.length; ++i) {
				if (furniture[i] != null) {
					if (furniture[i].isMouseOver() == true) {
						furniture[i] = null; // deletes the bed from screen, still 6 elements in array
						break;
					}
				}
			}
		}
		if (processing.key == 'r' || processing.key == 'R') {
			for (int i = 0; i < furniture.length; ++i) {
				if (furniture[i] != null) {
					if (furniture[i].isMouseOver() == true) {
						furniture[i].rotate();
						break;
					}
				}

			}
		}
	}
}