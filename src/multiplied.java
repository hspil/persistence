import java.util.*;
import java.math.*;

public class multiplied {
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
				switch(stringTotal.charAt(t)){
					case '1':
					case '4':
					case '5':
					case '0':
						nums = nums.add(new BigInteger(thing.giveMe(stringTotal.length() - t - 1)/*Integer.toString((int)(Math.pow(10, stringTotal.length() - t - 1)))*/));
						continue here;
				}
				if (stringTotal.charAt(t) == '2')
					twos++;
				if (stringTotal.charAt(t) == '3')
					threes++;
				if (stringTotal.charAt(t) == '6')
					sixes++;
				if (twos > 1 || threes > 1 || sixes > 1){
					nums = nums.add(new BigInteger(thing.giveMe(stringTotal.length() - t - 1)));
					continue here;
				}
				if (twos > 0 && threes > 0){
					nums = nums.add(new BigInteger(thing.giveMe(stringTotal.length() - t - 1)));
					twos = 0; threes = 0; sixes = 0;
					continue here;
				}
			}
			while(multi.compareTo(BigInteger.valueOf(9)) == 1) {
				multi = BigInteger.ONE;
				System.out.println(stringTotal);
				for (int t = 0; t < stringTotal.length(); t++) {
					multi = multi.multiply(BigInteger.valueOf(Character.getNumericValue(stringTotal.charAt(t))));
				}
				stringTotal = multi.toString();
				result++;
			}
			System.out.println(stringTotal + "\n" + "Steps: " + result);
			
			if(result > MAXSTEPS){
				MAXSTEPS = result;
				MAXSTEPS_NUMBER = nums;
				System.out.println("New Max: " + MAXSTEPS + " == " + MAXSTEPS_NUMBER + " Number length: " + stringTotal.length() + "\n");
			}
			else{
				System.out.println("Current Max Persistence: " + MAXSTEPS + " == " + MAXSTEPS_NUMBER + " Number length: " + nums.toString().length() + "\n");
			}
			nums = nums.add(BigInteger.ONE);
		}
	}
}
