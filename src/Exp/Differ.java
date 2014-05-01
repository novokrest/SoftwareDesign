package Exp;

import Evaluator.ExpVisitor;

/**
 * Created by Admin on 4/28/14.
 */
public class Differ extends BiExp {
    public Differ(Exp left, Exp right) {
        super(left, right);
    }

    @Override
    public <T> T accept(ExpVisitor<T> expVisitor) {
        return expVisitor.visit(this);
    }
}
