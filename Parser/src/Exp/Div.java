package Exp;

import ExpVisitor.IExpVisitor;

import java.util.Iterator;

/**
 * Created by novokrest on 4/16/14.
 */
public class Div extends BiExp {
    public Div(Exp left, Exp right) {
        super(left, right);
    }

    @Override
    public <T> T accept(IExpVisitor<T> expVisitor) {
        return expVisitor.visit(this);
    }

    @Override
    public Iterator<Exp> iterator() {
        return null;
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
