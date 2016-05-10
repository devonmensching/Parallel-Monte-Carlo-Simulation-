package simulation;

import java.util.Random;

/**
 * The Dart class, is a dart used in the 
 * simulation. This class uses a random number
 * generator to determine where the dart lands
 * on the dartboard. 
 * 
 * @author dmensching
 *
 */

public class Dart {
	
	// Represents the x coordinate of the dart
	private static double x;
	
	// Represents the y coordinate of the dart
	private static double y;
	
	/**
	 * The constructor sets the x and y 
	 * coordinates using the calcCoord() function. 
	 */
	public Dart() {
		x = calcCoord();
		y = calcCoord();
	}
	/**
	 * The calcCoord() function generates a random number
	 * for each coordinate when called. 
	 */
	private double calcCoord(){
		Random r = new Random();
		boolean x = r.nextBoolean();
		double num = 0;
		if(x){
			 num = r.nextDouble();
		}
		else {
			 num = r.nextDouble() - 1;
		}
		return num;
	}
	
	/**
	 * The getX() function returns the x coordinate. 
	 */
	public double getX(){
		return x; 
	}
	
	/**
	 * The getY() function returns the y coordinate. 
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * The boardCheck() function returns true if the 
	 * dart landed on the board and false if it didn't.  
	 */
	public boolean isOnBoard() {
		double d = Math.sqrt((x*x) + (y*y));
		if(d > 1) {
			return false;
		}
		return true;
	}
}
