import java.util.*;
import java.math.*;

public class baseWhatever {
	public static void main (String[] args) {
		System.out.print("Base: ");
		Scanner input = new Scanner(System.in);
		int base = input.nextInt();
		input.close();
		int check = 10;
		if (base > 10){
			check = base;
		}
		System.out.println();
		int result = 0; // The number of persistence
		BigInteger nums = new BigInteger("1" , base); // Essentially, a REALLY big integer
		BigInteger multi = new BigInteger("0", base); // Math stuffs for multiplying the digits
		String stringTotal = ""; // Used to store a converted nums value
		int MAXSTEPS = 0; // The current maximum persistence
		String MAXSTEPS_NUMBER = "0"; // The lowest number for the highest reached persistence
		give thing = new give(); //Creating an object of the give class, which is used for achieving the left to right adding
		int temp = 2;
		here: while(true){ // The "here:" is for later, establishing a functional loop
			stringTotal = nums.toString(base); // Converting
			result = 0;
			temp = 2;
			for (int t = 0; t < stringTotal.length(); t++) {
				if (temp > Character.getNumericValue(stringTotal.charAt(t))){
					nums = nums.add(new BigInteger(thing.giveMe(stringTotal.length() - t - 1), base));
					continue here;
				}
				multi = new BigInteger("10", base); // Setting to a value that works
				temp = Character.getNumericValue(stringTotal.charAt(t));
				switch(stringTotal.charAt(t)){ // Skips if there's a 1 or 0
					case '1': // It's the same number without the digit, which we've already checked
					case '0': // Death
						nums = nums.add(new BigInteger(thing.giveMe(stringTotal.length() - t - 1), base));
						continue here;
				}
			}
			while(multi.compareTo(new BigInteger(Integer.toString(base - 1, base), base)) == 1) { // "multi.compareTo(" returns 1 if the number being used to compare is larger
				multi = BigInteger.ONE;
				for (int t = 0; t < stringTotal.length(); t++) {
					multi = multi.multiply(new BigInteger(stringTotal.charAt(t) + "", check));
				}
				stringTotal = multi.toString();
				result++;
				if(multi.toString().length() == 1){
					break;
				}
			}			
			
			if(result > MAXSTEPS){ // Some quality of life tracking stuffs
				MAXSTEPS = result;
				MAXSTEPS_NUMBER = nums.toString(base);
				System.out.println("New Max: " + MAXSTEPS + " == " + MAXSTEPS_NUMBER + " Number length: " + MAXSTEPS_NUMBER.length() + "\n");
			}
			nums = nums.add(BigInteger.ONE); // Adds 1 onto the number
		}
	}
}
