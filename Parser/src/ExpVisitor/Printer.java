package ExpVisitor;

import Exp.*;

/**
 * Created by novokrest on 4/16/14.
 */

public class Printer implements IExpVisitor<String> {
    @Override
    public String visit(Num num) {
        return num.number.toString();
    }

    @Override
    public String visit(Sum sum) {
        return "(" + sum.left.accept(this) + " + " + sum.right.accept(this) + ")";
    }

    @Override
    public String visit(Differ differ) {
        return "(" + differ.left.accept(this) + " - " + differ.right.accept(this) + ")";
    }

    @Override
    public String visit(Mul mul) {
        return mul.left.accept(this) + " * " + mul.right.accept(this);
    }

    @Override
    public String visit(Div div) {
        return div.left.accept(this) + " / " + div.right.accept(this);
    }

    @Override
    public String visit(Variable var) {
        return var.name;
    }

    @Override
    public String visit(Assignment assign) {
        return assign.var.name + " = " + assign.exp.accept(this);
    }

    @Override
    public String visit(ErrorExp error) {
        return error.getMessage();
    }
}
