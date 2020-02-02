import java.io.*;
import java.nio.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.math.*;

public class trending {
	public static void main(String[] args) throws IOException {
		File file = new File("nodes.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String st = reader.readLine();
		String[] s2 = new String[2];
		int row = 0;
		LinearRegression linReg = new LinearRegression();
		while ((st = reader.readLine()) != null){
			s2 = st.split(" ");
			if(s2.length == 2){
				s2 = st.split(" ");
				row++;
				linReg.addPoint(new BigInteger(s2[0]), new BigInteger(s2[1]));
			}
		}
		linReg.doMath();
		System.out.println("f(x) = " + linReg.getxScale() + " + " + linReg.getyPosAdd());
		System.out.println("f(12) = " + linReg.findPoint(new BigDecimal("12")));
	}
}
