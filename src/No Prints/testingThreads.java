import java.util.*; 
import java.lang.Thread;
public class testingThreads {
	
	public static void main (String[] args) {
		ArrayList<testThread> threads = new ArrayList<testThread>();
		int i = 0;
		while(true){
			for(int j = 0; j <= i; j++){
				threads.add(new testThread());
				threads.get(j).start(j);
			}
			if(threads.get(0).i != 0){
				System.out.println("This system can support " + i + " threads.");
				return;
			}
			i++;
		}
	}
}

