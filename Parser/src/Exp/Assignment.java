package Exp;

import ExpVisitor.IExpVisitor;

import java.util.Iterator;

/**
 * Created by novokrest on 4/16/14.
 */
public class Assignment implements Exp {
    public final Variable var;
    public final Exp exp;

    public Assignment(Variable var, Exp exp) {
        this.var = var;
        this.exp = exp;
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
