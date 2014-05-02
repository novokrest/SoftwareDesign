package Exp;

import ExpVisitor.IExpVisitor;

import java.util.Iterator;

/**
 * Created by novokrest on 4/16/14.
 */
public class Num implements Exp {
    public final Double number;

    public Num(Double number) {
        this.number = number;
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
