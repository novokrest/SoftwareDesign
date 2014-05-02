package Lexer.Token;

/**
 * Created by Admin on 4/27/14.
 */
public class Token {
    TokenType type;
    String value;
    int startPosition;

    public Token(TokenType type, String value, int startPosition) {
        this.type = type;
        this.value = value;
        this.startPosition = startPosition;
    }

    public TokenType getType() {
        return type;
    }
    public String getValue() { return value; }
    public int getLength() { return value.length(); }
    public int getStartPosition() { return startPosition; }

    public boolean isOperator() {
        return (type == TokenType.PLUS)
                || (type == TokenType.MINUS)
                || (type == TokenType.MUL)
                || (type == TokenType.DIV)
                || (type == TokenType.ASSIGN)
                || (type == TokenType.LEFT_BRACKET)
                || (type == TokenType.RIGHT_BRACKET);
    }

    public boolean isOperand() {
        return (type == TokenType.NUM)
                || (type == TokenType.VAR);
    }

    public boolean isError() {
        return !isOperand() && !isOperator();
    }
}
