import java.math.*;
class Mul extends Thread {
	private Thread thread;	
	BigInteger thisValue = new BigInteger("10");
	BigInteger initialValue = BigInteger.ONE;
	int steps = 0;
	String stringTotal = "";
	public Mul(String numString){stringTotal = numString;}
	public void run() {
		steps = 0;
		thisValue = new BigInteger("10");
		initialValue = BigInteger.ONE;
		initialValue = new BigInteger(stringTotal);
		thisValue = initialValue;
		while(thisValue.compareTo(BigInteger.valueOf(9)) == 1) { // "multi.compareTo(" returns 1 if the number being used to compare is larger
			thisValue = BigInteger.ONE;
			for (int t = 0; t < stringTotal.length(); t++) {
				thisValue = thisValue.multiply(BigInteger.valueOf(Character.getNumericValue(stringTotal.charAt(t))));
			}
			steps++;
			stringTotal = thisValue.toString();
		}
	}
	public void start (String numString) {
      if (thread == null) {
		thread = new Thread (this);
		start();
      }
      stringTotal = numString;
      run();
   }
}
