package Exp;

import ExpVisitor.ExpVisitor;

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
    public void accept(ExpVisitor visitor) {
        visitor.visit(this);
    }
    @Override
    public void traverse(ExpVisitor visitor) {
        left.traverse(visitor);
        visitor.visit(this);
        right.traverse(visitor);
    }
    */
}
