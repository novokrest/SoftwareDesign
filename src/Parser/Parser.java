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

    public Parser(ITokenIterator tokenIterator) {
        this.tokenIterator = tokenIterator;
        errorOccurred = false;
        errorPosition = -1;
    }

    public Exp parse() {
        tokenIterator.next();
        return getExpression().getExp();
    }

    private ExpBuilder getExpression() {
        ExpBuilder expression = new ExpBuilder();
        expression.addOperand(getSummand().getExp());
        while (tokenIterator.get().getType() == TokenType.PLUS
                || tokenIterator.get().getType() == TokenType.MINUS) {
            expression.addOperator(tokenIterator.get().getType());
            tokenIterator.next();
            expression.addOperand(getSummand().getExp());
        }

        return expression;
    }

    private ExpBuilder getSummand() {
        ExpBuilder summand = new ExpBuilder();
        summand.addOperand(getFactor());
        while (tokenIterator.get().getType() == TokenType.MUL
                || tokenIterator.get().getType() == TokenType.DIV) {
            summand.addOperator(tokenIterator.get().getType());
            tokenIterator.next();
            summand.addOperand(getFactor());
        }

        return summand;
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
            ExpBuilder expression = getExpression();
            if (tokenIterator.get().getType() == TokenType.RIGHT_BRACKET) {
                tokenIterator.next();
                return expression.getExp();
            }
        }
        //set error
        return null;
    }

}
