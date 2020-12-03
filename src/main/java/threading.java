import java.util.ArrayList;
import java.util.List;

 /**********************************************************************\
 *  This class's purpose is to loop through a given List<> of Strings   *
 *    and find their Multiplicative persistence using the Mul class.    *
 *  It is possible to modify Mul to find additive persistence as well.  *
 *                      Written by: Kaleb Burris                        *
 *                   Public Domain, use at own risk.                    *
 \**********************************************************************/

public class threading {
	// Stores the object's maximum persistence that it's found.
	public int MAXSTEPS = 0;
	// This stores the String value of the value that has the largest found Multiplicative persistence.
	public String MAXNUMBER = "";
	// An ArrayList of Mul threads.
	public ArrayList<Mul> threads = new ArrayList<>();
	// Used for finding how many threads were used. Mainly for debugging.
	int numThreads = 0;
	// Use this function to start the Multiplicative persistence loop.
    public void counting(List<String> numString, int maxThreads) {
    	// Storing the number of threads that will be exercised during this cycle.
		numThreads = numString.size();
		// This loop makes sure you don't go over the maximum number of threads.
		for (int loopNum = 0; loopNum < numThreads / maxThreads + 1; loopNum++) {
			// Adds Mul() threads for however many elements are needed to be done.
			while (numString.size() > threads.size()) {
				threads.add(new Mul());
			}
			// Starting those threads.
			for (int i = 0; i < threads.size(); i++) {
				threads.get(i).start(numString.get(i), i);
			}
			// Looping through, join()ing the threads, and checking their persistence.
			for (int i = 0; i < Mul.initialValues.size(); i++) {
				// Because they're threads, we have to do a try/catch.
				try {
					// Waiting in case the thread is still going at it.
					threads.get(i).join();
					// Checking if the thread's persistence is higher than the current persistence.
					if (Mul.steps.get(i) > MAXSTEPS) {
						// Setting the persistence and value that generated that persistence.
						MAXSTEPS = Mul.steps.get(i);
						MAXNUMBER = Mul.initialValues.get(i).toString();
					}
				} catch (Exception ex) {
					// A quick Exception catch.
					System.out.println("Exception has been caught in threading.java: " + ex);
				}
			}
		}
		// We clear these values so that they don't take up space and cause issues later on.
		Mul.initialValues.clear();
		Mul.steps.clear();
		threads.clear();
		numString.clear();
    }
}

