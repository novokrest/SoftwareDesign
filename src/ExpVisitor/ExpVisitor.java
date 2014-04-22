package ExpVisitor;

import Exp.*;

/**
 * Created by novokrest on 4/16/14.
 */
public interface ExpVisitor<T> {
    T visit(Num num);
    T visit(Sum sum);
    T visit(Mul mul);
    T visit(Div div);
    T visit(Variable var);
    T visit(Assignment assign);
}
