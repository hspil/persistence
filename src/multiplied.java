import java.util.*;
import java.math.*;

public class multiplied {
	public static void main (String[] args) {
		int result = 0; // The number of persistence
		BigInteger nums = new BigInteger("1"); // Essentially, a REALLY big integer
		long multi = 0; // Math stuffs for multiplying the digits
		String stringTotal = ""; // Used to store a converted nums value
		int MAXSTEPS = 0; // The current maximum persistence
		BigInteger MAXSTEPS_NUMBER = new BigInteger("0"); // The lowest number for the highest reached persistence
		int threes = 0;
		int twos = 0;
		int sixes = 0;
		int sevens = 0;
		int temp = 2;
		here: while(true){
			multi = 10;
			stringTotal = nums.toString();
			result = 0;
			twos = 0;
			threes = 0;
			sixes = 0;
			temp = 2;
			if (stringTotal.charAt(0) != '2' && stringTotal.charAt(0) != '3'){
				System.out.println("Skipped: " + nums + " Current Max Persistence: " + MAXSTEPS + " == " + MAXSTEPS_NUMBER + " Number length: " + stringTotal.length() + "\n");
				nums = nums.add(new BigInteger(Integer.toString((int)(Math.pow(10, stringTotal.length() - 1)))));
				continue here;
			}
			// Skips any unnecessary numbers, but not those with 2/4 ratios, beacause the math is inefficient
			for (int t = 0; t < stringTotal.length(); t++) {
				if (temp > Character.getNumericValue(stringTotal.charAt(t))){
					System.out.println("Skipped: " + nums + " Current Max Persistence: " + MAXSTEPS + " == " + MAXSTEPS_NUMBER + " Number length: " + stringTotal.length() + "\n");
					nums = nums.add(new BigInteger(Integer.toString((int)(Math.pow(10, stringTotal.length() - t - 1)))));
					continue here;
				}
				temp = Character.getNumericValue(stringTotal.charAt(t));
				switch(stringTotal.charAt(t)){
					case '1':
					case '4':
					case '5':
					case '0':
						System.out.println("Skipped: " + nums + " Current Max Persistence: " + MAXSTEPS + " == " + MAXSTEPS_NUMBER + " Number length: " + stringTotal.length() + "\n");
						nums = nums.add(new BigInteger(Integer.toString((int)(Math.pow(10, stringTotal.length() - t - 1)))));
						continue here;
				}
				if (stringTotal.charAt(t) == '2')
					twos++;
				if (stringTotal.charAt(t) == '3')
					threes++;
				if (stringTotal.charAt(t) == '6')
					sixes++;
				if (stringTotal.charAt(t) == '7')
					sevens++;
				if (twos > 1 || threes > 1 || sixes > 1){
					System.out.println("Skipped: " + nums + " Current Max Persistence: " + MAXSTEPS + " == " + MAXSTEPS_NUMBER + " Number length: " + stringTotal.length() + "\n");
					nums = nums.add(new BigInteger(Integer.toString((int)(Math.pow(10, stringTotal.length() - t - 1)))));
					continue here;
				}
				if (twos > 0 && threes > 0){
					System.out.println("Skipped: " + nums + " Current Max Persistence: " + MAXSTEPS + " == " + MAXSTEPS_NUMBER + " Number length: " + stringTotal.length() + "\n");
					nums = nums.add(new BigInteger(Integer.toString((int)(Math.pow(10, stringTotal.length() - t - 1)))));
					twos = 0; threes = 0; sixes = 0;
					continue here;
				}
			}
			
			if (sevens == 4){
					System.out.println("Skipped: " + nums + " Current Max Persistence: " + MAXSTEPS + " == " + MAXSTEPS_NUMBER + "\n");
					nums = nums.add(BigInteger.ONE);
					continue here;
			}
			sevens = 0;
			while (multi > 9) {
				multi = 1;
				System.out.println(stringTotal);
				for (int t = 0; t < stringTotal.length(); t++) {
					multi *= Character.getNumericValue(stringTotal.charAt(t));
				}
				stringTotal = Long.toString(multi);
				result++;
			}
			
			if (multi < 9){
				System.out.println(stringTotal);
			}
			
			System.out.println("Steps: " + result);
			if(result > MAXSTEPS){
				MAXSTEPS = result;
				MAXSTEPS_NUMBER = nums;
				System.out.println("New Max: " + MAXSTEPS + " == " + MAXSTEPS_NUMBER + "\n");
			}
			else{
				System.out.println("Current Max Persistence: " + MAXSTEPS + " == " + MAXSTEPS_NUMBER + "\n");
			}
			 nums = nums.add(BigInteger.ONE);
		}
	}
}
