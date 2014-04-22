package Exp;

import ExpVisitor.ExpVisitor;

/**
 * Created by novokrest on 4/16/14.
 */
public class Num implements Exp {
    public final Double number;
    public Num(Double number) {
        this.number = number;
    }

    @Override
    public <T> T accept(ExpVisitor<T> expVisitor) {
        return expVisitor.visit(this);
    }
}
