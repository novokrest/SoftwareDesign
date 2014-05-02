package Exp;

import ExpVisitor.IExpVisitor;

import java.util.Iterator;

/**
 * Created by Admin on 4/27/14.
 */
public class ErrorExp implements Exp {
    private String message;

    public ErrorExp(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
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
