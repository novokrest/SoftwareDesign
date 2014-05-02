package Exp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Admin on 5/1/14.
 */
public class ExpIterator implements Iterator<Exp> {
    private List<Exp> expList;
    private int current;

    public ExpIterator() {
        expList = new ArrayList<Exp>();
        current = -1;
    }

    public void add(Exp exp) {
        expList.add(exp);
    }

    @Override
    public boolean hasNext() {
        return !((current + 1) == expList.size());
    }

    @Override
    public Exp next() {
        current++;
        return expList.get(current);
    }

    @Override
    public void remove() {
        expList.remove(current);
    }
}
