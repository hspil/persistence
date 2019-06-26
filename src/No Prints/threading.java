import java.util.*; 

public class threading {
	int MAXSTEPS = 0;
	String MAXNUMBER = "";
	ArrayList<Mul> threads = new ArrayList<Mul>();
    public void counting(List<String> numString){
		int objective = numString.size();
		here: while(objective != 0){
			for(int i = 0; i < objective; i++){
				while(numString.size() > threads.size()){
					threads.add(new Mul());
				}
				threads.get(i).run(numString.get(i));
			}
			for(int i = 0; i < objective; i++){
				try{
					threads.get(i).join();
					if(threads.get(i).steps > MAXSTEPS){MAXSTEPS = threads.get(i).steps; MAXNUMBER = threads.get(i).initialValue.toString();}
				}
				catch(Exception ex){
					System.out.println("Exception has been caught" + ex);		
				}
			}
			objective--;
			continue here;
		}
    }
}
