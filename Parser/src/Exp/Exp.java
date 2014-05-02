package Exp;

import ExpVisitor.IExpVisitor;
import java.util.Iterator;

/**
 * Created by novokrest on 4/16/14.
 */
public interface Exp {
    public <T> T accept (IExpVisitor<T> expVisitor);
    public Iterator<Exp> iterator();
}
