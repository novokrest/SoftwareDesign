package Exp;

import ExpVisitor.IExpVisitor;

import java.util.Iterator;

/**
 * Created by novokrest on 4/16/14.
 */
public class Sum extends BiExp{
    public Sum(Exp left, Exp right) {
        super(left, right);
    }

    @Override
    public <T> T accept(IExpVisitor<T> expVisitor) {
        return expVisitor.visit(this);
    }

    @Override
    public Iterator<Exp> iterator() {
        return null;
    }
}
