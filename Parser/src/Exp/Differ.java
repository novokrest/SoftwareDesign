package Exp;

import ExpVisitor.IExpVisitor;

import java.util.Iterator;

/**
 * Created by Admin on 4/28/14.
 */
public class Differ extends BiExp {
    public Differ(Exp left, Exp right) {
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
