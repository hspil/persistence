import java.math.*;
class Mul extends Thread {
	BigInteger thisValue = new BigInteger("10");
	BigInteger initialValue = BigInteger.ONE;
	int steps = 0;
	public void run(String numString) {
		String stringTotal = numString;
		initialValue = new BigInteger(stringTotal);
		while(thisValue.compareTo(BigInteger.valueOf(9)) == 1) { // "multi.compareTo(" returns 1 if the number being used to compare is larger
			thisValue = BigInteger.ONE;
			for (int t = 0; t < stringTotal.length(); t++) {
				thisValue = thisValue.multiply(BigInteger.valueOf(Character.getNumericValue(stringTotal.charAt(t))));
			}
			steps++;
			stringTotal = thisValue.toString();
		}
	}
}
