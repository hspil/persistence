import java.util.*;
import java.math.BigInteger;

public class multiplied {
	public static void main (String[] args) {
		int result = 0;
		BigInteger nums = new BigInteger("1");
		long multi = 1;
		String stringTotal = "";
		int MAXSTEPS = 0;
		BigInteger MAXSTEPS_NUMBER = new BigInteger("0");
		here: while(true){
			multi = 10;
			stringTotal = nums.toString();
			result = 0;
			for (int t = 0; t < stringTotal.length(); t++) {
				switch(stringTotal.charAt(t)){
					case '1':
					case '5':
					case '6':
					case '0':
						System.out.println("Skipped: " + nums + "\n");
						nums = nums.add(BigInteger.ONE);
						continue here;
				}
					
			}
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
				System.out.println("Current Max Steps: " + MAXSTEPS + " == " + MAXSTEPS_NUMBER + "\n");
			}
			 nums = nums.add(BigInteger.ONE);
		}
	}
}
