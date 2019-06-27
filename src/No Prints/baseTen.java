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
		int currLeng = 1;
		long initialTime = System.nanoTime();
		long actTime = initialTime;
		threading threadRipper = new threading();
		ArrayList<String> numString = new ArrayList<String>();
		here: while(true){ // The "here:" is for later, establishing a functional loop
			stringTotal = nums.toString(); // Converting
			if (stringTotal.charAt(0) != '2' && stringTotal.charAt(0) != '3'){ // We don't need numbers that don't start with 2 or 3 - Well, probably
				nums = nums.add(new BigInteger(thing.giveMe(stringTotal.length() - 1))); // Adds 1 to the first digit
				continue here;
			}
			twos = 0;
			threes = 0;
			sevens = 0;
			eights = 0;
			nines = 0;
			sixes = 0;
			temp = 2; // Restarted some variables
			for (int t = 0; t < stringTotal.length(); t++) {
				if (temp > Character.getNumericValue(stringTotal.charAt(t))){
					nums = nums.add(new BigInteger(thing.giveMe(stringTotal.length() - t - 1)));
					continue here;
				}
				temp = Character.getNumericValue(stringTotal.charAt(t));
				switch(stringTotal.charAt(t)){ // Skips if there's a 1, 4, 5, or 0
					case '1': // It's the same number without the digit, which we've already checked
					case '4': // 4s are evil
					case '5': // Either causes a 0, or another 5 that causes a 0
					case '0': // Death
						nums = nums.add(new BigInteger(thing.giveMe(stringTotal.length() - t - 1)));
						continue here;
				}
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
	
				if (twos > 1 || threes > 1 || sixes > 1){ // There should only be one of 2, 3, and 6 (Individually)
					nums = nums.add(new BigInteger(thing.giveMe(stringTotal.length() - t - 1)));
					continue here;
				}
				if (twos > 0 && threes > 0){ // There should only be either a single 2 or 3
					nums = nums.add(new BigInteger(thing.giveMe(stringTotal.length() - t - 1)));
					twos = 0; threes = 0; sixes = 0;
					continue here;
				}
			}
			if(sevens != eights && sevens + 1 != eights && sevens != eights + 1 && sevens != nines && sevens + 1 != nines && sevens != nines + 1 && eights != nines && eights + 1 != nines && eights != nines + 1){
				nums = nums.add(BigInteger.ONE.add(BigInteger.ONE));
				sevens = 0; eights = 0; nines = 0;
				continue here;
			}
			numString.add(nums.toString());
			if(nums.toString().length() > currLeng){
				currLeng++;
				threadRipper.counting(numString);
				System.out.println("Current Max Persistence: " + MAXSTEPS + " == " + MAXSTEPS_NUMBER + " Number length: " + nums.toString().length() + " (Total Time: " + thing.timeFormat(System.nanoTime() - initialTime) + " (Action Time: " + thing.timeFormat(System.nanoTime() - actTime) + " Threads: " + threadRipper.threads.size() + "\n");
				actTime = System.nanoTime();
				numString.clear();
			}
			if(threadRipper.MAXSTEPS > MAXSTEPS){ // Some quality of life tracking stuffs
				MAXSTEPS = threadRipper.MAXSTEPS;
				MAXSTEPS_NUMBER = threadRipper.MAXNUMBER;
				System.out.println("New Max: " + MAXSTEPS + " == " + MAXSTEPS_NUMBER + " Number length: " + nums.toString().length() + " (Total Time: " + thing.timeFormat(System.nanoTime() - initialTime) + " (Action Time: " + thing.timeFormat(System.nanoTime() - actTime) + " Threads: " + threadRipper.threads.size() + "\n");
			}
			nums = nums.add(BigInteger.ONE); // Adds 1 onto the number
		}
	}
}
