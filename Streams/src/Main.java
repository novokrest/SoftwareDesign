import java.awt.*;

/**
 * Created by Admin on 5/2/14.
 */
public class Main {
    public static void main(String[] args) {
        NaturalStream naturalStream1 = new NaturalStream();
        NaturalStream naturalStream2 = new NaturalStream();
        NaturalStream naturalStream3 = new NaturalStream();
        FibonacciFilter fibonacciStream = new FibonacciFilter(naturalStream1);
        DividedIntoThreeFilter dividedIntoThreeStream = new DividedIntoThreeFilter(naturalStream2);
        SecondDegreeNumberFilter secondDegreeNumberStream = new SecondDegreeNumberFilter(naturalStream3);

        for (int i = 0; i < 10; i++) {
            System.out.println("i = " + i);
            System.out.println("FibonacciStream: " + fibonacciStream.getNext());
            System.out.println("DividedIntoThreeStream: " + dividedIntoThreeStream.getNext());
            System.out.println("SecondDegreeNumberStream: " + secondDegreeNumberStream.getNext());
            System.out.println();
        }
    }
}
