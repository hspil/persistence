import java.math.*;
import java.util.*; 

public class baseTen {
	public static void main (String[] args) {
		BigInteger nums = new BigInteger("1"); // Essentially, a REALLY big integer
		String stringTotal = ""; // Used to store a converted nums value
		int MAXSTEPS = 0; // The current maximum persistence
		String MAXSTEPS_NUMBER = "0"; // The lowest number for the highest reached persistence
		give thing = new give(); //Creating an object of the give class, which is used for achieving the left to right adding
		int threes = 0;
		int twos = 0;
		int sixes = 0; // These are used for conditions
		int sevens = 0;
		int eights = 0;
		int nines = 0;
		int temp = 2; // Used to chech if the number starts with at least a 2
		int currLeng = 0;
		long initialTime = System.nanoTime();
		long actTime = initialTime;
		threading threadRipper = new threading();
		ArrayList<String> numString = new ArrayList<String>();
		int maxThreads = 3000;
		here: while(true){ // The "here:" is for later, establishing a functional loop
			stringTotal = nums.toString(); // Converting
			twos = 0;
			threes = 0;
			sixes = 0;
			sevens = 0;
			eights = 0;
			nines = 0;
			temp = 2; // Restarted some variables
			if (stringTotal.charAt(0) != '2' && stringTotal.charAt(0) != '3'){ // We don't need numbers that don't start with 2 or 3 - Well, probably
				nums = nums.add(new BigInteger(thing.giveMe(stringTotal.length() - 1))); // Adds 1 to the first digit
				continue here;
			}
			for (int t = 0; t < stringTotal.length(); t++) {
				if (temp > Character.getNumericValue(stringTotal.charAt(t))){
					nums = nums.add(new BigInteger(thing.giveMe(stringTotal.length() - t - 1)));
					continue here;
				}
				switch(stringTotal.charAt(t)){ // Skips if there's a 1, 4, 5, or 0
					case '1': // It's the same number without the digit, which we've already checked
					case '4': // 4s are evil
					case '5': // Either causes a 0, or another 5 that causes a 0
					case '0': // Death
						nums = nums.add(new BigInteger(thing.giveMe(stringTotal.length() - t - 1)));
						continue here;
				}
				temp = Character.getNumericValue(stringTotal.charAt(t));
				if (stringTotal.charAt(t) == '2') // These are for more conditionals
					twos++;
				if (stringTotal.charAt(t) == '3')
					threes++;
				if (stringTotal.charAt(t) == '6')
					sixes++;
				if (stringTotal.charAt(t) == '7') // These are for more conditionals
					sevens++;
				if (stringTotal.charAt(t) == '8')
					eights++;
				if (stringTotal.charAt(t) == '9')
					nines++;
	
				if (twos > 1 || threes > 1 || sixes > 1){ // There should only be one of 2, 3, and 6 (Individually). Also, 2 * 2 is a four, and 3 * 3 is 9, so no reason to calc those.
					nums = nums.add(new BigInteger(thing.giveMe(stringTotal.length() - t - 1)));
					continue here;
				}
				if (twos > 0 && threes > 0){ // There should only be either a single 2 or 3 or no 2s and 3s.
					nums = nums.add(new BigInteger(thing.giveMe(stringTotal.length() - t - 1)));
					continue here;
				}
			}
			
			if(baseTen.ratio(sevens, eights, nines, nums.toString()) == true){
				nums = nums.add(BigInteger.ONE);
				continue here;
			}
			
			if(nums.toString().length() > currLeng){
				threadRipper.counting(numString, maxThreads);
				currLeng = nums.toString().length();
				System.out.println("Current Max Persistence: " + MAXSTEPS + " == " + MAXSTEPS_NUMBER + " Number length: " + MAXSTEPS_NUMBER.toString().length() + " Current Length: " + currLeng + " (Total Time: " + thing.timeFormat(System.nanoTime() - initialTime) + " (Action Time: " + thing.timeFormat(System.nanoTime() - actTime) + " Threads: " + threadRipper.threads.size() + "\n");
				actTime = System.nanoTime();
				numString.clear();
			}
			numString.add(nums.toString());
			if(threadRipper.MAXSTEPS > MAXSTEPS){ // Some quality of life tracking stuffs
				MAXSTEPS = threadRipper.MAXSTEPS;
				MAXSTEPS_NUMBER = threadRipper.MAXNUMBER;
				System.out.println("New Max: " + MAXSTEPS + " == " + MAXSTEPS_NUMBER + " Number length: " + MAXSTEPS_NUMBER.toString().length() + " (Total Time: " + thing.timeFormat(System.nanoTime() - initialTime) + " (Action Time: " + thing.timeFormat(System.nanoTime() - actTime) + " Threads: " + threadRipper.threads.size() + "\n");
			}
			nums = nums.add(BigInteger.ONE); // Adds 1 onto the number
		}
		
	}
	public static boolean ratio (int sevens, int eights, int nines, String nums){
		if((sevens == eights) || (eights == nines) || (sevens == nines) || (sevens + 1 == eights) || (sevens == eights + 1) || (eights + 1 == nines) || (eights == nines + 1) || (sevens + 1 == nines) || (sevens == nines + 1)){
			if((sevens == 0 && eights == 0) || (sevens == 0 && nines == 0) || (eights == 0 && nines == 0)){
				return true;
			}
			return false;
		}
		return true;
	}
}
