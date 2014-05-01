package Lexer.LexemeParsers;

import Lexer.Token.Token;
import Lexer.Token.TokenType;

/**
 * Created by Admin on 4/27/14.
 */
public class SimpleLexemeParser implements ILexemeParser {
    TokenType type;
    String lexeme;

    public SimpleLexemeParser(TokenType type, String lexeme) {
        this.type = type;
        this.lexeme = lexeme;
    }

    @Override
    public Token parse(String input, int startPosition) {
        if ((startPosition + lexeme.length() <= input.length())
                && lexeme.equals(input.substring(startPosition, startPosition + lexeme.length()))) {
            return new Token(type, lexeme, startPosition);
        }
        return null;
    }
}
