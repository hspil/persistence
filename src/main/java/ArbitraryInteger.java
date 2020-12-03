import java.util.Arrays;
import java.util.Objects;

/****************************************
 Made by Kaleb Burris, 2020.
 A class for Arbitrarily large numbers.
 Inspired and bits taken from BigInteger.
 ****************************************/


public class ArbitraryInteger extends Number implements Comparable<ArbitraryInteger> {
    // The max length of a "group" - how many digits are allowable in an integer in the Number[] array.
    // Note: INT_MAX is 10 digits long. This is for the sake of addition,
    // multiplication is done with longs, which afford much more space.
    private static final int groupMaxLength = 9;
    // A number that is the maximum allowed value in a group.
    // Notice that this value is 10 digits long instead of 10.
    // This is for the sake of dividing and modulo-ing the value later on.
    private static final int groupMaxValue = 1000000000;
    // The stored Arbitrarily long Integer. Made out of longs for the sake of efficiency.
    private long[] Number = null;
    // The sign; denotes positivity (1) or negativity (-1).
    private int sign;

    // Constant values of ArbitraryInteger. Useful for iteration or for starting values as they're already initialized.
    public static final ArbitraryInteger ONE = new ArbitraryInteger("1");
    public static final ArbitraryInteger ZERO = new ArbitraryInteger("0");
    public static final ArbitraryInteger NEGATIVE_ONE = new ArbitraryInteger("-1");

    // Used publicly to declare an ArbitraryInteger using a String.
    ArbitraryInteger(String value) {
        // Defining whether the variable is positive or negative.
        if (value.charAt(0) == '-') {
            sign = -1;
            value = value.substring(1);
        } else {
            sign = 1;
        }

        // If the number is less than 10^9, just initialize without all the extra checks and such.
        if (value.length() <= groupMaxLength) {
            Number = new long[]{Integer.parseInt(value)};
            return;
        }

        // Declaring the most prominent part of the ArbitraryInteger - the first group.
        int cursor = value.length() % groupMaxLength;
        // If the value given's digits are not multiple of 9, we make the Number[] array
        // equal to the number of digits divided by 9, with an extra element for the extra digits
        if (cursor != 0) {
            Number = new long[(value.length() / groupMaxLength) + 1];
            Number[0] = Integer.parseInt(value.substring(0, cursor));
        }
        // If the check failed, the value given's digits are a multiple of 9, so the math is simpler.
        else {
            Number = new long[(value.length() / groupMaxLength)];
            Number[0] = Integer.parseInt(value.substring(0, groupMaxLength));
            cursor += groupMaxLength;
        }

        // Just looping through and adding the rest of the value String to the Number[] array.
        for (int i = 1; i < Number.length; i++) {
            Number[i] = Integer.parseInt(value.substring(cursor, cursor += groupMaxLength));
        }
    }

    // Used to declare and ArbitraryInteger based on a suitable array of ints and a given sign.
    // Super simple, basically just copy-pasting.
    private ArbitraryInteger(int[] value, int sign) {
        for (int i = 0; i < value.length; i++) {
            Number[i] = value[i];
        }
        this.sign = sign;
    }

    // Used for the multiply() function.
    // This assumes the long[] array given has no elements greater than an Integer.
    // Simply copies from a long[] array to an int[] array.
    private ArbitraryInteger(long[] result, int sign) {
        this.sign = sign;
        Number = result;
    }

    // Now deprecated.
    // I decided to make parts of this integrated into the add() code.
    // Left it here just in case I need it, and as a monument to my hubris.
    @Deprecated
    private static int[] carry(int[] value, int index) {

        // Just checking if carrying is actually needed.
        if (value[index] >= groupMaxValue) {
            // Checking if the relevant index is the first index.
            if (index == 0) {
                // Basically, we make a copy of the array, and shift the elements one to the right.
                // The performance of this is not that big of a deal, as it's called once per add() operation.
                value = Arrays.copyOf(value, value.length + 1);
                int[] temp = new int[value.length];
                if (value.length - 1 >= 0)
                    System.arraycopy(value, 0, temp, 1, value.length - 1);
                value = temp;
                index++;
            }
            // Make the next index of ints a little bigger, based on this one.
            value[index - 1] = value[index] / groupMaxValue;
            // Remove the amount we added to the next index of ints.
            value[index] = value[index] % groupMaxValue;
        }
        return value;
    }

