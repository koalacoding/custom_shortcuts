package custom_shortcuts;

import static custom_shortcuts.Frame.*;
import static custom_shortcuts.PixelRobot.*;

import java.awt.AWTException;
import java.awt.Color;

public class Main {

	public static void main(String[] args) throws AWTException {
		Frame window = new Frame(320, 240);
		window.setVisible(true);
		
		PixelRobot myRobot = new PixelRobot();
		
		String a = window.screenWidthField.getText();
		System.out.println(a);
		/*Color[] pixelArray = {new Color(150, 164, 178), new Color(67, 170, 90), new Color(156, 212, 166)};
		int[] foundCoordinates = myRobot.findPixelSequence(pixelArray, 1920, 1080);
		myRobot.moveMouseAndClick(foundCoordinates[0], foundCoordinates[1]);
		System.out.println(foundCoordinates[0] + " " + foundCoordinates[1]);*/
	}

}
