package Exp;

import Evaluator.ExpVisitor;

/**
 * Created by novokrest on 4/16/14.
 */
public class Variable implements Exp {
    public final String name;

    public Variable(String name) {
        this.name = name;
    }
    @Override
    public <T> T accept(ExpVisitor<T> expVisitor) {
        return expVisitor.visit(this);
    }
}
