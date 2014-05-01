package Evaluator;

import Exp.*;

import java.util.Map;

/**
 * Created by novokrest on 4/16/14.
 */
public class Evaluator implements ExpVisitor<Exp> {
    Map<String, Exp> context;

    public Evaluator(Map<String, Exp> context) {
        this.context = context;
    }

    @Override
    public Exp visit(Num num) {
        return num;
    }

    @Override
    public Exp visit(Sum sum) {
        Exp left = sum.left.accept(this);
        Exp right = sum.right.accept(this);
        if ((left instanceof Num) && (right instanceof Num)) {
            return new Num(((Num) left).number + ((Num) right).number);
        }
        return new Sum(left, right);
    }

    @Override
    public Exp visit(Differ Differ) {
        Exp left = Differ.left.accept(this);
        Exp right = Differ.right.accept(this);
        if ((left instanceof Num) && (right instanceof Num)) {
            return new Num(((Num) left).number - ((Num) right).number);
        }
        return new Sum(left, right);
    }

    @Override
    public Exp visit(Mul mul) {
        Exp left = mul.left.accept(this);
        Exp right = mul.right.accept(this);
        if ((left instanceof Num) && (right instanceof Num)) {
            return new Num(((Num) left).number * ((Num) right).number);
        }
        return new Mul(left, right);
    }

    @Override
    public Exp visit(Div div) throws RuntimeException {
        Exp left = div.left.accept(this);
        Exp right = div.right.accept(this);
        if ((left instanceof Num) && (right instanceof Num)) {
            if (((Num) right).number == 0) {
                throw new RuntimeException("Divide by zero");
            }
            return new Num(((Num) left).number / ((Num) right).number);
        }
        return new Div(left, right);
    }

    @Override
    public Exp visit(Assignment assign) {
        context.put(assign.var.name, assign.exp.accept(this));
        return assign.exp.accept(this);
    }

    @Override
    public Exp visit(Variable var) {
        if (context.containsKey(var.name)) {
            return context.get(var.name).accept(this);
        } else {
            return var;
        }
    }
}
