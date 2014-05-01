package Parser;

import Exp.*;
import Lexer.Token.TokenType;

/**
 * Created by Admin on 4/27/14.
 */
public class ExpBuilder {
    Exp operand;
    TokenType operator;

    public ExpBuilder() {
        operand = null;
        operator = null;
    }

    public Exp getExp() {
        return operand;
    }

    public void addOperand(Exp operand) {
        if (this.operand == null) {
            this.operand = operand;
        } else {
            createBiExp(operand);
        }
    }

    public void addOperator(TokenType operator) {
        this.operator = operator;
    }

    private void createBiExp(Exp secondOperand) {
        switch (operator) {
            case PLUS: operand = new Sum(operand, secondOperand);
                break;
            case MINUS: operand = new Differ(operand, secondOperand);
                break;
            case MUL: operand = new Mul(operand, secondOperand);
                break;
            case DIV: operand = new Div(operand, secondOperand);
                break;
            default:
                break;
        }
    }
}
