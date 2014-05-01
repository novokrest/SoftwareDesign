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
}
