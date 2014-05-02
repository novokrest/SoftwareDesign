/**
 * Created by Admin on 5/2/14.
 */
public class FibonacciFilter implements IntegerStream {
    private int f1 = 0;
    private int f2 = 1;
    private IntegerStream innerStream;

    public FibonacciFilter(IntegerStream innerStream) {
        this.innerStream = innerStream;
    }

    @Override
    public int getNext() {
        int current = innerStream.getNext();
        while (current != f2) {
            if (current < f2) {
                current = innerStream.getNext();
            } else {
                f2 = f2 + f1;
                f1 = f2 - f1;
            }
        }
        return current;
    }
}