    // Did a lot of research on big multiplication problems. Was a pain to debug and wrap my head around.
    // Tends to plateau around 40% slower over time with exponentially larger numbers. Numbers under 600 digits see
    // ~1.5-4.45 times faster times.
    public ArbitraryInteger multiply(ArbitraryInteger value) {
        // If either number is 0, just return 0.
        if (value.Number[0] == 0 || Number[0] == 0)
            return ArbitraryInteger.ZERO;

        // Declaring two arrays that will be acted upon.
        long[] x;
        long[] y;

        // Choosing the larger array.
        // If the number being applied upon by the function is larger,
        // store the Number[] array in x[].
        if (this.Number.length >= value.Number.length) {
            x = this.Number;
            y = value.Number;
        } else {
            y = this.Number;
            x = value.Number;
        }

        // Used for looping.
        // xCursor points at the place in the x[] (larger) array.
        int xCursor = x.length - 1;
        // yCursor points at the place in the y[] (smaller) array.
        int yCursor = y.length - 1;
        // sum[] is the array we write the products of the multiplication process to.
        // At the moment, the number of x and y elements added together seems to be the correct
        // number of elements needed in sum[]. Has some minor issues if sum[1] == 999999999 and sum[0] == 0.
        long[] sum;
        if (x[0] * y[0] >= groupMaxValue) {
            sum = new long[x.length + y.length];
        }
        else {
            sum = new long[x.length + y.length - 1];
        }
        // sumCursor points at the place in the sum[] array.
        int sumCursor = sum.length - 1;
        long product;
        // The primary multiplication loop.
        // We follow the yCursor as the y[] array contains fewer/smaller elements.
        while (yCursor >= 0) {
            // This is a check to see if we've hit the end of the x[] or sum[] arrays.
            if (xCursor < 0 || sumCursor < 0) {
                // Once we do, we move an element to the left on the y[] array.
                yCursor--;
                // As well as moving one element to the left on the sum[] array.
                // Think of this as putting the 0 down during long multiplication.
                sumCursor--;
                // Resets xCursor.
                xCursor = x.length - 1;
                // Resets the sumCursor, respective to the xCursor.
                sumCursor += xCursor + 1;
                // if we're out of y[] or sum[], break out of the loop.
                if (yCursor < 0 || sumCursor < 0)
                    break;
            }
            // This stores the sum of the position in x[] and y[] we're on.
            // It also decrements sumCursor and xCursor.
            product = (x[xCursor--] * y[yCursor]);
            // Adding the product to the current group.
            sum[sumCursor] += product;
            if (sumCursor > 0) {
                // This line shaves off any overflow from the group
                // we added to and adds it to the next group.
                sum[--sumCursor] += sum[sumCursor + 1] / groupMaxValue;
                // This does the shaving but finalizes it for the group
                // that's having the overflow removed.
                sum[sumCursor + 1] = sum[sumCursor + 1] % groupMaxValue;
            }
        }
        // Multiply the signs together when we return a new ArbitraryInteger.
        return new ArbitraryInteger(sum, this.getSign() * value.getSign());
    }

    // Unfinished divide() method. Brain hurts.
    /*public ArbitraryInteger divide(ArbitraryInteger value) {
        if (Number[0] == 0) {
            try {
                throw new Exception("Error: Division by Zero is illegal.");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        
        if (value.getNumber()[0] == 0)
            return ArbitraryInteger.ZERO;

        long[] x;
        long[] y;

        if (this.Number.length >= value.Number.length) {
            x = this.Number;
            y = value.Number;
        } else {
            y = this.Number;
            x = value.Number;
        }

        int xCursor = x.length - 1;
        int yCursor = y.length - 1;

        return new ArbitraryInteger(sum, this.getSign() * value.getSign());
    }*/

