import java.util.*;
import java.math.*;

public class LinearRegression { 
	BigInteger n = new BigInteger("0");
	BigDecimal xScale = new BigDecimal("0.00");
	BigDecimal yPosAdd = new BigDecimal("0.00");
	
	BigDecimal ySum = new BigDecimal("0.00");
	BigDecimal xSum = new BigDecimal("0.00");
	BigDecimal tempSum = new BigDecimal("0.00");
	BigDecimal tempSumTwo = new BigDecimal("0.00");
	
	BigDecimal xySum = new BigDecimal("0.00");
	BigDecimal xSumSquare = new BigDecimal("0.00");
	BigDecimal ySumSquare = new BigDecimal("0.00");
	
	MathContext precision = new MathContext(10);
	
    public void addPoint(BigInteger x, BigInteger y) { 
		xSum = xSum.add(new BigDecimal(x));
		ySum = ySum.add(new BigDecimal(y));
		if(n.compareTo(x) == -1)
			n = x;
    }
    
    public void setPrecision(int n){
		precision = new MathContext(n);
	}
    
    public BigDecimal getxScale(){
		return xScale;
	}
    
    public BigDecimal getyPosAdd(){
		return yPosAdd;
	}
	
	public void doMath(){
		xySum = xSum.multiply(ySum, precision);
		xSumSquare = xSum.multiply(xSum, precision);
		ySumSquare = ySum.multiply(ySum, precision);
		
		tempSum = ySum.multiply(xSumSquare, precision);
		tempSum = tempSum.subtract(xSum.multiply(ySum, precision));
		
		tempSumTwo = xSumSquare.multiply(new BigDecimal(n), precision);
		tempSumTwo = tempSumTwo.subtract(xSumSquare);
		
		xScale = tempSum.divide(tempSumTwo, precision);
		
		tempSum = xySum.multiply(new BigDecimal(n));
		tempSum = tempSum.subtract(xSum.multiply(ySum, precision));
		
		tempSumTwo = xSumSquare.multiply(new BigDecimal(n));
		tempSumTwo = tempSumTwo.subtract(xSumSquare);
		
		yPosAdd = tempSum.divide(tempSumTwo, precision);
	}
	
	public BigDecimal findPoint(BigDecimal x){
		x = x.subtract(yPosAdd);
		return tempSum = x.divide(xScale, new MathContext(100));
	}
    	
}
