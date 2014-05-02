package ExpVisitor.Evaluator;

import Exp.*;
import ExpVisitor.IExpVisitor;

import java.util.Map;

/**
 * Created by novokrest on 4/16/14.
 */
public class Evaluator implements IExpVisitor<Exp> {
    Map<String, Exp> context;
    boolean isSimplify;

    public Evaluator(Map<String, Exp> context) {
        this.context = context;
        isSimplify = true;
    }

    public void setSimplifyMode() {
        isSimplify = true;
    }

    public void setEvaluateMode() {
        isSimplify = false;
    }

    @Override
    public Exp visit(Num num) {
        return num;
    }

    @Override
    public Exp visit(Sum sum) {
        Exp left = sum.left.accept(this);
        if (left instanceof ErrorExp) {
            return left;
        }
        Exp right = sum.right.accept(this);
        if (right instanceof ErrorExp) {
            return right;
        }
        if ((left instanceof Num) && (right instanceof Num)) {
            return new Num(((Num) left).number + ((Num) right).number);
        }
        return new Sum(left, right);
    }

    @Override
    public Exp visit(Differ Differ) {
        Exp left = Differ.left.accept(this);
        if (left instanceof ErrorExp) {
            return left;
        }
        Exp right = Differ.right.accept(this);
        if (right instanceof ErrorExp) {
            return right;
        }
        if ((left instanceof Num) && (right instanceof Num)) {
            return new Num(((Num) left).number - ((Num) right).number);
        }
        return new Sum(left, right);
    }

    @Override
    public Exp visit(Mul mul) {
        Exp left = mul.left.accept(this);
        if (left instanceof ErrorExp) {
            return left;
        }
        Exp right = mul.right.accept(this);
        if (right instanceof ErrorExp) {
            return right;
        }
        if ((left instanceof Num) && (right instanceof Num)) {
            return new Num(((Num) left).number * ((Num) right).number);
        }
        return new Mul(left, right);
    }

    @Override
    public Exp visit(Div div) throws RuntimeException {
        Exp left = div.left.accept(this);
        if (left instanceof ErrorExp) {
            return left;
        }
        Exp right = div.right.accept(this);
        if (right instanceof ErrorExp) {
            return right;
        }
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
        Exp value = assign.exp.accept(this);
        if (value instanceof ErrorExp) {
            return value;
        }
        context.put(assign.var.name, value);
        return value;
    }

    @Override
    public Exp visit(ErrorExp error) {
        return error;
    }

    @Override
    public Exp visit(Variable var) {
        if (context.containsKey(var.name)) {
            return context.get(var.name).accept(this);
        } else {
            return isSimplify ? var : new ErrorExp("Error: " + var.name + " is undefined");
        }
    }
}