    // This is inspired by the BigInteger function, but is much more efficient recursively.
    // Simplified to a single function as we don't need regex.
    // Also, about 2.5x faster than BigInteger on average! (Over time, that it is.)
    public ArbitraryInteger add(ArbitraryInteger value) {
        // If they aren't the same sign, it's subtraction, not addition.
        if (value.getSign() != this.getSign()) {
            if (this.getSign() == -1)
                return value.subtract(this);
            return this.subtract(value);
        }

        // Arrays for adding. Used to make sure the
        // larger array is x, and the smaller is y.
        // I'm sure this came be made more efficient
        // via an if or another function, but it's not
        // worth the time.
        long[] x;
        long[] y;

        if (this.Number.length >= value.Number.length) {
            x = this.Number;
            y = value.Number;
        } else {
            y = this.Number;
            x = value.Number;
        }

        // sumArray[] is the array that stores our results.
        long[] sumArray;
        // Checking if the array needs to accommodate for a carry
        // overflowing into a new group.
        if (x.length == y.length && x[0] + y[0] >= groupMaxValue) {
            sumArray = new long[x.length + 1];
            if (x.length == 1) {
                sumArray[0] += ((x[0] + y[0]) / groupMaxValue);
                sumArray[1] += ((x[0] + y[0]) % groupMaxValue);
                return new ArbitraryInteger(sumArray, this.getSign());
            }
        }
        // If it's not going to overflow, just make the new array
        // as long as the longest value's array.
        else {
            sumArray = new long[x.length];
        }

        // Used for looping.
        int xCursor = x.length;
        int yCursor = y.length;
        int sumCursor = x.length;

        // For each group that is similar, add them together.
        while (yCursor > 0) {
            sumArray[--sumCursor] += (x[--xCursor] + y[--yCursor]);
            // Carrying the values greater than groupMaxValue.
            if (sumCursor > 0) {
                // Shaving off anything that was carried.
                sumArray[sumCursor - 1] += sumArray[sumCursor] / groupMaxValue;
                sumArray[sumCursor] = sumArray[sumCursor] % groupMaxValue;
            }
        }
        // This loop simply carries over x[] into sumArray[] and carries over all overflow.
        while (sumCursor > 0) {
            sumArray[--sumCursor] += x[--xCursor];
            if (sumCursor != 0) {
                sumArray[sumCursor - 1] += sumArray[sumCursor] / groupMaxValue;
                sumArray[sumCursor] = sumArray[sumCursor] % groupMaxValue;
            }
        }

        // The signs will always be the same, so just grab this
        // ArbitraryInteger's sign.
        return new ArbitraryInteger(sumArray, this.getSign());
    }

