import java.util.*;
import java.math.*;

public class baseTen {
	public static void main (String[] args) {
		int result = 0; // The number of persistence
		BigInteger nums = new BigInteger("1"); // Essentially, a REALLY big integer
		BigInteger multi = new BigInteger("0"); // Math stuffs for multiplying the digits
		String stringTotal = ""; // Used to store a converted nums value
		int MAXSTEPS = 0; // The current maximum persistence
		BigInteger MAXSTEPS_NUMBER = new BigInteger("0"); // The lowest number for the highest reached persistence
		give thing = new give(); //Creating an object of the give class, which is used for achieving the left to right adding
		int threes = 0;
		int twos = 0;
		int sixes = 0; // These are used for conditions
		int temp = 2; // Used to chech if the number starts with at least a 2
		int currLeng = 1;
		here: while(true){ // The "here:" is for later, establishing a functional loop
			multi = new BigInteger("10"); // Setting to a value that works
			stringTotal = nums.toString(); // Converting
			if (stringTotal.charAt(0) != '2' && stringTotal.charAt(0) != '3'){ // We don't need numbers that don't start with 2 or 3 - Well, probably
				nums = nums.add(new BigInteger(thing.giveMe(stringTotal.length() - 1))); // Adds 1 to the first digit
				continue here;
			}
			result = 0;
			twos = 0;
			threes = 0;
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
				if (twos > 1 || threes > 1 || sixes > 1){ // There should only be 1 of 2, 3, and 6 (Not collectively)
					nums = nums.add(new BigInteger(thing.giveMe(stringTotal.length() - t - 1)));
					continue here;
				}
				if (twos > 0 && threes > 0){ // There should only be either a single 2 or 3
					nums = nums.add(new BigInteger(thing.giveMe(stringTotal.length() - t - 1)));
					twos = 0; threes = 0; sixes = 0;
					continue here;
				}
			}
			while(multi.compareTo(BigInteger.valueOf(9)) == 1) { // "multi.compareTo(" returns 1 if the number being used to compare is larger
				multi = BigInteger.ONE;
				for (int t = 0; t < stringTotal.length(); t++) {
					multi = multi.multiply(BigInteger.valueOf(Character.getNumericValue(stringTotal.charAt(t))));
				}
				stringTotal = multi.toString();
				result++;
			}			
			if(result > MAXSTEPS){ // Some quality of life tracking stuffs
				MAXSTEPS = result;
				MAXSTEPS_NUMBER = nums;
				System.out.println("New Max: " + MAXSTEPS + " == " + MAXSTEPS_NUMBER + " Number length: " + stringTotal.length() + "\n");
			}
			if(nums.toString().length() > currLeng){
				currLeng++;
				System.out.println("Current Max Persistence: " + MAXSTEPS + " == " + MAXSTEPS_NUMBER + " Number length: " + nums.toString().length() + "\n");
			}
			nums = nums.add(BigInteger.ONE); // Adds 1 onto the number
		}
	}
}
