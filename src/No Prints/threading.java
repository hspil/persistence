import java.util.*; 
import java.lang.Thread;

public class threading {
	int MAXSTEPS = 0;
	String MAXNUMBER = "";
	ArrayList<Mul> threads = new ArrayList<Mul>();
    public void counting(List<String> numString){
		int objective = numString.size();
			for(int i = 0; i < objective; i++){
				while(numString.size() > threads.size()){
					threads.add(new Mul(numString.get(i)));
				}
				threads.get(i).start(numString.get(i));
			}
			for(int i = 0; i < objective; i++){
				try{
					threads.get(i).join();
					if(threads.get(i).steps > MAXSTEPS){MAXSTEPS = threads.get(i).steps; MAXNUMBER = threads.get(i).initialValue.toString();}
				}
				catch(Exception ex){
					System.out.println("Exception has been caught " + ex);		
				}
			}
		}
    }

