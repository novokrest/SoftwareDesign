package Parser;

import Exp.*;
import Lexer.TokenIterator.ITokenIterator;
import Lexer.Token.*;

/**
 * Created by Admin on 4/27/14.
 */
public class Parser {
    ITokenIterator tokenIterator;
    boolean errorOccurred;
    int errorPosition;

    public Parser() {
        errorOccurred = false;
        errorPosition = -1;
    }

    public Exp parse(ITokenIterator tokenIterator) {
        this.tokenIterator = tokenIterator;

        tokenIterator.next();
        if (tokenIterator.get().getType() == TokenType.VAR) {
            String varName = tokenIterator.get().getValue();
            tokenIterator.next();
            if (tokenIterator.get().getType() == TokenType.ASSIGN) {
                tokenIterator.next();
                Exp exp = getExpression();
                if (exp instanceof ErrorExp) {
                    return exp;
                }
                return new Assignment(new Variable(varName), exp);
            }
            tokenIterator.prev();
        }
        return getExpression();
    }

    private Exp getExpression() {
        ExpBuilder expression = new ExpBuilder();
        Exp summand = getSummand();
        if (summand instanceof ErrorExp) {
            return summand;
        }
        expression.addOperand(summand);

        while (tokenIterator.get().getType() == TokenType.PLUS
                || tokenIterator.get().getType() == TokenType.MINUS) {
            expression.addOperator(tokenIterator.get().getType());
            tokenIterator.next();
            summand = getSummand();
            if (summand instanceof ErrorExp) {
                return summand;
            }
            expression.addOperand(summand);
        }

        return expression.getExp();
    }

    private Exp getSummand() {
        ExpBuilder summand = new ExpBuilder();
        Exp factor = getFactor();
        if (factor instanceof ErrorExp) {
            return factor;
        }
        summand.addOperand(factor);

        while (tokenIterator.get().getType() == TokenType.MUL
                || tokenIterator.get().getType() == TokenType.DIV) {
            summand.addOperator(tokenIterator.get().getType());
            tokenIterator.next();
            factor = getFactor();
            if (factor instanceof ErrorExp) {
                return factor;
            }
            summand.addOperand(factor);
        }

        return summand.getExp();
    }

    private Exp getFactor() {
        if (tokenIterator.get().getType() == TokenType.NUM) {
            Num num = new Num(Double.parseDouble(tokenIterator.get().getValue()));
            tokenIterator.next();
            return num;
        }
        if (tokenIterator.get().getType() == TokenType.VAR) {
            Variable variable = new Variable(tokenIterator.get().getValue());
            tokenIterator.next();
            return variable;
        }
        if (tokenIterator.get().getType() == TokenType.LEFT_BRACKET) {
            tokenIterator.next();
            Exp expression = getExpression();
            if (tokenIterator.get().getType() == TokenType.RIGHT_BRACKET) {
                tokenIterator.next();
                return expression;
            }
        }
        return new ErrorExp("Error: couldn't parse at " + tokenIterator.get().getStartPosition() + " position");
    }

}
