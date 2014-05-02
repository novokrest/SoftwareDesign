package Exp;

import ExpVisitor.IExpVisitor;

import java.util.Iterator;

/**
 * Created by novokrest on 4/16/14.
 */
public class Variable implements Exp {
    public final String name;

    public Variable(String name) {
        this.name = name;
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
