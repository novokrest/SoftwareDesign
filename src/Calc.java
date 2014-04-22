import Exp.*;
import ExpVisitor.Evaluator;
import ExpVisitor.PrettyPrinter;

import java.util.Map;
import java.util.TreeMap;

public class Calc {

    public static void main(String[] args) {
        Exp exp = new Sum(
                new Mul(
                        new Sum(new Num(10.0), new Variable("x")),
                        new Sum(new Num(22.0), new Num(14.0))
                ),
                new Div(
                        new Sum(new Num(15.0), new Num(88.0)),
                        new Variable("y")
                )
        );
        try {
            Map<String, Exp> context = new TreeMap<String, Exp>();
            context.put("x", new Num(3.0));
            //context.put("y", new Num(8.0));
            Exp evaluatedExp = exp.accept(new Evaluator(context));
            String printedExp = evaluatedExp.accept(new PrettyPrinter());
            System.out.println(printedExp);
        }
        catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        }

        /*
        Iterator<Exp> i = exp.iterator();
        while(i.hasNext()) {
            // TODO: implement
        }
        */
    }
}