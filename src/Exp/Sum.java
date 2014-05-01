package Exp;

import Evaluator.ExpVisitor;

/**
 * Created by novokrest on 4/16/14.
 */
public class Sum extends BiExp{
    public Sum(Exp left, Exp right) {
        super(left, right);
    }

    @Override
    public <T> T accept(ExpVisitor<T> expVisitor) {
        return expVisitor.visit(this);
    }
}
