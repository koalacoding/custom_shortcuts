package custom_shortcuts;

import static custom_shortcuts.Frame.*;
import static custom_shortcuts.PixelRobot.*;

import java.awt.AWTException;
import java.awt.Color;

public class Main {

	public static void main(String[] args) throws AWTException {
		Frame fenetre = new Frame();
		//fenetre.setVisible(true);
		
		PixelRobot robot = new PixelRobot();
		
		Color[] pixelArray = {new Color(127, 0, 85), new Color(127, 0, 85), new Color(127, 0, 85), new Color(127, 0, 85)};
		int[] foundCoordinates = robot.findPixelSequence(pixelArray, 1920, 1080);
		System.out.println(foundCoordinates[0] + " " + foundCoordinates[1]);
	}

}
