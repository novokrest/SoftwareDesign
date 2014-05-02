/**
 * Created by Admin on 5/2/14.
 */
public class DividedIntoThreeFilter implements IntegerStream {
    private IntegerStream innerStream;

    public DividedIntoThreeFilter(IntegerStream innerStream) {
        this.innerStream = innerStream;
    }

    @Override
    public int getNext() {
        int current;
        while ((current = innerStream.getNext()) % 3 != 0) {}
        return current;
    }
}
