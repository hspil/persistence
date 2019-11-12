import java.util.*;
import java.math.*;
import java.io.*;
import java.nio.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class persistenceNodes {
	public static void main (String[] args) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("nodes.txt"));
		writer.write("Number | Persistence");
		System.out.println();
		int result = 0; // The number of persistence
		BigInteger nums = new BigInteger("1"); // Essentially, a REALLY big integer
		BigInteger multi = new BigInteger("0"); // Math stuffs for multiplying the digits
		String stringTotal = ""; // Used to store a converted nums value
		int MAXSTEPS = 0; // The current maximum persistence
		String MAXSTEPS_NUMBER = "0"; // The lowest number for the highest reached persistence
		give thing = new give(); //Creating an object of the give class, which is used for achieving the left to right adding
		int temp = 2;
		int currLeng = 0;
		here: while(true){ // The "here:" is for later, establishing a functional loop
			stringTotal = nums.toString(); // Converting
			result = 0;
			temp = 2;
			multi = new BigInteger("10"); // Setting to a value that works
			for (int t = 0; t < stringTotal.length(); t++) { //Checking that the number is (from left to right) in ascending order
				if (temp > Character.getNumericValue(stringTotal.charAt(t))){
					nums = nums.add(new BigInteger(thing.giveMe(stringTotal.length() - t - 1)));
					continue here;
				}
				
				temp = Character.getNumericValue(stringTotal.charAt(t));
				switch(stringTotal.charAt(t)){ // Skips if there's a 1 or 0
					case '1': // It's the same number without the digit, which we've already checked
					case '0': // Death
						nums = nums.add(new BigInteger(thing.giveMe(stringTotal.length() - t - 1)));
						continue here;
				}
			}
			while(multi.compareTo(new BigInteger(Integer.toString(9))) == 1) { // "multi.compareTo(" returns 1 if the number being used to compare is larger
				multi = BigInteger.ONE;
				for (int t = 0; t < stringTotal.length(); t++) {
					multi = multi.multiply(new BigInteger(stringTotal.charAt(t) + ""));
				}
				stringTotal = multi.toString();
				result++;
				if(multi.toString().length() == 1){
					break;
				}
			}			
			writer.newLine();
			writer.write(nums.toString() + " " + result);
			if(result > MAXSTEPS){ // Some quality of life tracking stuffs
				MAXSTEPS = result;
				MAXSTEPS_NUMBER = nums.toString();
				System.out.println("New Max: " + MAXSTEPS + " == " + MAXSTEPS_NUMBER + " Number length: " + MAXSTEPS_NUMBER.length() + "\n");
			}
			if(nums.toString().length() > currLeng){
				currLeng = nums.toString().length();
				System.out.println("Current Max Persistence: " + MAXSTEPS + " == " + MAXSTEPS_NUMBER + " Number length: " + MAXSTEPS_NUMBER.toString().length() + " Current Length: " + currLeng + "\n");
			}
			nums = nums.add(BigInteger.ONE); // Adds 1 onto the number
		}
	}
}
