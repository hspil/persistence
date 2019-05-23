import java.util.*;
import java.math.*;

public class baseFourPrints {
	public static void main (String[] args) {
		int result = 0; // The number of persistence
		BigInteger nums = new BigInteger("1" , 4); // Essentially, a REALLY big integer
		BigInteger multi = new BigInteger("0", 4); // Math stuffs for multiplying the digits
		String stringTotal = ""; // Used to store a converted nums value
		int MAXSTEPS = 0; // The current maximum persistence
		BigInteger MAXSTEPS_NUMBER = new BigInteger("0", 4); // The lowest number for the highest reached persistence
		give thing = new give(); //Creating an object of the give class, which is used for achieving the left to right adding
		int currLeng = 1;
		int temp = 2;
		here: while(true){ // The "here:" is for later, establishing a functional loop
			multi = new BigInteger("4"); // Setting to a value that works
			stringTotal = nums.toString(4); // Converting
			result = 0;
			temp = 2;
			for (int t = 0; t < stringTotal.length(); t++) {
				if (temp > Character.getNumericValue(stringTotal.charAt(t))){
					nums = nums.add(new BigInteger(thing.giveMe(stringTotal.length() - t - 1), 4));
					continue here;
				}
				temp = Character.getNumericValue(stringTotal.charAt(t));
				switch(stringTotal.charAt(t)){ // Skips if there's a 1, 4, 5, or 0
					case '1': // It's the same number without the digit, which we've already checked
					case '0': // Death
						nums = nums.add(new BigInteger(thing.giveMe(stringTotal.length() - t - 1), 4));
						continue here;
				}
			}
			System.out.println(stringTotal);
			while(multi.compareTo(BigInteger.valueOf(3)) == 1) { // "multi.compareTo(" returns 1 if the number being used to compare is larger
				multi = BigInteger.ONE;
				for (int t = 0; t < stringTotal.length(); t++) {
					multi = multi.multiply(new BigInteger(stringTotal.charAt(t) + ""));
				}
				stringTotal = multi.toString(4);
				System.out.println(stringTotal);
				result++;
				if(multi.toString().length() == 1){
					break;
				}
			}			
			System.out.println("Steps: " + result + "\n");
			if(result > MAXSTEPS){ // Some quality of life tracking stuffs
				MAXSTEPS = result;
				MAXSTEPS_NUMBER = new BigInteger(nums.toString(4));
				System.out.println("New Max: " + MAXSTEPS + " == " + MAXSTEPS_NUMBER + " Number length: " + nums.toString(4).length() + "\n");
			}
			nums = nums.add(BigInteger.ONE); // Adds 1 onto the number
		}
	}
}
