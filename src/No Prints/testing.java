public class testing {
	
	public static void main (String[] args) {
		Mul testee = new Mul();
		testee.start("277777788888899");
		try{
			testee.join();
			System.out.println("steps: " + testee.steps);
		}catch(Exception e){}
	}
}

