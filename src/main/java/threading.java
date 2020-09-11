import java.util.*; 
import java.lang.Thread;

public class threading {
	int MAXSTEPS = 0;
	String MAXNUMBER = "";
	ArrayList<Mul> threads = new ArrayList<Mul>();
    public void counting(List<String> numString, int maxThreads){
		int objective = numString.size();
		while(maxThreads > threads.size() && numString.size() > threads.size()){
			threads.add(new Mul());
		}
		for(int i = 0; i < (objective / maxThreads) + 1; i++){
			for (int k = 0; k < maxThreads && k < objective; k++){
				threads.get(k).start(numString.get(0));
				numString.remove(0);
			}
		}
		for(int i = 0; i < threads.size(); i++){
			try{
				threads.get(i).join();
				if(threads.get(i).steps > MAXSTEPS){
					MAXSTEPS = threads.get(i).steps; 
					MAXNUMBER = threads.get(i).initialValue.toString();
				}
			}
			catch(Exception ex){
				System.out.println("Exception has been caught: " + ex);		
			}
		}
		numString.clear();
    }
}

