package Exp;

import ExpVisitor.ExpVisitor;

import java.beans.Expression;

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
    public <T> T accept(ExpVisitor<T> expVisitor) {
        return expVisitor.visit(this);
    }
}
