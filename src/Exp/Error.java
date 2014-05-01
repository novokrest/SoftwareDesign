package Exp;

import Evaluator.ExpVisitor;

/**
 * Created by Admin on 4/27/14.
 */
public class Error implements Exp {
    @Override
    public <T> T accept(ExpVisitor<T> expVisitor) {
        return null;
    }
}
