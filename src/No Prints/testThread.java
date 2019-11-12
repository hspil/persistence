import java.math.*;
public class testThread extends Thread {
	int i;
	private Thread thread;
	public void run (int  i){
		this.i = i;
		System.out.println("Thread " + i);
	}
	
	public void start (int i) {
      thread = new Thread (this);
      if (thread == null) {
		start();
      }
      this.i = i;
      run();
   }
}

