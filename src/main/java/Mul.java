import java.util.ArrayList;

/**********************************************************************\
 * This class is a thread that's used to do Multiplicative Persistence. *
 *                      Written by: Kaleb Burris                        *
 *                   Public Domain, use at own risk.                    *
 \**********************************************************************/

class Mul extends Thread {
    // These two ArrayLists are what store the values we calculate.
    public static ArrayList<ArbitraryInteger> initialValues = new ArrayList<>();
    public static ArrayList<Integer> steps = new ArrayList<>();
    private int threadNum;

    public void run() {
        String stringTotal = initialValues.get(threadNum).toString();
        // The case that the number doesn't require ArbitraryInteger, that being that it's below the int max.
        if (stringTotal.length() <= 9) {
            // sumNum will be used to sum the product during the multiplication loop.
            int sumNum;
            // This check makes sure we're not 1 digit long.
            while (stringTotal.length() > 1) {
                // Setting sumNum to the first digit.
                sumNum = stringTotal.charAt(0) - '0';
                // Looping though stringTotal.
                for (int i = 1; i < stringTotal.length(); i++) {
                    // Multiplying sumNum by the next digit.
                    sumNum *= stringTotal.charAt(i) - '0';
                }
                // Turning sumNum back to a String.
                stringTotal = Integer.toString(sumNum);
                // Incrementing the number of steps.
                steps.set(threadNum, steps.get(threadNum) + 1);
            }
        } else {
            // Declaring the variables.
            long[] longArray;
            String[] stringArray;
            String tempString;
            int sumNum;
            ArbitraryInteger arbInt = new ArbitraryInteger(stringTotal);
            // Looping through until stringTotal is 1 digit long.
            while (arbInt.toString().length() > 1) {
                if (arbInt.toString().contains("0")) {
                    // Increments the step value and ends the loop.
                    // Zeroes make the entire value 1 digits long, so there's no reason to waste the time.
                    steps.set(threadNum, steps.get(threadNum) + 1);
                    return;
                }
                // Makes longArray[] equal to an array of longs - made by ArbitraryInteger.
                longArray = arbInt.getNumber();
                // stringArray derives its length from longArray[].
                stringArray = new String[longArray.length];
                // Resetting arbInt.
                arbInt = ArbitraryInteger.ONE;
                // Looping through stringArray.
                for (int i = 0; i < stringArray.length; i++) {
                    // The total sum of the loop.
                    sumNum = 1;
                    // This loop goes through and multiplies the digits together.
                    while (longArray[i] > 0) {
                        // Multiplying sumNum by the next digit.
                        sumNum *= longArray[i] % 10;
                        longArray[i] /= 10;
                    }
                    // Puts sumNum back into stringArray[].
                    stringArray[i] = Integer.toString(sumNum);
                }
                // The final step in doing a round of multiplication.
                for (String string : stringArray) {
                    arbInt = arbInt.multiply(new ArbitraryInteger(string));
                }
                // Increments the steps value.
                steps.set(threadNum, steps.get(threadNum) + 1);
            }
        }
    }

    // This function starts the thread.
    // Takes the String needed to be checked and stores it in stringTotal and initializes initialValue.
    public void start(String numString, int threadNum) {
        // Setting up the space needed for the thread.
        initialValues.add(new ArbitraryInteger(numString));
        steps.add(0);
        this.threadNum = threadNum;
        // Running the thread.
        run();
    }
}
