import java.util.*;
import java.math.*;

public class QuadraticRegression { 
	BigInteger n = new BigInteger("0");
	BigDecimal a = new BigDecimal("0.00");
	BigDecimal b = new BigDecimal("0.00");
	BigDecimal c = new BigDecimal("0.00");
	
	BigDecimal ySum = new BigDecimal("0.00");
	BigDecimal xSum = new BigDecimal("0.00");
	BigDecimal tempSum = new BigDecimal("0.00");
	BigDecimal tempSumTwo = new BigDecimal("0.00");
	
	BigDecimal xySum = new BigDecimal("0.00");
	BigDecimal xSumSquared = new BigDecimal("0.00");
	BigDecimal ySumSquared = new BigDecimal("0.00");
	BigDecimal Sumxx = new BigDecimal("0.00");
	BigDecimal Sumxy = new BigDecimal("0.00");
	BigDecimal SumxSquaredy = new BigDecimal("0.00");
	BigDecimal Sumxsquaredxsquared = new BigDecimal("0.00");
	BigDecimal Sumxxsquared = new BigDecimal("0.00");

	
    public void addPoint(BigInteger x, BigInteger y) { 
		xSum = xSum.add(new BigDecimal(x));
		ySum = ySum.add(new BigDecimal(y));
		Sumxx = Sumxx.add(new BigDecimal(x).pow(2));
		SumxSquaredy = SumxSquaredy();
		if(n.compareTo(x) == -1)
			n = x;
    }
	
	public void doMath(){
		//Okay, so we're going to establish our constants.
		xySum = xSum.multiply(ySum);
		xSumSquared = xSum.multiply(xSum);
		ySumSquared = ySum.multiply(ySum);
		
	}
	
	public BigDecimal findPoint(BigDecimal x){
		return BigDecimal.ONE;
	}
    	
}
