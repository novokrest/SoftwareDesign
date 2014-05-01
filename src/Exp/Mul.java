package Exp;

import Evaluator.ExpVisitor;

/**
 * Created by novokrest on 4/16/14.
 */
public class Mul extends BiExp {
    public Mul(Exp left, Exp right) {
        super(left, right);
    }

    @Override
    public <T> T accept(ExpVisitor<T> expVisitor) {
        return expVisitor.visit(this);
    }
}
