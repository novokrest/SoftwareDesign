package Console;

import Exp.*;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.util.*;

/**
 * Created by Admin on 5/3/14.
 */
public class UndoManagerExpression {
    private Map<String, Exp> context;
    private Stack<Exp> expressions;

    public UndoManagerExpression(Map<String, Exp> context) {
        this.context = context;
        expressions = new Stack<Exp>();
    }

    public void addEdit(Exp expression) {
        expressions.add(expression);
    }

    public boolean canUndo() {
        return expressions.size() > 0;
    }

    public void undo() {
        Exp lastExpression = expressions.pop();
        if (lastExpression instanceof Assignment) {
            context.remove(((Assignment) lastExpression).var.name);
        }
    }
}
