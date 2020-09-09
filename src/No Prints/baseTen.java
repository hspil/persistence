import java.math.*;
import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class baseTen {
	public static void main (String[] args) throws IOException {
		BigInteger nums = new BigInteger("1"); // Essentially, a REALLY big integer
		String stringTotal = ""; // Used to store a converted nums value
		int MAXSTEPS = 0; // The current maximum persistence
		String MAXSTEPS_NUMBER = "0"; // The lowest number for the highest reached persistence
		give thing = new give(); //Creating an object of the give class, which is used for achieving the left to right adding
		int checkNums[] = new int[9];
		int temp = 2; // Used to chech if the number starts with at least a 2
		int currLeng = 0;
		long initialTime = System.nanoTime();
		long actTime = initialTime;
		int numThreads = 0;
		threading threadRipper = new threading();
		ArrayList<String> numString = new ArrayList<String>();
		int maxThreads = 32072;
		try{
			BufferedReader br = new BufferedReader(new FileReader("data.txt"));
			String[] input = br.readLine().split(" ");
			nums = new BigInteger(input[0]);
			MAXSTEPS = Integer.parseInt(input[1]);
			MAXSTEPS_NUMBER = input[2];
			currLeng = MAXSTEPS_NUMBER.length();
		}
		catch(IOException e){
			System.out.println("No data file detected.");
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"));
		try {
			here: while(true){ // The "here:" is for later, establishing a functional loop
				stringTotal = nums.toString(); // Converting
				checkNums = new int[9];
				temp = 0; // Restarted some variables
				for (int t = 0; t < stringTotal.length(); t++) {
					if (temp > Character.getNumericValue(stringTotal.charAt(t))){
						//System.out.print("Skipped " + nums.toString() + " for not being in order. ");
						nums = new BigInteger(thing.outOfOrder(nums.toString(), t));
						//System.out.println("nums is now " + nums.toString() + ".");
						continue;
					}
					switch(stringTotal.charAt(t)){ // Skips if there's a 1, 4, 5, or 0. In these cases, the number is not the first number (which can be 2 or 3), so we add the difference to get to 6.
						case '1': // It's the same number without the digit, which we've already checked
							//System.out.println("Skipped " + nums.toString() + " for having a 1.");
							nums = nums.add(new BigInteger(thing.giveMe(stringTotal.length() - t - 1)));
							continue here;
						case '4': // 4s are evil
							//System.out.println("Skipped " + nums.toString() + " for having a 4.");
							nums = nums.add(new BigInteger(thing.giveMe(stringTotal.length() - t - 1, "2")));
							continue here;
						case '5': // Either causes a 0, or another 5 that causes a 0
							//System.out.println("Skipped " + nums.toString() + " for having a 5.");
							nums = nums.add(new BigInteger(thing.giveMe(stringTotal.length() - t - 1)));
							continue here;
						case '0': // Death
							//System.out.println("Skipped " + nums.toString() + " for having a 0.");
							nums = nums.add(new BigInteger(thing.giveMe(stringTotal.length() - t - 1, "6")));
							continue here;
					}
					temp = Character.getNumericValue(stringTotal.charAt(t));
					checkNums[temp - 1]++;
		
					if (checkNums[1] > 1 || checkNums[2] > 1 || checkNums[5] > 1){ // There should only be one of 2, 3, and 6 (Individually). Also, 2 * 2 is a four, and 3 * 3 is 9, so no reason to calc those.
						//System.out.println("Skipped " + nums.toString() + " for having more than one 2, 3 and/or 6s.");
						nums = nums.add(new BigInteger(thing.giveMe(stringTotal.length() - t - 1)));
						continue here;
					}
					if (checkNums[1] > 0 && checkNums[2] > 0){ // There should only be either a single 2 or 3 or no 2s and 3s.
						//System.out.println("Skipped " + nums.toString() + " for having a 2 and a 3.");
						nums = nums.add(new BigInteger(thing.giveMe(stringTotal.length() - t - 1)));
						continue here;
					}
				}
				
				if(baseTen.ratio(checkNums[6], checkNums[7], checkNums[8]) == true){
					//System.out.println("Skipped " + nums.toString() + " for having a ration discrepency.");
					nums = nums.add(BigInteger.ONE);
					continue here;
				}
				numString.add(nums.toString());
				//System.out.println("Added " + nums.toString() + " to numString.");
				nums = nums.add(BigInteger.ONE); // Adds 1 onto the number
				
				if(nums.toString().length() > currLeng){
					threadRipper.counting(numString, maxThreads);
					currLeng = nums.toString().length();
					System.out.println("Current Max Persistence: " + MAXSTEPS + " == " + MAXSTEPS_NUMBER + " Number length: " + MAXSTEPS_NUMBER.toString().length() + " Current Length: " + currLeng + " (Total Time: " + thing.timeFormat(System.nanoTime() - initialTime) + " (Action Time: " + thing.timeFormat(System.nanoTime() - actTime) + " Threads: " + threadRipper.threads.size() + " Thread difference: " + (threadRipper.threads.size() - numThreads) + "\n");
					actTime = System.nanoTime();
					numString.clear();
					if(threadRipper.MAXSTEPS > MAXSTEPS){ // Some quality of life tracking stuffs
						MAXSTEPS = threadRipper.MAXSTEPS;
						MAXSTEPS_NUMBER = threadRipper.MAXNUMBER;
						System.out.println("New Max: " + MAXSTEPS + " == " + MAXSTEPS_NUMBER + " Number length: " + MAXSTEPS_NUMBER.toString().length() + " (Total Time: " + thing.timeFormat(System.nanoTime() - initialTime) + " (Action Time: " + thing.timeFormat(System.nanoTime() - actTime) + " Threads: " + threadRipper.threads.size() + " Thread difference: " + (threadRipper.threads.size() - numThreads) + "\n");
					}
					numThreads = threadRipper.threads.size();
					writer = new BufferedWriter(new FileWriter("data.txt")); // Overwrites the data.txt file with data
					writer.write(nums.toString() + " " + MAXSTEPS + " " + MAXSTEPS_NUMBER);
					writer.close(); // Closes and allows the file to be written over - allows you to crash your computer and still have the point if you don't lose the data
				}
			}
		}
		catch(Exception e){writer.close(); System.out.println(e + " Nice."); return;}
		
	}
	public static boolean ratio (int sevens, int eights, int nines){
		if((sevens == eights) || (eights == nines) || (sevens == nines) || (sevens + 1 == eights) || (sevens == eights + 1) || (eights + 1 == nines) || (eights == nines + 1) || (sevens + 1 == nines) || (sevens == nines + 1)){
			/*if((sevens == 0 && eights == 0) || (sevens == 0 && nines == 0) || (eights == 0 && nines == 0)){
				return false;
			}*/
			return false;
		}
		return true;
	}
}
