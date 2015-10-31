package custom_shortcuts;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;

public class PixelRobot {
	public PixelRobot() throws AWTException {}
	
    Robot robot = new Robot();
    
	void moveMouseAndClick(int x, int y) {
        robot.mouseMove(x, y);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	int[] findPixelSequence(Color[] pixelArray, int screenWidth, int screenHeight) {
		BufferedImage screen = robot.createScreenCapture(new Rectangle(0, 0, screenWidth,
																															screenHeight));
		Color pixelColor = new Color(screen.getRGB(0, 0)); // Initializing.
		
		boolean found = true;
		
		for (int y = 0; y < screenHeight; y++) {
			for (int x = 0; x < screenWidth; x++) {
				// If testing the actual pixel would get us testing non existing pixels.
				if (x + pixelArray.length > screenWidth) {
					break;
				}
				
				pixelColor = new Color(screen.getRGB(x, y));
				//System.out.println("x = " + x + " y = " + y + " " + pixelArray[0] + " " +  pixelColor);
				
				if (pixelColor.equals( pixelArray[0])) { // If current pixel is equal to the first pixel of the pixel sequence.
					for (int k = 0; k < pixelArray.length; k++) {
						Color pixelColorNext = new Color(screen.getRGB(x + k, y)); // Getting the color of the next pixels on the screen.
						if (!pixelColorNext.equals(pixelArray[k])) { // The sequence don't match.
							found = false;
							break; // We will test with the next pixel on the screen. 
						}
					}
					
					if (found == true) {
						int[] array = {x, y};
						return array;
					}
					
					found = true; // Reinitializing 'found'.
				}
			}
		}
		
		int[] emptyArray = {0, 0};
		return emptyArray;
	}	
}
