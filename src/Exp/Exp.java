package Exp;

import Evaluator.ExpVisitor;

/**
 * Created by novokrest on 4/16/14.
 */
public interface Exp {
    public <T> T accept (ExpVisitor<T> expVisitor);
    //public Iterator<Exp> iterator();
}
