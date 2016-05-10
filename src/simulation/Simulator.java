package simulation;


import java.util.*;

/**
 * The Simulator class, which executes the simulation.
 * @author dmensching
 *
 */
public class Simulator 
{
	
	// The number of games, or threads, that the user 
	// wants to play. 
	private static int games = 0;
	
	// The number of darts that will be thrown at the
	// dartboard in each game. 
	private static int darts = 0;
	
	// This is an array of threads
	private static ArrayList<Thread> threads = new ArrayList<Thread>();
	
	// This is an array of results form each thread
	private static double result = 0.0;
	
	/**
	 * The main function runs the program. The intro() method
	 * is run.  
	 * @param args
	 */
	public static void main(String[] args) {
		
		intro();
		
		// The Dartboard class extends the Thread class, and 
		// calculates the value of pie for each game. 
		class Dartboard extends Thread {
			public void run() {
				int onBoard = 0;
				for(int i = 0; i < darts; i++) {
					Dart dart = new Dart();
					if(dart.isOnBoard()){
						onBoard++;
					}
				}
				System.out.println(onBoard);
				double pi = (double)(onBoard * 4) / (double)darts;
				result += pi;
			}
		}
		
		// Create and start the number of threads (games) 
		// that the user has inputed. 
		for(int i = 0; i < games; i++) {
			threads.add(new Dartboard());
			threads.get(i).start();
		}
		
		// Join all of the threads to the main thread
		try {
			for(Thread thread: threads) {
				thread.join();
			}
		}
		catch(InterruptedException e) {
			System.out.println("Something went wrong with the threads.");
		}
		
		// Print out the average of the games 
		System.out.println("\nThe value of pi is: " + result/games);
	}
	
	/**
	 * The intro() function informs the user what the program will be 
	 * doing and then asks the user for the number of games played 
	 * and the number of darts thrown in each game. 
	 */
	private static void intro() {
		
		// Inform the user what the program will be doing
		System.out.println("Welcome to the Parallel Monte Carlo Simulation!\n\n"
				+ "This simulation is modeled after the Monte Carlo methods. In a Monte Carlo simulation, random \n"
				+ "number generators simulate real-world situations. In this simulation pretend that you have your \n"
				+ "eyes closed and you are throwing darts at a dartboard. Random numbers will be generated to \n"
				+ "determine each dart’s x and y coordinates. We will then use the data we collected from our\n"
				+ "randomly distributed darts to statistically estimate the value of pi.\n");
		
		// Ask the user to enter the number of threads
		Scanner scanner = new Scanner(System.in);
		System.out.print("To begin, enter the number of games played(between 1 and 20): ");
		games = scanner.nextInt();
		if(games < 1 || games > 20) {
			System.out.println("The number must be between 1 and 20. Please enter another number: ");
			games = scanner.nextInt();
		}
		
		// Ask the user to enter the number of darts
		System.out.print("Now enter the number of darts thrown each game: ");
		darts  = scanner.nextInt();
		if(darts < 1){
			System.out.println("Please enter a number larger than 0: ");
			darts = scanner.nextInt();
		}
		
		scanner.close();
		
	}
}