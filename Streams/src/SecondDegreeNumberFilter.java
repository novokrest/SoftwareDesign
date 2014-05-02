/**
 * Created by Admin on 5/2/14.
 */
public class SecondDegreeNumberFilter implements IntegerStream {
    private IntegerStream innerStream;

    public SecondDegreeNumberFilter(IntegerStream innerStream) {
        this.innerStream = innerStream;
    }

    @Override
    public int getNext() {
        int current;
        while (!isSecondDegree(current = innerStream.getNext())) {

        }
        return current;
    }

    private boolean isSecondDegree(int number) {
        if (number < 0) {
            return false;
        }
        int i = 0;
        while (number > i*i) {
            i++;
        }
        return number == i * i;
    }
}
