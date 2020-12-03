import java.io.IOException;
import java.util.ArrayList;

public class mainTest {
    public static void main(String[] args) throws InterruptedException {
        /*ArrayList<Mul> threads = new ArrayList<>();
        ArbitraryInteger num = new ArbitraryInteger("1");
        int maxSteps = 0;
        String maxNum = "";
        while (true) {
            for (int i = 0; i < 100; i++) {
                threads.add(new Mul());
                threads.get(i).start(num.toString(), i);
                num = num.add(ArbitraryInteger.ONE);
            }
            for (int i = 0; i < 100; i++) {
                threads.get(i).join();
                if (Mul.steps.get(i) > maxSteps) {
                    maxSteps = Mul.steps.get(i);
                    maxNum = Mul.initialValues.get(i).toString();
                    System.out.println("New largest steps: " + maxSteps + ": " + maxNum);
                }
            }
            Mul.steps.clear();
            Mul.initialValues.clear();
            threads.clear();
        }*/
        Mul thread = new Mul();
        thread.start("67777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777788888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888889999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999", 0);
        thread.join();
        System.out.println(Mul.steps.get(0));
    }
}
