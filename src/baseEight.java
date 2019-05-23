import java.util.*;
import java.math.*;

public class baseEight {
	public static void main (String[] args) {
		int result = 0; // The number of persistence
		BigInteger nums = new BigInteger("1" , 8); // Essentially, a REALLY big integer
		BigInteger multi = new BigInteger("0", 8); // Math stuffs for multiplying the digits
		String stringTotal = ""; // Used to store a converted nums value
		int MAXSTEPS = 0; // The current maximum persistence
		BigInteger MAXSTEPS_NUMBER = new BigInteger("0", 8); // The lowest number for the highest reached persistence
		give thing = new give(); //Creating an object of the give class, which is used for achieving the left to right adding
		int currLeng = 1;
		int temp = 2;
		here: while(true){ // The "here:" is for later, establishing a functional loop
			multi = new BigInteger("8"); // Setting to a value that works
			stringTotal = nums.toString(4); // Converting
			result = 0;
			temp = 2;
			for (int t = 0; t < stringTotal.length(); t++) {
				if (temp > Character.getNumericValue(stringTotal.charAt(t))){
					nums = nums.add(new BigInteger(thing.giveMe(stringTotal.length() - t - 1), 8));
					continue here;
				}
				temp = Character.getNumericValue(stringTotal.charAt(t));
				switch(stringTotal.charAt(t)){ // Skips if there's a 1, 4, 5, or 0
					case '1': // It's the same number without the digit, which we've already checked
					case '0': // Death
						nums = nums.add(new BigInteger(thing.giveMe(stringTotal.length() - t - 1), 8));
						continue here;
				}
			}
			System.out.println(nums);
			while(multi.compareTo(BigInteger.valueOf(7)) == 1) { // "multi.compareTo(" returns 1 if the number being used to compare is larger
				multi = BigInteger.ONE;
				for (int t = 0; t < stringTotal.length(); t++) {
					multi = multi.multiply(new BigInteger(stringTotal.charAt(t) + "", 8));
				}
				stringTotal = multi.toString();
				result++;
				System.out.println(stringTotal);
				if(multi.toString().length() == 1){
					break;
				}
			}			
			System.out.println("Steps: " + result);

			
			if(result > MAXSTEPS){ // Some quality of life tracking stuffs
				MAXSTEPS = result;
				MAXSTEPS_NUMBER = new BigInteger(nums.toString(8));
				System.out.println("New Max: " + MAXSTEPS + " == " + MAXSTEPS_NUMBER + " Number length: " + nums.toString(8).length() + "\n");
			}
			nums = nums.add(BigInteger.ONE); // Adds 1 onto the number
		}
	}
}
