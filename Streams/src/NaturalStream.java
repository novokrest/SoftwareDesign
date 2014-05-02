/**
 * Created by Admin on 5/2/14.
 */
public class NaturalStream implements IntegerStream {
    private int current = 1;

    @Override
    public int getNext() {
        return current++;
    }
}