    // Essentially the add() function, but reversed.
    // Depending on the initial conditions, can be ~20-250% more efficient than BigInteger.
    // Occasionally 20% slower than BigInteger.
    public ArbitraryInteger subtract(ArbitraryInteger value) {
        // Basically, if this is negative and we're subtracting
        // a positive value - that's addition.
        if (value.getSign() != this.getSign()) {
            if (this.getSign() == -1) {
                this.setSign(-1);
                return value.add(this);
            }
            this.setSign(-1);
            return this.add(value);
        }

        // Establishing our variables for subtracting.
        long[] x = value.Number;
        long[] y = this.Number;
        // The array to store the resultant value.
        long[] result = new long[0];
        int sign = 0;
        // If the number being subtracted from is larger, the result
        // will be in the sign of this number.
        if (this.Number.length > value.Number.length) {
            result = new long[Number.length];
            sign = getSign();
        }
        // If they're equal, and this Number is still bigger,
        // the sign is the sign of this Number.
        else if (this.Number.length == value.Number.length && this.Number[0] >= value.Number[0]) {
            result = new long[Number.length];
            sign = getSign();
        }
        // All options exhausted, invert the sign.
        else {
            result = new long[value.Number.length];
            sign = value.getSign() * -1;
        }
        // Used for tracking where we are in the arrays.
        int xlen = x.length;
        int ylen = y.length;
        // The end product of the subtraction.
        long product;
        // The subtraction loop.
        while (ylen > 0) {
            // Performing the subtraction.
            product = x[--xlen] - y[--ylen];
            // Checking for the needed borrow.
            if (product < 0) {
                product += groupMaxValue;
                x[xlen - 1] -= 1;
            }
            // Pushing product into the result[] array.
            result[xlen] = product;
        }
        // Pushing the rest of x[] onto the result.
        while (xlen > 0) {
            result[--xlen] = x[xlen];
        }

        // Removes any preceding zeroes. This is
        // unavoidable, unfortunately.
        while (result.length != 1 && result[0] == 0) {
            long[] temp = new long[result.length - 1];
            System.arraycopy(result, 1, temp, 0, result.length - 1);
            result = temp;
        }
        // Returns the value.
        return new ArbitraryInteger(result, sign);
    }

    public int getSign() {
        return sign;
    }

    // Setting the negative/positive sign.
    public void setSign(int sign) {
        if (sign > 1 || sign < -1) {
            try {
                throw new Exception("Incorrect sign declaration");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        this.sign = sign;
    }

    // No clue if this works.
    @Override
    public int intValue() {
        if (Number.length == 1)
            return (int) Number[0];
        try {
            throw new Exception("Error: ArbitraryInteger value is greater than an Integer");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    @Override
    public long longValue() {
        if (Number.length == 1)
            return Number[0];
        if (Number.length <= 7) {
            long retLong = 0;
            for (int i = 0; i < Number.length; i++) {
                retLong += Number[i] * Math.pow(10, i);
            }
            return retLong;
        }
        try {
            throw new Exception("Error: ArbitraryInteger value is greater than a Long.");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    @Override
    public float floatValue() {
        return 0;
    }

    @Override
    public double doubleValue() {
        return 0;
    }

    // So the next two functions (equals() and hashCode()) are both machine generated.
    // I had nothing to do with these but they work like magic.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArbitraryInteger)) return false;
        ArbitraryInteger that = (ArbitraryInteger) o;
        return getSign() == that.getSign() &&
                Arrays.equals(getNumber(), that.getNumber());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getSign());
        result = 31 * result + Arrays.hashCode(getNumber());
        return result;
    }

    public long[] getNumber() {
        return Number;
    }

    @Override
    public int compareTo(ArbitraryInteger o) {
        String thisString = this.toString();
        String oString = o.toString();
        if (thisString.equals(oString))
            return 0;
        if (thisString.length() > oString.length())
            return 1;
        if (thisString.length() == oString.length()){
            int thisFirst = Integer.parseInt(String.valueOf(thisString.charAt(0)));
            int oFirst = Integer.parseInt(String.valueOf(oString.charAt(0)));
            if (thisFirst == oFirst)
                return 0;
            if (thisFirst > oFirst)
                return 1;
        }
        return -1;
    }

    // Returns the String representation of this Number.
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // Conditional for negative numbers.
        if (sign == -1)
            sb.append("-");
        // Appending the first element.
        // This is done now because we add preceding zeros later,
        // and doing it here makes the String have a bunch of zeroes
        // at the beginning.
        sb.append(Number[0]);
        // Looping through and appending each group on to a String.
        for (int i = 1; i < Number.length; i++) {
            // Adding in preceding zeroes using Java's weird methods
            // that I've never seen before.
            if (Long.toString(Number[i]).length() < 9) {
                sb.append("0".repeat(9 - Long.toString(Number[i]).length()));
            }
            sb.append(Number[i]);
        }
        // Returning the appended String.
        return sb.toString();
    }
}