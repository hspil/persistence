import java.math.*;
import java.util.Arrays;

class Mul extends Thread {
	private Thread thread;	
	BigInteger calcNum = new BigInteger("10");
	BigInteger initialValue = BigInteger.ONE;
	int steps = 0;
	String stringTotal = "";
	int temp = 0;
	int tempInt[] = new int[2];
	String[] tempStrings;
	public void run() {
		steps = 0;
		initialValue = new BigInteger(stringTotal);
		calcNum = initialValue;
		here: do{
			if(calcNum.compareTo(BigInteger.valueOf(10)) != 0){
				stringTotal = calcNum.toString();
			}
			else{
				stringTotal = initialValue.toString();
			}
			 
			if(stringTotal.length() % 8 == 0){
				temp = stringTotal.length() / 8;
			}
			else{
				temp = (stringTotal.length() / 8) + 1;
			}
			calcNum = new BigInteger("1");	
			tempInt = new int[temp];
			Arrays.fill(tempInt, 1);
			tempStrings = StringSplit(stringTotal, temp);
			if(temp == 1){
				do {
					tempInt[0] = 1;
					for (int t = 0; t < tempStrings[0].length(); t++) {
						tempInt[0] *= Character.getNumericValue(tempStrings[0].charAt(t));
					}
					steps++;
					tempStrings[0] = Integer.toString(tempInt[0]);
					if(tempStrings[0].length() == 1){
						calcNum = new BigInteger(tempStrings[0]);
						continue here;
					}
				}while(tempStrings[0].length() > 1);
			}
			else{
				for(int target = 0; target < tempStrings.length; target++){
					tempInt[target] = 1;
					for(int i = 0; i < tempStrings[target].length(); i++){
						tempInt[target] *= Character.getNumericValue(tempStrings[target].charAt(i));
					}
					tempStrings[target] = Integer.toString(tempInt[target]);
				}
				for(int i = 0; i < temp; i++){
					calcNum = calcNum.multiply(BigInteger.valueOf(tempInt[i]));
				}
				steps++;
				continue here;
			}
		}while(calcNum.toString().length() > 1);
	}
	
	private static String[] StringSplit(String splitee, int numberOfStrings){
		String[] strings = new String[numberOfStrings];
		if(numberOfStrings == 1){
			strings[0] = splitee;
			return strings;
		}
		for(int i = 0; i < numberOfStrings; i++){
			if(i + 1 == numberOfStrings){
				strings[i] = splitee.substring(i * 8, (i * 8) + (splitee.length() - (i * 8)));
				return strings;
			}
			strings[i] = splitee.substring((i * 8), (i+1) * 8);
		}
		return strings;
	}
	
	public void start (String numString) {
      stringTotal = numString;
      run();
   }
}
