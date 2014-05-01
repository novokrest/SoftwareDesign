package Exp;

import Evaluator.ExpVisitor;

/**
 * Created by novokrest on 4/16/14.
 */
public class Div extends BiExp {
    public Div(Exp left, Exp right) {
        super(left, right);
    }

    @Override
    public <T> T accept(ExpVisitor<T> expVisitor) {
        return expVisitor.visit(this);
    }
    /*
    @Override
    public void accept(Evaluator visitor) {
        visitor.visit(this);
    }
    @Override
    public void traverse(Evaluator visitor) {
        left.traverse(visitor);
        visitor.visit(this);
        right.traverse(visitor);
    }
    */
}
