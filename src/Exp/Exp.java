package Exp;

import ExpVisitor.ExpVisitor;

import java.util.Iterator;

/**
 * Created by novokrest on 4/16/14.
 */
public interface Exp {
    public <T> T accept (ExpVisitor<T> expVisitor);
    //public Iterator<Exp> iterator();

}