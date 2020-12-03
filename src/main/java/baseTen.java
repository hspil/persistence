import java.util.*;
import java.io.*;

 /**********************************************************************\
 *    This function's purpose is to create Strings to be fed to the     *
 * threading class. It's extremely efficient and generates a linearly   *
 *            increasing amount of numbers per digit added.             *
 *                      Written by: Kaleb Burris                        *
 *                   Public Domain, use at own risk.                    *
 \**********************************************************************/
// Be warned: Very unreadable code!
public class baseTen {
	public static void main(String[] args) throws IOException {
		ArbitraryInteger nums = new ArbitraryInteger("1"); // Essentially, a REALLY big integer
		String stringTotal; // Used to store a converted nums value
		int MAXSTEPS = 0; // The current maximum persistence
		String MAXSTEPS_NUMBER = "0"; // The lowest number for the highest reached persistence
		give thing = new give(); //Creating an object of the give class, which is used for achieving the left to right adding
		int[] checkNums;
		int temp; // Used to chech if the number starts with at least a 2
		int currLeng = 0;
		long initialTime = System.nanoTime();
		long actTime = initialTime;
		int numThreads = 0;
		threading threadRipper = new threading();
		ArrayList<String> numString = new ArrayList<>();
		int maxThreads = 32072;
		try{
			BufferedReader br = new BufferedReader(new FileReader("data.txt"));
			String[] input = new String[] {br.readLine()};
			if (input[0] != null) {
				input = input[0].split(" ");
				nums = new ArbitraryInteger(input[0]);
				MAXSTEPS = Integer.parseInt(input[1]);
				MAXSTEPS_NUMBER = input[2];
				currLeng = MAXSTEPS_NUMBER.length();
			}
		}
		catch(IOException e){
			System.out.println("No data file detected.");
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"));
		try {
			here: while(true) { // The "here:" is used to return to the beginning of the while(true) loop.
				stringTotal = nums.toString(); // Converting nums to a String.
				checkNums = new int[9]; // Resetting checkNums[].
				temp = 0; // Restarted some variables
				for (int t = 0; t < stringTotal.length(); t++) {
					if (temp > Character.getNumericValue(stringTotal.charAt(t))) {
						nums = new ArbitraryInteger(thing.outOfOrder(nums.toString(), temp));
						continue here;
					}
					switch (stringTotal.charAt(t)) { // Skips if there's a 1, 4, 5, or 0. In these cases, the number is not the first number (which can be 2 or 3), so we add the difference to get to 6.
						case '4': // 4s are evil
						case '5': // Either causes a 0, or another 5 that causes a 0
						case '1': // It's the same number without the digit, which we've already checked
						case '0': // Death
							nums = nums.add(new ArbitraryInteger(thing.giveMe(stringTotal.length() - t - 1)));
							continue here;
					}
					temp = Character.getNumericValue(stringTotal.charAt(t));
					checkNums[temp - 1]++;

					if (checkNums[1] > 1 || checkNums[2] > 1 || checkNums[5] > 1) { // There should only be one of 2, 3, and 6 (Individually). Also, 2 * 2 is a four, and 3 * 3 is 9, so no reason to calc those.
						nums = nums.add(new ArbitraryInteger(thing.giveMe(stringTotal.length() - t - 1)));
						continue here;
					}
					if (checkNums[1] > 0 && checkNums[2] > 0) { // There should only be either a single 2 or 3 or no 2s and 3s.
						nums = nums.add(new ArbitraryInteger(thing.giveMe(stringTotal.length() - t - 1)));
						continue here;
					}
				}
				if(baseTen.ratio(checkNums[6], checkNums[7], checkNums[8])) {
					nums = nums.add(ArbitraryInteger.ONE);
					continue here;
				}
				numString.add(nums.toString());
				//System.out.println("Added " + nums.toString() + " to numString.");
				nums = nums.add(ArbitraryInteger.ONE); // Adds 1 onto the number
				if(stringTotal.length() > currLeng){
					currLeng = stringTotal.length();
					threadRipper.counting(numString, maxThreads);
					numString.clear();
					System.out.println("Current Max Persistence: " + MAXSTEPS + " == " + MAXSTEPS_NUMBER + " Number length: " + MAXSTEPS_NUMBER.length() + " Current Length: " + currLeng + " (Total Time: " + thing.timeFormat(System.nanoTime() - initialTime) + " (Action Time: " + thing.timeFormat(System.nanoTime() - actTime) + " Threads: " + threadRipper.numThreads + " Thread difference: " + (threadRipper.numThreads - numThreads) + "\n");
					actTime = System.nanoTime();
					if(threadRipper.MAXSTEPS > MAXSTEPS){ // Some quality of life tracking stuffs
						MAXSTEPS = threadRipper.MAXSTEPS;
						MAXSTEPS_NUMBER = threadRipper.MAXNUMBER;
						System.out.println("New Max: " + MAXSTEPS + " == " + MAXSTEPS_NUMBER + " Number length: " + MAXSTEPS_NUMBER.length() + " (Total Time: " + thing.timeFormat(System.nanoTime() - initialTime) + " (Action Time: " + thing.timeFormat(System.nanoTime() - actTime) + " Threads: " + threadRipper.numThreads + " Thread difference: " + (threadRipper.numThreads - numThreads) + "\n");
					}
					numThreads = threadRipper.numThreads;
					writer = new BufferedWriter(new FileWriter("data.txt")); // Overwrites the data.txt file with data
					writer.write(nums.toString() + " " + MAXSTEPS + " " + MAXSTEPS_NUMBER);
					writer.close(); // Closes and allows the file to be written over - allows you to crash your computer and still have the point if you don't lose the data
				}
			}
		}
		catch(Exception e){writer.close(); System.out.println("Exception caught during while(true) loop: " + e.toString());
		}
	}

	// Makes sure there's some sort of ratio between 7s, 8s, and 9s. It also makes this class linear.
	public static boolean ratio (int sevens, int eights, int nines){
		return (sevens != eights) && (eights != nines) && (sevens != nines) && (sevens + 1 != eights) && (sevens != eights + 1) && (eights + 1 != nines) && (eights != nines + 1) && (sevens + 1 != nines) && (sevens != nines + 1);
	}
}
